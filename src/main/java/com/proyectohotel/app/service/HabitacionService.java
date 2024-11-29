package com.proyectohotel.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyectohotel.app.entity.Habitacion;
import com.proyectohotel.app.repository.HabitacionRepository;


@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Habitacion> findAll() {
        return habitacionRepository.findAll();
    }

    public Habitacion findById(Long id) {
        return habitacionRepository.findById(id).orElse(null);
    }

    public Habitacion save(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public void delete(Long id) {
        habitacionRepository.deleteById(id);
    }
}
