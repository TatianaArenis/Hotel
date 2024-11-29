package com.proyectohotel.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.proyectohotel.app.entity.Habitacion;
import com.proyectohotel.app.service.HabitacionService;

@Controller
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping
    public String listHabitaciones(Model model) {
        model.addAttribute("habitaciones", habitacionService.findAll());
        return "vistas/habitacion/habitaciones"; // Ruta a la vista de lista de habitaciones en la carpeta 'vistas'
    }


    @GetMapping("/new")
    public String showNewForm(Model model) {
        model.addAttribute("habitacion", new Habitacion());
        return "vistas/habitacion/habitacion_form"; // Ruta a la vista del formulario de nueva habitación en 'vistas'
    }

    @PostMapping
    public String saveHabitacion(@ModelAttribute Habitacion habitacion) {
        habitacionService.save(habitacion);
        return "redirect:/habitaciones";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Habitacion habitacion = habitacionService.findById(id);
        model.addAttribute("habitacion", habitacion);
        return "vistas/habitacion/habitacion_form"; // Ruta a la vista del formulario de edición de habitación en 'vistas'
    }
    @GetMapping("/delete/{id}")
    public String deleteHabitacion(@PathVariable("id") Long id) {
        habitacionService.delete(id);
        return "redirect:/habitaciones";
    }
}
