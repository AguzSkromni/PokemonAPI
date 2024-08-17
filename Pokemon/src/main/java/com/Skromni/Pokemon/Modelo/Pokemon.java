package com.Skromni.Pokemon.Modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Pokemon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idPokemon;
    String nombrePokemon;
    String tipoPokemon;
    Integer nivelPokemon;

    @ManyToOne
    @JoinColumn(name = "entrenador_id")
    @JsonBackReference
    private Entrenador entrenador;

}
