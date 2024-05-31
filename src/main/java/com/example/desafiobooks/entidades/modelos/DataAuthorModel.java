package com.example.desafiobooks.entidades.modelos;

import jakarta.persistence.*;

@Entity
@Table (name="autores")
public class DataAuthorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(unique = true)
    private String nombre;
    private String fechaNacimiento;

    public DataAuthorModel() {
    }

    public DataAuthorModel(String nombre, String fechaNacimiento) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public DataAuthorModel(Long id, String nombre, String fechaNacimiento) {
        Id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "DataAuthor as class{" +
                "nombre='" + nombre + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                '}';
    }
}
