package com.Skromni.Pokemon.Servicio.Pokemon;

import com.Skromni.Pokemon.Modelo.Pokemon;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface IPokemonServicio {

    public List<Pokemon> listarPokemones();

    public Pokemon buscarPokemonPorId(Integer idPokemon);

    public Pokemon guardarPokemon(Pokemon pokemon);

    public void eliminarPokemon(Integer idPokemon);
}
