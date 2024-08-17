package com.Skromni.Pokemon.Controlador;

import com.Skromni.Pokemon.Excepcion.RecursosNoEncontradosExcepcion;
import com.Skromni.Pokemon.Modelo.Pokemon;
import com.Skromni.Pokemon.Servicio.Pokemon.PokemonServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pokemones")
public class PokemonControlador {

    @Autowired
    private PokemonServicio pokemonServicio;

    @GetMapping
    public List<Pokemon> listadoPokemones(){
        List<Pokemon> listadoTodosPokemon = pokemonServicio.listarPokemones();
        return listadoTodosPokemon;
    }

    @RequestMapping("/{id}")
    public ResponseEntity<Pokemon> pokemonPorId(
            @PathVariable Integer id
    ){
        Pokemon pokemonObtenido = pokemonServicio.buscarPokemonPorId(id);
        if(pokemonObtenido != null){
            return ResponseEntity.ok(pokemonObtenido);
        }else{
            throw new RecursosNoEncontradosExcepcion("Pokemon no encontrado con ID" + id);
        }
    }

    @PostMapping
    public ResponseEntity<Pokemon> agregarPokemon(@RequestBody Pokemon nuevoPokemon){
        Pokemon pokemonAGuardar = pokemonServicio.guardarPokemon(nuevoPokemon);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPokemon);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> actualizarPokemon(
            @PathVariable Integer id, @RequestBody Pokemon pokemonDetalles
    ){
        Pokemon pokemonBusqueda = pokemonServicio.buscarPokemonPorId(id);
        if(pokemonBusqueda != null){
            pokemonBusqueda.setNivelPokemon(pokemonDetalles.getNivelPokemon());
            pokemonBusqueda.setNombrePokemon(pokemonDetalles.getNombrePokemon());
            pokemonBusqueda.setTipoPokemon(pokemonDetalles.getTipoPokemon());
            return ResponseEntity.ok(pokemonServicio.guardarPokemon(pokemonBusqueda));
        }else{
            throw new RecursosNoEncontradosExcepcion("Pokemon no encontrado con ID " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Pokemon> eliminarPokemon(
            @PathVariable Integer id
    ){
        Pokemon pokemonEliminar = pokemonServicio.buscarPokemonPorId(id);
        if(pokemonEliminar != null){
            pokemonServicio.eliminarPokemon(id);
            return ResponseEntity.noContent().build();
        }else{
            throw new RecursosNoEncontradosExcepcion("No hay pokemon con ID " + id + " para eliminar.");
        }
    }

}
