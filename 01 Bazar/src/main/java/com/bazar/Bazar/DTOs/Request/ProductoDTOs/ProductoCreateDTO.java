package com.bazar.Bazar.DTOs.Request.ProductoDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoCreateDTO {
    private String nombre;
    private String marca;
    private Double precio;
    private Integer stock;
}
