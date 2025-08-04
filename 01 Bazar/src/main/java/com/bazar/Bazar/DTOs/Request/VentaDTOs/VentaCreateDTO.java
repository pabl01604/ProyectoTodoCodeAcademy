package com.bazar.Bazar.DTOs.Request.VentaDTOs;

import com.bazar.Bazar.DTOs.Request.DetalleVentaDTO.DetalleVentaCreateDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaCreateDTO {
    private LocalDate fechaVenta;
    private double importeNeto;
    private double descuento;
    private  double otrosImpuetos;
    private double total;
    private Long cliente;
    private List<DetalleVentaCreateDTO> detalles;
}
