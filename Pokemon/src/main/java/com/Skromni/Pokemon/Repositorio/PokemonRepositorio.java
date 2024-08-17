package com.Skromni.Pokemon.Repositorio;

import com.Skromni.Pokemon.Modelo.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepositorio extends JpaRepository<Pokemon, Integer> {
}
