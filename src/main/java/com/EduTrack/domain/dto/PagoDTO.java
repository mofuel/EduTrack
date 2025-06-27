package com.EduTrack.domain.dto;

import java.time.LocalDateTime;

public class PagoDTO {
    private Long id;
    private String usuarioId;
    private Long cursoId;
    private String metodoPago;
    private String referenciaPago;
    private LocalDateTime fechaPago;

    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getUsuarioId() {return usuarioId;}

    public void setUsuarioId(String usuarioId) {this.usuarioId = usuarioId;}

    public Long getCursoId() {return cursoId;}

    public void setCursoId(Long cursoId) {this.cursoId = cursoId;}

    public String getMetodoPago() {return metodoPago;}

    public void setMetodoPago(String metodoPago) {this.metodoPago = metodoPago;}

    public String getReferenciaPago() {return referenciaPago;}

    public void setReferenciaPago(String referenciaPago) {this.referenciaPago = referenciaPago;}

    public LocalDateTime getFechaPago() {return fechaPago;}

    public void setFechaPago(LocalDateTime fechaPago) {this.fechaPago = fechaPago;}
}
