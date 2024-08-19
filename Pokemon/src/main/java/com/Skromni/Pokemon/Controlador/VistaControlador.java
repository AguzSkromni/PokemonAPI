package com.Skromni.Pokemon.Controlador;

import com.Skromni.Pokemon.Modelo.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class VistaControlador {

    @Setter
    @Getter
    private String pokemonNames;

    @Getter
    @Setter
    private Integer idBusquedaGuardar;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/")
    public String mostrarIndex(Model model){
        model.addAttribute("page", "home");
        return "index";
    }

    @GetMapping("/listar-entrenadores")
    public String listarEntrenadores(Model model) {
        String url = "http://localhost:8080/api/entrenadores";
        Entrenador[] entrenadores = restTemplate.getForObject(url, Entrenador[].class);

        for (Entrenador entrenador : entrenadores) {
            String pokemonNames = entrenador.getPokemones().stream()
                    .map(Pokemon::getNombrePokemon)
                    .collect(Collectors.joining(", "));
            entrenador.setPokemonNames(pokemonNames);
        }

        model.addAttribute("entrenadores", entrenadores);
        model.addAttribute("page", "listar-entrenadores");
        return "listar-entrenadores";
    }

    @GetMapping("/editar-entrenador/{id}")
    public String editarEntrenador(@PathVariable("id") Integer id, Model model) {
        String url = "http://localhost:8080/api/entrenadores/" + id;
        Entrenador entrenador = restTemplate.getForObject(url, Entrenador.class);
        model.addAttribute("entrenador", entrenador);
        return "editar-entrenador";
    }

    @PostMapping("/editar-entrenador")
    public String actualizarEntrenador(@ModelAttribute Entrenador entrenador) {
        String url = "http://localhost:8080/api/entrenadores/" + entrenador.getIdEntrenador();
        restTemplate.put(url, entrenador);
        return "redirect:/listar-entrenadores";
    }

    @PostMapping("/eliminar-entrenador")
    public String eliminarEntrenador(@RequestParam("id") Integer id) {
        String url = "http://localhost:8080/api/entrenadores/" + id;
        restTemplate.delete(url);
        return "redirect:/listar-entrenadores";
    }


    // Método para mostrar la vista con el formulario de búsqueda
    @GetMapping("/busqueda-entrenador")
    public String mostrarBusquedaEntrenador(Model model) {
        model.addAttribute("page", "busqueda-entrenador");
        return "busqueda-entrenador"; // Renderiza la vista busqueda-entrenador.html
    }

    // Método para manejar la validación y redirección
    @PostMapping("/buscar-entrenador")
    public String buscarEntrenadorPorId(
            @RequestParam("id") Integer id, Model model, RedirectAttributes redirectAttributes) {

        String url = "http://localhost:8080/api/entrenadores/" + id;

        try {
            // Intentar obtener el entrenador de la API
            Entrenador entrenador = restTemplate.getForObject(url, Entrenador.class);
            if (entrenador != null) {
                // Si el entrenador existe, redirige a la vista de edición
                return "redirect:/editar-entrenador/" + id;
            }
        } catch (Exception e) {
            // Si ocurre un error (por ejemplo, el entrenador no existe), captura la excepción
        }

        // Si el entrenador no existe, agregar un mensaje de error
        model.addAttribute("error", "ID no encontrado. Inténtalo nuevamente.");
        return "busqueda-entrenador"; // Mantiene al usuario en la misma página
    }
}
