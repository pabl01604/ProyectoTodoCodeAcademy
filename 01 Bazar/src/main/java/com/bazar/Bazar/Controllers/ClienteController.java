package com.bazar.Bazar.Controllers;

import com.bazar.Bazar.DTOs.Request.ClienteDTOs.ClienteCreateDTO;
import com.bazar.Bazar.DTOs.Request.ClienteDTOs.ClienteUpdateDTO;
import com.bazar.Bazar.DTOs.Response.ClienteResponseDTO;
import com.bazar.Bazar.Models.Cliente;
import com.bazar.Bazar.Services.IClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {
    @Autowired
    private IClienteService service;

    @PostMapping("/cliente/crear")
    public ResponseEntity<ClienteResponseDTO> crearCliente(@RequestBody ClienteCreateDTO cliente){
        return new ResponseEntity<>(service.createCliente(cliente), HttpStatus.CREATED);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteResponseDTO>> traerClientes(){
        return new ResponseEntity<>( service.readAllClientes(),HttpStatus.FOUND);
    }

    @GetMapping("/cliente/{id}")
    public ResponseEntity<ClienteResponseDTO> traerUnCliente(@PathVariable Long  id){
        return new ResponseEntity<>(service.readClienteById(id),HttpStatus.FOUND);
    }

    @PutMapping("/cliente/editar/{id}")
    public  ResponseEntity<ClienteResponseDTO> editarCliente(@PathVariable Long id, @RequestBody ClienteUpdateDTO clienteUpdateDTO){
        return new ResponseEntity<>(service.updateCliente(id,clienteUpdateDTO),HttpStatus.OK);
    }

    @DeleteMapping("/cliente/eliminar/{id}")
    public ResponseEntity<String> eliminarCliente(@PathVariable Long id){
        service.deleteCliente(id);
        return new ResponseEntity<>("Cliente eliminado", HttpStatus.OK);
    }
}
