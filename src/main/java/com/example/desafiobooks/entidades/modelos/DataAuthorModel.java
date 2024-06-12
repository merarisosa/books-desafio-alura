package com.example.desafiobooks.entidades.modelos;
import com.example.desafiobooks.entidades.records.DataAuthor;
import jakarta.persistence.*;

@Entity
@Table (name="autores")
public class DataAuthorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(unique = true, nullable = false)
    private String nombre;

    @Column
    private String fechaNacimiento;

    @Column
    private String fechaMuerte;

    @ManyToOne //muchos autores pertenecen a un libro
    @JoinColumn(name = "libro_id")
    private DataBooksModel libro;

    public DataAuthorModel() {
    }

    public DataAuthorModel(String nombre, String fechaNacimiento, String fechaMuerte) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaMuerte = fechaMuerte;
    }

    public DataAuthorModel(Long id, String nombre, String fechaNacimiento, String fechaMuerte) {
        Id = id;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaMuerte = fechaMuerte;
    }

    public DataBooksModel getLibro() {
        return libro;
    }

    public void setLibro(DataBooksModel libro) {
        this.libro = libro;
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

    public String getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(String fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    @Override
    public String toString() {
        return "DataAuthor as class{" +
                "\nnombre= " + nombre +
                "\nfechaNacimiento= " + fechaNacimiento +
                "\nfechaMuerte= " + fechaMuerte +
                '}';
    }

    public static DataAuthorModel fromDataAuthor(DataAuthor dataAuthor) {
        DataAuthorModel authorModel = new DataAuthorModel();
        authorModel.setNombre(dataAuthor.nombre());
        authorModel.setFechaNacimiento(dataAuthor.fechaNacimiento());
        authorModel.setFechaMuerte(dataAuthor.fechaMuerte());
        //authorModel.setLibro();
        //authorModel.setId();
        return authorModel;
    }
}
