package com.bazar.Bazar.Services;


import com.bazar.Bazar.DTOs.Request.ProductoDTOs.ProductoCreateDTO;
import com.bazar.Bazar.DTOs.Request.ProductoDTOs.ProductoUpdateDTO;
import com.bazar.Bazar.DTOs.Response.ProductoResponseDTO;

import java.util.List;

public interface IProductoService {
    public ProductoResponseDTO createProducto(ProductoCreateDTO producto);
    public List<ProductoResponseDTO> readAllProductos();
    public ProductoResponseDTO readProductoById(Long id);
    public ProductoResponseDTO updateProducto(Long id, ProductoUpdateDTO productoUpdateDTO);
    public void deleteProducto(Long id);
    public  List<ProductoResponseDTO> readProductoFaltaStock();
}
