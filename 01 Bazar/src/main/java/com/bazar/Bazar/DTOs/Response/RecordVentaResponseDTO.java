package com.bazar.Bazar.DTOs.Response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecordVentaResponseDTO {

    private Long codVenta;
    private double total;
    private Integer cantProductos;
    private String nombreCliente;
    private String apellidoCliente;

}
