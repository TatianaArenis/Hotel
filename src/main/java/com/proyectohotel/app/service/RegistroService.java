package com.proyectohotel.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectohotel.app.entity.Registro;
import com.proyectohotel.app.repository.RegistroRepository;

import java.util.List;

@Service
public class RegistroService {

    @Autowired
    private RegistroRepository registroRepository;

    public List<Registro> findAll() {
        return registroRepository.findAll();
    }

    public Registro findById(Long id) {
        return registroRepository.findById(id).orElse(null);
    }

    public Registro save(Registro registro) {
        return registroRepository.save(registro);
    }

    public void delete(Long id) {
    	registroRepository.deleteById(id);
    }
}
