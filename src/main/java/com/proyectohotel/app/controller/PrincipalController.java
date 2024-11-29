package com.proyectohotel.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PrincipalController {

    @GetMapping("/")
    public String mostrarPaginaPrincipal() {
        return "vistas/paginaAterrizaje/home";
    }
    @GetMapping("/contactanos")
    public String mostrarContacto() {
        return "vistas/paginaAterrizaje/contactanos";
    }
    @GetMapping("/login")
    public String login() {
        return "vistas/paginaAterrizaje/login";
    }

    @PostMapping("/login")
    public String user(@RequestParam("nombre") String nombre,
                       @RequestParam("documento") String documento,
                       RedirectAttributes redirectAttributes) {

        // Validación de credenciales
        if ("admin".equals(nombre) && "123".equals(documento)) {
            return "vistas/paginaAterrizaje/admin"; // Redirige a index si las credenciales son correctas
        }else if ("cliente".equals(nombre) && "123".equals(documento)) {
            return "vistas/paginaAterrizaje/cliente"; // Redirige a index si las credenciales son correctas
        }else if ("resepcion".equals(nombre) && "123".equals(documento)) {
            return "vistas/paginaAterrizaje/resepcion"; // Redirige a index si las credenciales son correctas
        } else {
            // Agrega un mensaje de error y redirige a la página de login
            redirectAttributes.addFlashAttribute("error", "Nombre o documento incorrectos.");
            return "redirect:/login"; // Redirige a la página de login
        }
    }
}
