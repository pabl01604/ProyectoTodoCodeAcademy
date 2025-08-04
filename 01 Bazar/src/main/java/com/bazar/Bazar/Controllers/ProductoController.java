package com.bazar.Bazar.Controllers;

import com.bazar.Bazar.DTOs.Request.ProductoDTOs.ProductoCreateDTO;
import com.bazar.Bazar.DTOs.Request.ProductoDTOs.ProductoUpdateDTO;
import com.bazar.Bazar.DTOs.Response.ProductoResponseDTO;
import com.bazar.Bazar.Services.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {
    @Autowired
    private IProductoService service;

    @PostMapping("/producto/crear")
    public ResponseEntity<ProductoResponseDTO> crearProducto(@RequestBody ProductoCreateDTO producto){
        return new ResponseEntity<>(service.createProducto(producto), HttpStatus.CREATED);
    }

    @GetMapping("/productos")
    public ResponseEntity<List<ProductoResponseDTO>> traerProducto(){
        return new ResponseEntity<>( service.readAllProductos(),HttpStatus.FOUND);
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<ProductoResponseDTO> traerUnProducto(@PathVariable Long  id){
        return new ResponseEntity<>(service.readProductoById(id),HttpStatus.FOUND);
    }

    @PutMapping("/producto/editar/{id}")
    public  ResponseEntity<ProductoResponseDTO> editarProducto(@PathVariable Long id, @RequestBody ProductoUpdateDTO productoUpdateDTO){
        return new ResponseEntity<>(service.updateProducto(id,productoUpdateDTO),HttpStatus.OK);
    }

    @DeleteMapping("/producto/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id){
        service.deleteProducto(id);
        return new ResponseEntity<>("Producto eliminado", HttpStatus.OK);
    }
}
