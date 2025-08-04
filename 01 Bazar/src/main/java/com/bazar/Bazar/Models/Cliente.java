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
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(name = "Nombre de Cliente")
    private String nombre;

    @Column(name = "Apellido de Cliente")
    private String apellido;

    @Column(name = "Dni")
    private String dni;

    @OneToMany(mappedBy = "cliente")
    private List<Venta> lVentas;
}
