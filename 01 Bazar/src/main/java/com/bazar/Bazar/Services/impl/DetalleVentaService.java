package com.bazar.Bazar.Services.impl;

import com.bazar.Bazar.DTOs.Request.DetalleVentaDTO.DetalleVentaCreateDTO;
import com.bazar.Bazar.DTOs.Request.VentaDTOs.VentaCreateDTO;
import com.bazar.Bazar.Models.DetalleVenta;
import com.bazar.Bazar.Models.Producto;
import com.bazar.Bazar.Models.Venta;
import com.bazar.Bazar.Repositories.IDetalleRepository;
import com.bazar.Bazar.Repositories.IProductoRepository;
import com.bazar.Bazar.Services.IDetalleVentaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DetalleVentaService implements IDetalleVentaService {
    //Responsable de verificar Stock y crear los detalles Venta validos
    @Autowired
    private IProductoRepository repoProducto;

    @Autowired
    private IDetalleRepository repoDetalle;
    @Override
    public List<DetalleVenta> procesarDetalles(List<DetalleVentaCreateDTO> detalles, Venta venta) {
        List<DetalleVenta> detallesModels= new ArrayList<>();
        int contador = 1;
        for(DetalleVentaCreateDTO dto: detalles){
            Producto pro =repoProducto.findById(dto.getProducto())
                    .orElseThrow(()-> new RuntimeException("Producto no encontrado"));
            if(pro.getStock()>= dto.getCantidad()){
                pro.setStock(pro.getStock()- dto.getCantidad());
                repoProducto.save(pro);
                DetalleVenta det = new DetalleVenta();
                det.setProducto(pro);
                det.setCantidad(dto.getCantidad());
                double subtotal = pro.getPrecio()* dto.getCantidad();
                det.setSubtotal(subtotal);
                det.setNroDetalle(contador);
                det.setVenta(venta);
                repoDetalle.save(det);
                detallesModels.add(det);
                contador++;
            }
            else{
                System.out.println("No hay stock suficiente de "+pro.getNombre());
            }
        }

        return detallesModels;
    }

    public boolean verificarStock(List<DetalleVentaCreateDTO> detalles){
        int productosDisponibles = 0 ;
        for(DetalleVentaCreateDTO dto: detalles){
            Producto pro =repoProducto.findById(dto.getProducto())
                    .orElseThrow(()-> new RuntimeException("Producto no encontrado"));
            if(pro.getStock()>= dto.getCantidad()){
                productosDisponibles++;
            }
        }
        if(productosDisponibles!=0){
            return true;
        }
        else{
            return false;
        }
    }
}
