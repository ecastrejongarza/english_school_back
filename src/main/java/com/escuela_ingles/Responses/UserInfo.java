package com.escuela_ingles.Responses;

import lombok.Data;

@Data
public class UserInfo {

	String username;
    String password;
    String nombre;
    String paterno;
    String materno;
    String telefono;
    String email;
    String fechaInscripcion;
    String rol;
}
