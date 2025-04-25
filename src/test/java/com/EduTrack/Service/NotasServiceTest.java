package com.EduTrack.Service;

import com.EduTrack.DTO.NotaDTO;
import com.EduTrack.Model.Notas;
import com.EduTrack.Model.Usuarios;
import com.EduTrack.Repository.NotasRepository;
import com.EduTrack.Repository.UsuariosRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class NotasServiceTest {

    @Mock
    private NotasRepository notasRepository;

    @Mock
    private UsuariosRepository usuariosRepository;

    @InjectMocks
    private NotasService notasService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGuardarNotaDesdeDTO_Success() {
        NotaDTO notaDTO = new NotaDTO();
        notaDTO.setUsuarioId("U001");
        notaDTO.setCalificacion(18.5);
        notaDTO.setFecha(new Date());
        notaDTO.setComentario("Muy bien");

        Usuarios usuario = new Usuarios();
        usuario.setId("U001");

        when(usuariosRepository.findById("U001")).thenReturn(Optional.of(usuario));

        Notas notaGuardada = new Notas();
        notaGuardada.setId(1L);
        notaGuardada.setEstudiante(usuario);
        notaGuardada.setCalificacion(18.5);
        notaGuardada.setComentario("Muy bien");

        when(notasRepository.save(any(Notas.class))).thenReturn(notaGuardada);

        Notas resultado = notasService.guardarNotaDesdeDTO(notaDTO);

        assertNotNull(resultado);
        assertEquals("Muy bien", resultado.getComentario());
        assertEquals(18.5, resultado.getCalificacion());
        verify(notasRepository, times(1)).save(any(Notas.class));
    }

    @Test
    public void testGuardarNotaDesdeDTO_UsuarioNoExiste() {
        NotaDTO notaDTO = new NotaDTO();
        notaDTO.setUsuarioId("U999");

        when(usuariosRepository.findById("U999")).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () ->
                notasService.guardarNotaDesdeDTO(notaDTO)
        );

        assertTrue(exception.getMessage().contains("Usuario no encontrado"));
    }

    @Test
    public void testEliminarNota() {
        Long id = 1L;
        doNothing().when(notasRepository).deleteById(id);
        notasService.eliminarNota(id);
        verify(notasRepository, times(1)).deleteById(id);
    }

    @Test
    public void testObtenerPromedio() {
        String estudianteId = "U001";

        Notas nota1 = new Notas(); nota1.setCalificacion(18.0);
        Notas nota2 = new Notas(); nota2.setCalificacion(20.0);

        when(notasRepository.findByEstudianteId(estudianteId)).thenReturn(Arrays.asList(nota1, nota2));

        Double promedio = notasService.obtenerPromedio(estudianteId);
        assertEquals(19.0, promedio);
    }
}
