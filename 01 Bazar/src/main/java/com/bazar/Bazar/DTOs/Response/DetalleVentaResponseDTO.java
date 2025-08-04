package com.bazar.Bazar.DTOs.Response;

import com.bazar.Bazar.Models.Producto;
import com.bazar.Bazar.Models.Venta;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaResponseDTO {
    private Long id;
    private Long venta;
    private int nroDetalle;
    private String producto;
    private  Integer cantidad;
    private  double subtotal;
}
