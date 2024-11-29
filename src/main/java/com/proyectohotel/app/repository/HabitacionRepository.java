package com.proyectohotel.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyectohotel.app.entity.Habitacion;
@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {

}
