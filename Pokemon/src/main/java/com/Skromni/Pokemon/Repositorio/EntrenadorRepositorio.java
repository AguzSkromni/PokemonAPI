package com.Skromni.Pokemon.Repositorio;

import com.Skromni.Pokemon.Modelo.Entrenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrenadorRepositorio extends JpaRepository<Entrenador, Integer> {
}
