package com.bazar.Bazar.Services;

import com.bazar.Bazar.DTOs.Request.ClienteDTOs.ClienteCreateDTO;
import com.bazar.Bazar.DTOs.Request.ClienteDTOs.ClienteUpdateDTO;
import com.bazar.Bazar.DTOs.Response.ClienteResponseDTO;

import java.util.List;

public interface IClienteService {
    public ClienteResponseDTO createCliente(ClienteCreateDTO cliente);
    public List<ClienteResponseDTO> readAllClientes();
    public ClienteResponseDTO readClienteById(Long id);
    public ClienteResponseDTO updateCliente(Long id, ClienteUpdateDTO clienteActualizar);
    public void deleteCliente(Long id);
}
