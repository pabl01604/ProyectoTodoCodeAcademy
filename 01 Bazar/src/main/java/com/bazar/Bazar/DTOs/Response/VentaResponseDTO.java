package com.bazar.Bazar.DTOs.Response;

import com.bazar.Bazar.Models.Cliente;
import com.bazar.Bazar.Models.DetalleVenta;
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
public class VentaResponseDTO {
    private Long idVenta;
    private LocalDate fechaVenta;
    private String cliente;
    private List<DetalleVentaResponseDTO> lDetalle;
    private double importeNeto;
    private double descuento;
    private double iva;
    private  double otrosImpuetos;
    private double total;
}
