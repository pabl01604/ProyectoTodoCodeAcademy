package com.bazar.Bazar.DTOs.Response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductoResponseDTO {
    private Long idProducto;
    private String nombre;
    private String marca;
    private Double precio;
    private Integer stock;
}
