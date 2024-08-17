package com.Skromni.Pokemon.Servicio.Pokemon;

import com.Skromni.Pokemon.Excepcion.RecursosNoEncontradosExcepcion;
import com.Skromni.Pokemon.Modelo.Pokemon;
import com.Skromni.Pokemon.Repositorio.PokemonRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonServicio implements IPokemonServicio{

    @Autowired
    private PokemonRepositorio pokemonRepositorio;

    @Override
    public List<Pokemon> listarPokemones() {
        return pokemonRepositorio.findAll();
    }

    @Override
    public Pokemon buscarPokemonPorId(Integer idPokemon) {
        return pokemonRepositorio.findById(idPokemon)
                .orElseThrow(() -> new RecursosNoEncontradosExcepcion(
                        "Pokemon con ID " + idPokemon + " no encontrado"));
    }

    @Override
    public Pokemon guardarPokemon(Pokemon pokemon) {
        return pokemonRepositorio.save(pokemon);
    }

    @Override
    public void eliminarPokemon(Integer idPokemon) {
        pokemonRepositorio.deleteById(idPokemon);
    }
}
