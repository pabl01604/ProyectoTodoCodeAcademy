package com.bazar.Bazar.Services;

import com.bazar.Bazar.DTOs.Request.DetalleVentaDTO.DetalleVentaCreateDTO;
import com.bazar.Bazar.DTOs.Request.VentaDTOs.VentaCreateDTO;
import com.bazar.Bazar.Models.DetalleVenta;
import com.bazar.Bazar.Models.Venta;

import java.util.List;

public interface IDetalleVentaService {

    public List<DetalleVenta> procesarDetalles(List<DetalleVentaCreateDTO> detalles, Venta ventas);
    public boolean verificarStock(List<DetalleVentaCreateDTO> detalles);
}
