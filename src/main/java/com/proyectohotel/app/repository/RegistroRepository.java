package com.proyectohotel.app.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.proyectohotel.app.entity.Registro;




public interface RegistroRepository extends JpaRepository<Registro, Long> {

}
