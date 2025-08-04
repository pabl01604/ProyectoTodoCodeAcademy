package com.bazar.Bazar.Services.impl;

import com.bazar.Bazar.DTOs.Request.DetalleVentaDTO.DetalleVentaCreateDTO;
import com.bazar.Bazar.DTOs.Request.VentaDTOs.VentaCreateDTO;
import com.bazar.Bazar.DTOs.Request.VentaDTOs.VentaUpdateDTO;
import com.bazar.Bazar.DTOs.Response.DetalleVentaResponseDTO;
import com.bazar.Bazar.DTOs.Response.ProductoResponseDTO;
import com.bazar.Bazar.DTOs.Response.RecordVentaResponseDTO;
import com.bazar.Bazar.DTOs.Response.VentaResponseDTO;
import com.bazar.Bazar.Factory.VentasFactory;
import com.bazar.Bazar.Models.Cliente;
import com.bazar.Bazar.Models.DetalleVenta;
import com.bazar.Bazar.Models.Producto;
import com.bazar.Bazar.Models.Venta;
import com.bazar.Bazar.Repositories.IClienteRepository;
import com.bazar.Bazar.Repositories.IVentaRepository;
import com.bazar.Bazar.Services.IDetalleVentaService;
import com.bazar.Bazar.Services.IVentaService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
@Service
public class VentaService implements IVentaService {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private IVentaRepository repoVenta;
    @Autowired
    private VentasFactory factory;
    @Autowired
    private IDetalleVentaService detalleVentaService;


    @Override
    @Transactional
    public VentaResponseDTO createVenta(VentaCreateDTO venta) {
        VentaResponseDTO ventaResponseDTO =null;
        if(venta.getDetalles()!=null){
            if(detalleVentaService.verificarStock(venta.getDetalles())){
                Venta ventaModel = factory.crearVentaDesdeDTO(venta);
                Venta ventaBD = repoVenta.save(ventaModel);
                ventaResponseDTO = getVentaResponseDTO(venta, ventaBD);
            }
            else{
                throw new RuntimeException("No hay stock de ninguno de los productos detallados");
            }
        }
        return ventaResponseDTO;
    }

    private VentaResponseDTO getVentaResponseDTO(VentaCreateDTO venta, Venta ventaBD) {
        VentaResponseDTO ventaResponseDTO;
        List<DetalleVenta> detalles = detalleVentaService.procesarDetalles(venta.getDetalles(), ventaBD);
        if (detalles.isEmpty()) {
            throw new RuntimeException("No hay stock para los productos seleccionados");
        }
        else{
            if(ventaBD.getLDetalle().isEmpty()){
                ventaBD.setLDetalle(detalles);
            }
            else{
                for(DetalleVenta detalleVenta: detalles){
                    ventaBD.getLDetalle().add(detalleVenta);
                }
            }
            ventaBD.setImporteNeto(factory.calcularTotal(ventaBD.getLDetalle()));
            ventaBD.setOtrosImpuetos(0.0);
            ventaBD.setDescuento(0.0);
            ventaBD.setIva(ventaBD.getImporteNeto()*0.21);
            ventaBD.setTotal(ventaBD.getImporteNeto()+ ventaBD.getOtrosImpuetos()+ ventaBD.getIva()- ventaBD.getDescuento());
            repoVenta.save(ventaBD);
            ventaResponseDTO =crearVentaResponseDTO(ventaBD);
        }
        return ventaResponseDTO;
    }

    @Override
    public List<VentaResponseDTO> readAllVentas() {
        List<Venta> lVentasBd = repoVenta.findAll();
        List<VentaResponseDTO> lVentasResponse = new ArrayList();
        for(Venta venta: lVentasBd){
            VentaResponseDTO ventaResponseDTO = crearVentaResponseDTO(venta);
            lVentasResponse.add(ventaResponseDTO);
        }
        return lVentasResponse;
    }

    @Override
    public VentaResponseDTO readVentasById(Long id) {
        Venta ventaBd = repoVenta.findById(id).orElseThrow(()->new RuntimeException("Venta no encontrada"));
        VentaResponseDTO ventaResponseDTO = crearVentaResponseDTO(ventaBd);
        return ventaResponseDTO;
    }
    private VentaResponseDTO crearVentaResponseDTO(Venta venta){
        VentaResponseDTO ventaResponseDTO =new VentaResponseDTO();
        ventaResponseDTO.setIdVenta(venta.getIdVenta());
        ventaResponseDTO.setFechaVenta(venta.getFechaVenta());
        String cliente = venta.getCliente().getNombre()+" "+venta.getCliente().getApellido();
        ventaResponseDTO.setCliente(cliente);
        List<DetalleVentaResponseDTO> lDetalles = new ArrayList<>();
        for (DetalleVenta detalleVenta: venta.getLDetalle()){
            DetalleVentaResponseDTO detalleVentaResponseDTO = getDetalleVentaResponseDTO(detalleVenta);
            lDetalles.add(detalleVentaResponseDTO);
        }
        ventaResponseDTO.setLDetalle(lDetalles);
        ventaResponseDTO.setImporteNeto(venta.getImporteNeto());
        ventaResponseDTO.setDescuento(venta.getDescuento());
        ventaResponseDTO.setIva(venta.getIva());
        ventaResponseDTO.setOtrosImpuetos(venta.getOtrosImpuetos());
        ventaResponseDTO.setTotal(venta.getTotal());
        return ventaResponseDTO;
    }

    private static DetalleVentaResponseDTO getDetalleVentaResponseDTO(DetalleVenta detalleVenta) {
        DetalleVentaResponseDTO detalleVentaResponseDTO= new DetalleVentaResponseDTO();
        detalleVentaResponseDTO.setId(detalleVenta.getId());
        detalleVentaResponseDTO.setVenta(detalleVenta.getVenta().getIdVenta());
        detalleVentaResponseDTO.setNroDetalle(detalleVenta.getNroDetalle());
        detalleVentaResponseDTO.setProducto(detalleVenta.getProducto().getNombre());
        detalleVentaResponseDTO.setCantidad(detalleVenta.getCantidad());
        detalleVentaResponseDTO.setSubtotal(detalleVenta.getSubtotal());
        return detalleVentaResponseDTO;
    }


    @Override
    @Transactional
    public VentaResponseDTO updateVentas(Long id, VentaUpdateDTO ventaActualizar) {
        VentaResponseDTO ventaResponseDTO=null;
        Venta ventaEditable =  repoVenta.findById(id).orElseThrow(()->new RuntimeException("Venta no encontrada"));
        Venta ventaActualizada = factory.editarVentaDesdeDTO(ventaEditable,ventaActualizar);
        if(!ventaActualizar.getDetalles().isEmpty()){
            VentaCreateDTO ventaCreateDTO = modelMapper.map(ventaActualizar,VentaCreateDTO.class);
            ventaResponseDTO=getVentaResponseDTO(ventaCreateDTO, ventaActualizada);
        }
        else{
            ventaResponseDTO =new VentaResponseDTO();
            ventaResponseDTO.setIdVenta(ventaActualizada.getIdVenta());
            ventaResponseDTO.setFechaVenta(ventaActualizada.getFechaVenta());
            String cliente = ventaActualizada.getCliente().getNombre()+" "+ventaActualizada.getCliente().getApellido();
            ventaResponseDTO.setCliente(cliente);
        }
        return ventaResponseDTO;
    }

    @Override
    public void deleteVentas(Long id) {
        repoVenta.deleteById(id);
    }

    @Override
    public List<ProductoResponseDTO> readProductoByVenta(Long id) {
        List<Producto> lProductos = new ArrayList<>();
        List<ProductoResponseDTO> lProductoReponse = new ArrayList<>();
        Venta ventaBd = repoVenta.findById(id).orElseThrow(()->new RuntimeException("Venta no encontrada"));
        for(DetalleVenta detalle : ventaBd.getLDetalle()){
            lProductos.add(detalle.getProducto());
        }
        for(Producto producto: lProductos){
            ProductoResponseDTO response = modelMapper.map(producto, ProductoResponseDTO.class);
            lProductoReponse.add(response);
        }
        return lProductoReponse;
    }

    @Override
    public double obtenerVentasTotalesDelDia(LocalDate fecha) {
        double total = 0.0;
        List<VentaResponseDTO> lTodasLasVentas= readAllVentas();
        for(VentaResponseDTO venta: lTodasLasVentas){
            if(venta.getFechaVenta().isEqual(fecha
            )){
                total=total+venta.getTotal();
            }
        }
        return total;
    }

    @Override
    public RecordVentaResponseDTO obtenerRecordVentaGeneral() {
        Venta ventaRecord = null;
        List<Venta> lTodasLasVentas= repoVenta.findAll();
        boolean primera = true;
        for(Venta venta: lTodasLasVentas){
            if(primera==true){
                ventaRecord=venta;
                primera= false;
            }else if(venta.getTotal()>ventaRecord.getTotal()){
                ventaRecord = venta;
            }
        }
        RecordVentaResponseDTO recordVentaResponseDTO = new RecordVentaResponseDTO();
        recordVentaResponseDTO.setCodVenta(ventaRecord.getIdVenta());
        recordVentaResponseDTO.setTotal(ventaRecord.getTotal());
        //obtener cantidad articulos
        int articulos = 0;
        for(DetalleVenta detalleVenta: ventaRecord.getLDetalle()){
            articulos = detalleVenta.getCantidad()+articulos;
        }
        recordVentaResponseDTO.setCantProductos(articulos);
        VentaResponseDTO ventaResponseDTO= readVentasById(ventaRecord.getIdVenta());
        recordVentaResponseDTO.setNombreCliente(ventaResponseDTO.getCliente());
        return recordVentaResponseDTO;
    }
}
