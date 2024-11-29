package com.proyectohotel.app.service;

import com.proyectohotel.app.entity.Cliente;
import com.proyectohotel.app.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente authenticate(String nombre, String documento) {
        return clienteRepository.findByNombre(nombre)
                .filter(cliente -> cliente.getDocumento().equals(documento))
                .orElse(null);
    }
}

