package com.EduTrack.persistance.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table (name = "curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "docente_id")
    private Usuarios docente;

    @ManyToMany
    @JoinTable(
            name = "curso_estudiantes",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "estudiante_id")
    )
    private List<Usuarios> estudiantes;

    private Double precio;

    @Column(nullable = false)
    private Boolean activo = true;

    @Column(name = "disponible_para_compra", nullable = false)
    private Boolean disponibleParaCompra = false;

    @Column(columnDefinition = "TEXT")
    private String requisitos;

    @Column(columnDefinition = "TEXT")
    private String objetivos;

    @Column(columnDefinition = "TEXT")
    private String incluye;

    @Column(length = 500)
    private String imagen;



    // Constructor vacío
    public Curso() {}

    // Getters y setters
    public Long getId() {return id;}

    public void setId(Long id) {this.id = id;}

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getDescripcion() {return descripcion;}

    public void setDescripcion(String descripcion) {this.descripcion = descripcion;}

    public Usuarios getDocente() {return docente;}

    public void setDocente(Usuarios docente) {this.docente = docente;}

    public List<Usuarios> getEstudiantes() {return estudiantes;}

    public void setEstudiantes(List<Usuarios> estudiantes) {this.estudiantes = estudiantes;}

    public Double getPrecio() {return precio;}

    public void setPrecio(Double precio) {this.precio = precio;}

    public Boolean getActivo() {return activo;}

    public void setActivo(Boolean activo) {this.activo = activo;}

    public String getRequisitos() {return requisitos;}

    public void setRequisitos(String requisitos) {this.requisitos = requisitos;}

    public String getObjetivos() {return objetivos;}

    public void setObjetivos(String objetivos) {this.objetivos = objetivos;}

    public String getIncluye() {return incluye;}

    public void setIncluye(String incluye) {this.incluye = incluye;}

    public String getImagen() {return imagen;}

    public void setImagen(String imagen) {this.imagen = imagen;}

    public Boolean getDisponibleParaCompra() {return disponibleParaCompra;}

    public void setDisponibleParaCompra(Boolean disponibleParaCompra) {this.disponibleParaCompra = disponibleParaCompra;}
}
