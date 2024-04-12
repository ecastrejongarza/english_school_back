package com.escuela_ingles.modelo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    String username;
    String password;
    String nombre;
    String paterno;
    String materno;
    String telefono;
    String email;
    String fechaInscripcion;
    String role;
}