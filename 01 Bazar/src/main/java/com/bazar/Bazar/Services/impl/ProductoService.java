package com.bazar.Bazar.Services.impl;

import com.bazar.Bazar.DTOs.Request.ProductoDTOs.ProductoCreateDTO;
import com.bazar.Bazar.DTOs.Request.ProductoDTOs.ProductoUpdateDTO;
import com.bazar.Bazar.DTOs.Response.ProductoResponseDTO;
import com.bazar.Bazar.Models.Producto;
import com.bazar.Bazar.Repositories.IProductoRepository;
import com.bazar.Bazar.Services.IProductoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductoService implements IProductoService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IProductoRepository repoProducto;

    @Override
    public ProductoResponseDTO createProducto(ProductoCreateDTO producto) {
        Producto productoModel = modelMapper.map(producto,Producto.class);
        Producto productoBD = repoProducto.save(productoModel);
        ProductoResponseDTO productoResponseDTO = modelMapper.map(productoBD,ProductoResponseDTO.class);
        return productoResponseDTO;
    }

    @Override
    public List<ProductoResponseDTO> readAllProductos() {
        List<Producto> lProducto = repoProducto.findAll();
        List<ProductoResponseDTO> lProductoResponse = new ArrayList();
        for(Producto pp : lProducto){
            ProductoResponseDTO productoResponseDTO = modelMapper.map(pp,ProductoResponseDTO.class);
            lProductoResponse.add(productoResponseDTO);
        }
        return lProductoResponse;
    }

    @Override
    public ProductoResponseDTO readProductoById(Long id) {
        Producto pp = repoProducto.findById(id).orElseThrow(()-> new RuntimeException("Producto no encontrado"));
        ProductoResponseDTO productoResponseDTO = modelMapper.map(pp,ProductoResponseDTO.class);
        return productoResponseDTO;
    }

    @Override
    public ProductoResponseDTO updateProducto(Long id, ProductoUpdateDTO productoUpdateDTO) {
        Producto pp = repoProducto.findById(id).orElseThrow(()-> new RuntimeException("Producto no encontrado"));
        if(productoUpdateDTO.getNombre()!=null){pp.setNombre(productoUpdateDTO.getNombre());}
        if(productoUpdateDTO.getMarca()!=null){pp.setMarca(productoUpdateDTO.getMarca());}
        if(productoUpdateDTO.getPrecio()!=null){pp.setPrecio(productoUpdateDTO.getPrecio());}
        if(productoUpdateDTO.getStock()!=null){pp.setStock(productoUpdateDTO.getStock());}
        Producto productoBD = repoProducto.save(pp);
        ProductoResponseDTO productoResponseDTO = modelMapper.map(productoBD,ProductoResponseDTO.class);
        return productoResponseDTO;
    }

    @Override
    public void deleteProducto(Long id) {
        repoProducto.deleteById(id);
    }

    @Override
    public  List<ProductoResponseDTO> readProductoFaltaStock() {
        List<ProductoResponseDTO> lProductos = readAllProductos();
        for(ProductoResponseDTO productoResponseDTO: lProductos){
            if(productoResponseDTO.getStock()>5){
                lProductos.remove(productoResponseDTO);
            }
        }
        return lProductos;
    }
}
