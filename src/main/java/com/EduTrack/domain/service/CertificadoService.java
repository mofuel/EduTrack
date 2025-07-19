package com.EduTrack.domain.service;

import com.EduTrack.domain.dto.CursoDTO;
import com.EduTrack.domain.dto.ProgresoCursoDTO;
import com.EduTrack.domain.repository.ProgresoContenidoRepository;
import com.EduTrack.domain.repository.UsuariosRepository;
import com.EduTrack.domain.repository.CursoRepository;
import com.EduTrack.persistance.entity.Curso;
import com.EduTrack.persistance.entity.Usuarios;
import com.EduTrack.persistance.mapper.CursoMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Service
public class CertificadoService {

    @Autowired
    private ProgresoContenidoRepository progresoContenidoRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoMapper cursoMapper;


    public CursoDTO obtenerCursoConProgreso(String usuarioId, Long cursoId) {
        Curso curso = cursoService.obtenerCursoPorId(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));

        // Mapear el curso a DTO
        CursoDTO dto = cursoMapper.toDTO(curso);

        // Obtener el progreso del usuario en ese curso
        Double porcentajeAvance = progresoContenidoRepository
                .obtenerAvancePorUsuarioYCurso(usuarioId, cursoId)
                .map(ProgresoCursoDTO::getPorcentajeAvance)
                .orElse(0.0);

        // Decorar el DTO con el porcentaje
        dto.setPorcentajeAvance(porcentajeAvance);

        return dto;
    }


    public byte[] generarCertificado(String usuarioId, Long cursoId) throws Exception {
        // Verificar si el usuario completó el curso (avance 100%)
        Optional<ProgresoCursoDTO> progreso = progresoContenidoRepository
                .obtenerAvancePorUsuarioYCurso(usuarioId, cursoId);

        if (progreso.isEmpty() || progreso.get().getPorcentajeAvance() < 100) {
            throw new IllegalStateException("El curso aún no ha sido completado.");
        }

        // Obtener datos del estudiante y curso
        Optional<Usuarios> usuarioOpt = usuariosRepository.getById(usuarioId);
        Optional<Curso> cursoOpt = cursoRepository.getById(cursoId);

        if (usuarioOpt.isEmpty() || cursoOpt.isEmpty()) {
            throw new IllegalArgumentException("Datos inválidos para generar el certificado.");
        }

        Usuarios usuario = usuarioOpt.get();
        Curso curso = cursoOpt.get();

        // Crear PDF con iText
        Document documento = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(documento, out);

        documento.open();

        Font titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24);
        Font normal = FontFactory.getFont(FontFactory.HELVETICA, 12);

        documento.add(new Paragraph("CERTIFICADO DE FINALIZACIÓN", titulo));
        documento.add(new Paragraph("\n"));
        documento.add(new Paragraph("Se otorga a:", normal));
        documento.add(new Paragraph(usuario.getNombre() + " " + usuario.getApellido(), titulo));
        documento.add(new Paragraph("\n"));
        documento.add(new Paragraph("Por haber completado satisfactoriamente el curso:", normal));
        documento.add(new Paragraph("\"" + curso.getNombre() + "\"", titulo));
        documento.add(new Paragraph("\n"));
        documento.add(new Paragraph("Dictado por: " + curso.getDocente().getNombre(), normal));
        documento.add(new Paragraph("Fecha de finalización: " +
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), normal));
        documento.add(new Paragraph("\n\n"));
        documento.add(new Paragraph("¡Felicidades por tu logro!", normal));

        documento.close();
        return out.toByteArray();
    }
}
