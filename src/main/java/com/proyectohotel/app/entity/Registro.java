package com.proyectohotel.app.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "registros")
public class Registro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Fecha de registro es obligatoria")
    @Column(name = "fecha_registro")
    private LocalDate fechaRegistro;

    @NotNull(message = "Cliente es obligatorio")
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @NotNull(message = "Habitación es obligatoria")
    @ManyToOne
    @JoinColumn(name = "habitacion_id", nullable = false)
    private Habitacion habitacion;

    // Constructors
    public Registro() {}

    public Registro(LocalDate fechaRegistro, Cliente cliente, Habitacion habitacion) {
        this.fechaRegistro = fechaRegistro;
        this.cliente = cliente;
        this.habitacion = habitacion;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    // Optional: toString method for logging and debugging
    @Override
    public String toString() {
        return "Registro{" +
                "id=" + id +
                ", fechaRegistro=" + fechaRegistro +
                ", cliente=" + (cliente != null ? cliente.getId() : "null") +
                ", habitacion=" + (habitacion != null ? habitacion.getId() : "null") +
                '}';
    }
}
