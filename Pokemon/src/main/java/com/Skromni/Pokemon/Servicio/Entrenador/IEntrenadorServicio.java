package com.Skromni.Pokemon.Servicio.Entrenador;

import com.Skromni.Pokemon.Modelo.Entrenador;

import java.util.List;
import java.util.Optional;

public interface IEntrenadorServicio {

    public List<Entrenador> listadoEntrenadores();

    public Entrenador buscarEntrenadorPorId(Integer idEntrenador);

    public Entrenador guardarEntrenador(Entrenador entrenador);

    public void eliminarEntrenador(Integer idEntrenador);
}
