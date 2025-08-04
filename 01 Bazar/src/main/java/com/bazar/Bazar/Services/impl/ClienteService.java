package com.bazar.Bazar.Services.impl;

import com.bazar.Bazar.DTOs.Request.ClienteDTOs.ClienteCreateDTO;
import com.bazar.Bazar.DTOs.Request.ClienteDTOs.ClienteUpdateDTO;
import com.bazar.Bazar.DTOs.Response.ClienteResponseDTO;
import com.bazar.Bazar.Models.Cliente;
import com.bazar.Bazar.Repositories.IClienteRepository;
import com.bazar.Bazar.Services.IClienteService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ClienteService implements IClienteService {
    @Autowired
    private IClienteRepository repoCliente;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ClienteResponseDTO createCliente(ClienteCreateDTO cliente) {
        Cliente clienteModel = modelMapper.map(cliente,Cliente.class);
        Cliente clienteBD = repoCliente.save(clienteModel);
        ClienteResponseDTO clienteResponseDTO = modelMapper.map(clienteBD,ClienteResponseDTO.class);
        return clienteResponseDTO;
    }
    @Override
    public List<ClienteResponseDTO> readAllClientes() {
        List<Cliente> lClientes = repoCliente.findAll();
        List<ClienteResponseDTO> lClienteResponse = new ArrayList();
        for(Cliente cc : lClientes){
            ClienteResponseDTO clienteRespuesta = modelMapper.map(cc,ClienteResponseDTO.class);
            lClienteResponse.add(clienteRespuesta);
        }
        return lClienteResponse;
    }
    @Override
    public ClienteResponseDTO readClienteById(Long id) {
        Cliente cc = repoCliente.findById(id).orElseThrow(()-> new RuntimeException("Cliente no encontrado"));
        ClienteResponseDTO clienteRespuesta = modelMapper.map(cc,ClienteResponseDTO.class);
        return clienteRespuesta;
    }
    @Override
    public ClienteResponseDTO updateCliente(Long id, ClienteUpdateDTO clienteActualizar) {
        Cliente cliente = repoCliente.findById(id).orElseThrow(()->new RuntimeException( "Cliente no encontrado"));
        if(clienteActualizar.getNombre()!=null){cliente.setNombre(clienteActualizar.getNombre());}
        if(clienteActualizar.getApellido()!=null){cliente.setApellido(clienteActualizar.getApellido());}
        if(clienteActualizar.getDni()!=null){cliente.setDni(clienteActualizar.getDni());}
        Cliente clienteBD = repoCliente.save(cliente);
        ClienteResponseDTO clienteResponseDTO = modelMapper.map(clienteBD,ClienteResponseDTO.class);
        return clienteResponseDTO;
    }
    @Override
    public void deleteCliente(Long id) {
        repoCliente.deleteById(id);
    }
}
