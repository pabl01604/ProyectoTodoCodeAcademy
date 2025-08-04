package com.bazar.Bazar.Controllers;

import com.bazar.Bazar.DTOs.Request.VentaDTOs.VentaCreateDTO;
import com.bazar.Bazar.DTOs.Request.VentaDTOs.VentaUpdateDTO;
import com.bazar.Bazar.DTOs.Response.ProductoResponseDTO;
import com.bazar.Bazar.DTOs.Response.RecordVentaResponseDTO;
import com.bazar.Bazar.DTOs.Response.VentaResponseDTO;
import com.bazar.Bazar.Services.IVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class VentaController {
    @Autowired
    private IVentaService service;

    @PostMapping("/venta/crear")
    public ResponseEntity<VentaResponseDTO> crearVenta(@RequestBody VentaCreateDTO venta){
        return new ResponseEntity<>(service.createVenta(venta), HttpStatus.CREATED);
    }

    @GetMapping("/ventas")
    public ResponseEntity<List<VentaResponseDTO>> traerVenta(){
        return new ResponseEntity<>( service.readAllVentas(),HttpStatus.FOUND);
    }

    @GetMapping("/venta/{id}")
    public ResponseEntity<VentaResponseDTO> traerUnaVenta(@PathVariable Long  id){
        return new ResponseEntity<>(service.readVentasById(id),HttpStatus.FOUND);
    }

    @GetMapping("/venta/productos/{id}")
    public ResponseEntity<List<ProductoResponseDTO>> productosPorVenta(@PathVariable Long id){
        return new ResponseEntity<>(service.readProductoByVenta(id),HttpStatus.FOUND);
    }

    @GetMapping("/venta/obtenerTotal/{fecha}")
    public double montoVenta(@PathVariable LocalDate fecha){
        return service.obtenerVentasTotalesDelDia(fecha);
    }

    @GetMapping("/venta/obtenerRecord")
    public RecordVentaResponseDTO recordVenta(){
        return service.obtenerRecordVentaGeneral();
    }

    @PutMapping("/venta/editar/{id}")
    public  ResponseEntity<VentaResponseDTO> editarVenta(@PathVariable Long id, @RequestBody VentaUpdateDTO ventaUpdateDTO){
        return new ResponseEntity<>(service.updateVentas(id,ventaUpdateDTO),HttpStatus.OK);
    }

    @DeleteMapping("/venta/eliminar/{id}")
    public ResponseEntity<String> eliminarVenta(@PathVariable Long id){
        service.deleteVentas(id);
        return new ResponseEntity<>("Venta eliminada", HttpStatus.OK);
    }


}
