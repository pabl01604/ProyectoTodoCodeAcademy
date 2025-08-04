package com.bazar.Bazar.DTOs.Request.ClienteDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteUpdateDTO {

    private String nombre;

    private String apellido;

    private String dni;
}
