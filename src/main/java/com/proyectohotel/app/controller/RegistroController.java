package com.proyectohotel.app.controller;

import com.proyectohotel.app.entity.Cliente;
import com.proyectohotel.app.entity.Habitacion;
import com.proyectohotel.app.entity.Registro;
import com.proyectohotel.app.service.ClienteService;
import com.proyectohotel.app.service.HabitacionService;
import com.proyectohotel.app.service.RegistroService;
import jakarta.validation.Valid;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/registros")
public class RegistroController {
    private static final Logger logger = LoggerFactory.getLogger(RegistroController.class);

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private HabitacionService habitacionService;
    
    @Autowired
    private RegistroService registroService;

    @GetMapping
    public String listRegistros(Model model) {
        logger.info("Listing all registros");
        model.addAttribute("registros", registroService.findAll());
        return "vistas/registro/registros";
    }


	@GetMapping("/new")
	public String showNewForm(Model model) {
	    logger.info("Preparing new registro form");
	
	    // Fetch the list of clients and rooms
	    List<Cliente> clientes = clienteService.findAll();
	    List<Habitacion> habitaciones = habitacionService.findAll();
	
	    // Add the lists to the model
	    model.addAttribute("registro", new Registro());
	    model.addAttribute("clientes", clientes);
	    model.addAttribute("habitaciones", habitaciones);
	
	    return "vistas/registro/registro_form"; // Path to the Thymeleaf view
	}
	
	
    @PostMapping
    public String saveRegistro(
            @Valid @ModelAttribute("registro") Registro registro, 
            BindingResult bindingResult, 
            RedirectAttributes redirectAttributes, 
            Model model) {
        
        if (bindingResult.hasErrors()) {
            logger.warn("Validation errors in registro form");
            return "vistas/registro/registro_form";
        }

        try {
            registroService.save(registro);
            logger.info("Registro saved successfully: {}", registro);
            redirectAttributes.addFlashAttribute("successMessage", "Registro guardado exitosamente");
            return "redirect:/registros";
        } catch (Exception e) {
            logger.error("Error saving registro", e);
            model.addAttribute("errorMessage", "No se pudo guardar el registro");
            return "vistas/registro/registro_form";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        try {
            Registro registro = registroService.findById(id);
            if (registro == null) {
                logger.warn("Attempt to edit non-existent registro with id: {}", id);
                return "redirect:/registros?error=notfound";
            }
            model.addAttribute("registro", registro);
            return "vistas/registro/registro_form";
        } catch (Exception e) {
            logger.error("Error retrieving registro for edit", e);
            return "redirect:/registros?error=system";
        }
    }


    @GetMapping("/delete/{id}")
    public String deleteRegistro(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {
        try {
            // Perform the actual delete operation
            registroService.delete(id);
            logger.info("Registro deleted: {}", id);
            redirectAttributes.addFlashAttribute("successMessage", "Registro eliminado exitosamente");
        } catch (Exception e) {
            logger.error("Error deleting registro with id {}", id, e);
            redirectAttributes.addFlashAttribute("errorMessage", "No se pudo eliminar el registro");
        }
        return "redirect:/registros";
    }

}

