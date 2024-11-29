package com.proyectohotel.app.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectohotel.app.entity.Cliente;
import com.proyectohotel.app.service.ClienteService;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listClientes(Model model) {
        logger.info("Listing all clientes");
        model.addAttribute("clientes", clienteService.findAll());
        return "vistas/cliente/clientes";
    }

    @GetMapping("/new")
    public String showNewForm(Model model) {
        logger.info("Preparing new cliente form");
        model.addAttribute("cliente", new Cliente());
        return "vistas/cliente/cliente_form";
    }

    @PostMapping
    public String createCliente(
            @Valid @ModelAttribute("cliente") Cliente cliente, 
            BindingResult bindingResult, 
            RedirectAttributes redirectAttributes, 
            Model model) {
        
        if (bindingResult.hasErrors()) {
            logger.warn("Validation errors in cliente form");
            return "vistas/cliente/cliente_form";
        }

        try {
            // Ensure this is a new cliente (no existing ID)
            cliente.setId(null);
            
            Cliente savedCliente = clienteService.save(cliente);
            logger.info("Cliente created successfully: {}", savedCliente);
            
            redirectAttributes.addFlashAttribute("successMessage", "Cliente creado exitosamente");
            return "redirect:/clientes";
        } catch (Exception e) {
            logger.error("Error creating cliente", e);
            model.addAttribute("errorMessage", "No se pudo crear el cliente");
            return "vistas/cliente/cliente_form";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        try {
            Cliente cliente = clienteService.findById(id);
            if (cliente == null) {
                logger.warn("Attempt to edit non-existent cliente with id: {}", id);
                return "redirect:/clientes?error=notfound";
            }
            model.addAttribute("cliente", cliente);
            return "vistas/cliente/cliente_form";
        } catch (Exception e) {
            logger.error("Error retrieving cliente for edit", e);
            return "redirect:/clientes?error=system";
        }
    }

    @PostMapping("/update")
    public String updateCliente(
            @Valid @ModelAttribute("cliente") Cliente cliente, 
            BindingResult bindingResult, 
            RedirectAttributes redirectAttributes, 
            Model model) {
        
        if (bindingResult.hasErrors()) {
            logger.warn("Validation errors in cliente update form");
            return "vistas/cliente/cliente_form";
        }

        try {
            // Ensure the cliente has an existing ID for update
            if (cliente.getId() == null) {
                throw new IllegalArgumentException("Cliente ID must not be null for update");
            }
            
            Cliente updatedCliente = clienteService.save(cliente);
            logger.info("Cliente updated successfully: {}", updatedCliente);
            
            redirectAttributes.addFlashAttribute("successMessage", "Cliente actualizado exitosamente");
            return "redirect:/clientes";
        } catch (Exception e) {
            logger.error("Error updating cliente", e);
            model.addAttribute("errorMessage", "No se pudo actualizar el cliente");
            return "vistas/cliente/cliente_form";
        }
    }

    @GetMapping("/details/{id}")
    public String showClientDetails(@PathVariable("id") Long id, Model model) {
        try {
            Cliente cliente = clienteService.findById(id);
            if (cliente == null) {
                logger.warn("Attempt to view non-existent cliente details with id: {}", id);
                return "redirect:/clientes?error=notfound";
            }
            model.addAttribute("cliente", cliente);
            return "vistas/cliente/cliente_details";
        } catch (Exception e) {
            logger.error("Error retrieving cliente details", e);
            return "redirect:/clientes?error=system";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteCliente(
            @PathVariable("id") Long id, 
            RedirectAttributes redirectAttributes) {
        try {
            clienteService.delete(id);
            logger.info("Cliente deleted successfully: {}", id);
            redirectAttributes.addFlashAttribute("successMessage", "Cliente eliminado exitosamente");
        } catch (Exception e) {
            logger.error("Error deleting cliente", e);
            redirectAttributes.addFlashAttribute("errorMessage", "No se pudo eliminar el cliente");
        }
        return "redirect:/clientes";
    }
}