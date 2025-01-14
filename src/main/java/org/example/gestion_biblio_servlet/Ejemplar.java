package org.example.gestion_biblio_servlet;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "ejemplar")
public class Ejemplar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "isbn", nullable = false)
    private org.example.gestion_biblio_servlet.Libro isbn;

    @Lob
    @Column(name = "estado")
    private String estado;

    @OneToMany(mappedBy = "ejemplar")
    private Set<org.example.gestion_biblio_servlet.Prestamo> prestamos = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public org.example.gestion_biblio_servlet.Libro getIsbn() {
        return isbn;
    }

    public void setIsbn(org.example.gestion_biblio_servlet.Libro isbn) {
        this.isbn = isbn;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Set<org.example.gestion_biblio_servlet.Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Set<org.example.gestion_biblio_servlet.Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

}