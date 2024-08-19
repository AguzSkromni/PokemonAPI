package com.Skromni.Pokemon.Controlador;

import com.Skromni.Pokemon.Excepcion.RecursosNoEncontradosExcepcion;
import com.Skromni.Pokemon.Modelo.Entrenador;
import com.Skromni.Pokemon.Modelo.Pokemon;
import com.Skromni.Pokemon.Servicio.Entrenador.IEntrenadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entrenadores")
public class EntrenadorControlador {

    @Autowired
    private IEntrenadorServicio entrenadorServicio;

    @GetMapping
    public List<Entrenador> obtenerEntrenadores() {
        return entrenadorServicio.listadoEntrenadores();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> obtenerEntrenadorPorId(@PathVariable Integer id) {
        Entrenador entrenadorPorId = entrenadorServicio.buscarEntrenadorPorId(id);
        if (entrenadorPorId != null) {
            return ResponseEntity.ok(entrenadorPorId);
        } else {
            throw new RecursosNoEncontradosExcepcion("No encontrado ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Entrenador> agregarEntrenador(@RequestBody Entrenador nuevoEntrenador) {
        Entrenador entrenadorAGuardar = entrenadorServicio.guardarEntrenador(nuevoEntrenador);
        return ResponseEntity.status(HttpStatus.CREATED).body(entrenadorAGuardar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> actualizarEntrenador(
            @PathVariable Integer id, @RequestBody Entrenador entrenadorDetalles
    ) {
        // Verificar si el ID del entrenador es el mismo que el ID de la URL
        if (!id.equals(entrenadorDetalles.getIdEntrenador())) {
            throw new IllegalArgumentException("ID del entrenador en la URL no coincide con el ID en el cuerpo de la solicitud.");
        }

        Entrenador entrenadorActualizar = entrenadorServicio.buscarEntrenadorPorId(id);
        if (entrenadorActualizar != null) {
            entrenadorActualizar.setNombreEntrenador(entrenadorDetalles.getNombreEntrenador());
            entrenadorActualizar.setEdadEntrenador(entrenadorDetalles.getEdadEntrenador());
            entrenadorActualizar.setCiudadEntrenador(entrenadorDetalles.getCiudadEntrenador());

            // Manejo de la lista para los Pokémon asociados
            entrenadorActualizar.getPokemones().clear();
            for (Pokemon pokemon : entrenadorDetalles.getPokemones()) {
                if (pokemon.getIdPokemon() != null) { // Verifica que el ID no sea nulo
                    pokemon.setEntrenador(entrenadorActualizar);
                    entrenadorActualizar.getPokemones().add(pokemon);
                }
            }

            // Guardar los cambios
            entrenadorServicio.guardarEntrenador(entrenadorActualizar);
            return ResponseEntity.ok(entrenadorActualizar);
        } else {
            throw new RecursosNoEncontradosExcepcion("No se puede actualizar entrenador con ID " + id + " porque no fue encontrado");
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEntrenador(@PathVariable Integer id) {
        Entrenador entrenador = entrenadorServicio.buscarEntrenadorPorId(id);
        if (entrenador != null) {
            entrenadorServicio.eliminarEntrenador(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new RecursosNoEncontradosExcepcion("No se puede eliminar entrenador con ID " + id + " porque no fue encontrado");
        }
    }
}

