package com.bazar.Bazar.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idVenta")
    private Venta venta;

    @ManyToOne
    @JoinColumn(name ="idProducto")
    private Producto producto;

    @Column(name = "N Detalle")
    private int nroDetalle;

    @Column(name = "Cantidad")
    private  Integer cantidad;

    @Column(name = "Subtotal")
    private  double subtotal;


}
