package com.bazar.Bazar.Factory;

import com.bazar.Bazar.DTOs.Request.VentaDTOs.VentaCreateDTO;
import com.bazar.Bazar.DTOs.Request.VentaDTOs.VentaUpdateDTO;
import com.bazar.Bazar.Models.Cliente;
import com.bazar.Bazar.Models.DetalleVenta;
import com.bazar.Bazar.Models.Venta;
import com.bazar.Bazar.Repositories.IClienteRepository;
import com.bazar.Bazar.Services.IDetalleVentaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class VentasFactory {
    //Responsable de construir una venta Valida a partir delVentaCreateDTO
    @Autowired
    private IClienteRepository repoCliente;

    @Autowired
    private ModelMapper modelMapper;

    public Venta crearVentaDesdeDTO(VentaCreateDTO dto){
            Venta venta = new Venta();
            if(dto.getCliente()!=null){
                Cliente cliente = repoCliente.findById(dto.getCliente())
                        .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
                venta.setCliente(cliente);
            }
            if(dto.getFechaVenta()!=null){
                venta.setFechaVenta(dto.getFechaVenta());
            }
            return venta;
    }

    public Venta editarVentaDesdeDTO(Venta model, VentaUpdateDTO ventaUpdateDTO) {
        if(ventaUpdateDTO.getCliente()!=null){
            Cliente cliente = repoCliente.findById(ventaUpdateDTO.getCliente())
                    .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
            model.setCliente(cliente);
        }
        if(ventaUpdateDTO.getFechaVenta()!=null){
            model.setFechaVenta(ventaUpdateDTO.getFechaVenta());
        }
        return model;
    }

    public double calcularTotal(List<DetalleVenta> detalles) {
        double total =0.0;
        for(DetalleVenta det: detalles){
            total = total+det.getSubtotal();
        }
        return total;
    }


}
