package com.bazar.Bazar.DTOs.Request.DetalleVentaDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaUpdateDTO {


    private int nroDetalle;
    private Long producto;
    private  Integer cantidad;
    private  double subtotal;
}
