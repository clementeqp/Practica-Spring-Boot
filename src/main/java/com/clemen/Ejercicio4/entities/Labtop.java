package com.clemen.Ejercicio4.entities;


import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Labtop {


    // atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String marca;
    private String modelo;
    private Integer ram;
    private Double price;
    private LocalDate fechaLanzamiento;
    private Boolean tieneSo;

    //Constructores

    public Labtop() {

    }

    public Labtop(Long id, String marca, String modelo, Integer ram, Double price, LocalDate fechaLanzamiento, Boolean tieneSo) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ram = ram;
        this.price = price;
        this.fechaLanzamiento = fechaLanzamiento;
        this.tieneSo = tieneSo;
    }

    //Getter and Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getRam() {
        return ram;
    }

    public void setRam(Integer ram) {
        this.ram = ram;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Boolean getTieneSo() {
        return tieneSo;
    }

    public void setTieneSo(Boolean tieneSo) {
        this.tieneSo = tieneSo;
    }
    //Vista

    @Override
    public String toString() {
        return "Labtop{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ram=" + ram +
                ", price=" + price +
                ", fechaLanzamiento=" + fechaLanzamiento +
                ", tieneSo=" + tieneSo +
                '}';
    }
}
