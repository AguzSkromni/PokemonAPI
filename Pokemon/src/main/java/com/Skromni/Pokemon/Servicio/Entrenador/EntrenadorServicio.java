package com.Skromni.Pokemon.Servicio.Entrenador;

import com.Skromni.Pokemon.Excepcion.RecursosNoEncontradosExcepcion;
import com.Skromni.Pokemon.Modelo.Entrenador;
import com.Skromni.Pokemon.Repositorio.EntrenadorRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrenadorServicio implements IEntrenadorServicio{

    @Autowired
    private EntrenadorRepositorio entrenadorRepositorio;

    @Override
    public List<Entrenador> listadoEntrenadores() {;
        return entrenadorRepositorio.findAll();
    }

    @Override
    public Entrenador buscarEntrenadorPorId(Integer idEntrenador) {
        return entrenadorRepositorio.findById(idEntrenador).orElseThrow(() -> new RecursosNoEncontradosExcepcion(
                "Entrenador con id " + idEntrenador + " no encontrado"));
    }

    @Override
    public Entrenador guardarEntrenador(Entrenador entrenador) {
        entrenadorRepositorio.save(entrenador);
        return entrenador;
    }

    @Override
    public void eliminarEntrenador(Integer idEntrenador) {
        entrenadorRepositorio.deleteById(idEntrenador);
    }
}
