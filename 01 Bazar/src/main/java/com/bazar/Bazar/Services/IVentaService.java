package com.bazar.Bazar.Services;



import com.bazar.Bazar.DTOs.Request.VentaDTOs.VentaCreateDTO;
import com.bazar.Bazar.DTOs.Request.VentaDTOs.VentaUpdateDTO;
import com.bazar.Bazar.DTOs.Response.ProductoResponseDTO;
import com.bazar.Bazar.DTOs.Response.RecordVentaResponseDTO;
import com.bazar.Bazar.DTOs.Response.VentaResponseDTO;
import com.bazar.Bazar.Models.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentaService {
    public VentaResponseDTO createVenta(VentaCreateDTO venta);
    public List<VentaResponseDTO> readAllVentas();
    public VentaResponseDTO readVentasById(Long id);
    public VentaResponseDTO updateVentas(Long id, VentaUpdateDTO clienteActualizar);
    public void deleteVentas(Long id);
    public List<ProductoResponseDTO> readProductoByVenta(Long id);
    public double obtenerVentasTotalesDelDia(LocalDate fecha);
    public RecordVentaResponseDTO obtenerRecordVentaGeneral();
}
