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

    //Obtener todos los entrenadores
    @GetMapping
    public List<Entrenador> obtenerEntrenadores(){
        return entrenadorServicio.listadoEntrenadores();
    }

    //Obtener un entrenador por ID
    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> obtenerEntrenadorPorId(@PathVariable Integer id){
        Entrenador entrenadorPorId = entrenadorServicio.buscarEntrenadorPorId(id);
        if (entrenadorPorId != null){
            return ResponseEntity.ok(entrenadorPorId);
        }else{
            throw new RecursosNoEncontradosExcepcion("No encontrado ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Entrenador> agregarEntrenador(@RequestBody Entrenador nuevoEntrenador){
        Entrenador entrenadorAGuardar = entrenadorServicio.guardarEntrenador(nuevoEntrenador);
        return ResponseEntity.status(HttpStatus.CREATED).body(entrenadorAGuardar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> actualizarEntrenador(
            @PathVariable Integer id, @RequestBody Entrenador entrenadorDetalles
    ){
        Entrenador entrenadorActualizar = entrenadorServicio.buscarEntrenadorPorId(id);
        if(entrenadorActualizar != null){
            //Actualizamos datos entrenador
            entrenadorActualizar.setNombreEntrenador(entrenadorDetalles.getNombreEntrenador());
            entrenadorActualizar.setEdadEntrenador(entrenadorDetalles.getEdadEntrenador());
            entrenadorActualizar.setCiudadEntrenador(entrenadorDetalles.getCiudadEntrenador());

            //manejo de lista para pokemon asociados
            List<Pokemon> pokemonesActualizados = entrenadorDetalles.getPokemones();
            entrenadorActualizar.getPokemones().clear();
            entrenadorActualizar.getPokemones().addAll(pokemonesActualizados);

            return ResponseEntity.ok(entrenadorServicio.guardarEntrenador(entrenadorActualizar));
        }else{
            throw new RecursosNoEncontradosExcepcion("No se puede actualizar entrenado con ID " + id +
                    " porque no fue encontrado");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Entrenador> eliminarEntrenador(
            @PathVariable Integer id
    ){
        Entrenador entradorEliminar = entrenadorServicio.buscarEntrenadorPorId(id);
        if(entradorEliminar != null){
            entrenadorServicio.eliminarEntrenador(entradorEliminar.getIdEntrenador());
            return ResponseEntity.noContent().build();
        }else{
            throw new RecursosNoEncontradosExcepcion("No se puede eliminar entrenador con id: " + id +
                    "porque no fue encontrado");
        }
    }
}
