package com.bazar.Bazar.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProducto;

    @Column(name = "Nombre Producto")
    private String nombre;

    @Column(name = "Marca")
    private String marca;

    @Column(name = "Precio Unitario")
    private Double precio;

    @Column(name = "Stock")
    private Integer stock;

    @OneToMany(mappedBy = "producto")
    private List<DetalleVenta> lDetalles;
}
