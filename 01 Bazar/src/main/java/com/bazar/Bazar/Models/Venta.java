package com.bazar.Bazar.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idVenta;

    @Column(name = "Fecha")
    private LocalDate fechaVenta;

    @Column(name = "Importe Neto")
    private double importeNeto;

    @Column(name = "Descuento")
    private double descuento;

    @Column(name = "Iva")
    private double iva;

    @Column(name="Otro impuestos")
    private  double otrosImpuetos;

    @Column(name = "Total")
    private double total;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @OneToMany(mappedBy = "venta")
    private List<DetalleVenta> lDetalle;

}
