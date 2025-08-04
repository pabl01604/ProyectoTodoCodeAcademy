package com.bazar.Bazar.DTOs.Response;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponseDTO {

    private Long idCliente;

    private String nombre;

    private String apellido;

    private String dni;

}
