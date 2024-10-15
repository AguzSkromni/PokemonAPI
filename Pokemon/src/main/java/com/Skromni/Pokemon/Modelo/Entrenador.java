package com.Skromni.Pokemon.Modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Entrenador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEntrenador;

    private String nombreEntrenador;
    private Integer edadEntrenador;
    private String ciudadEntrenador;

    @OneToMany(mappedBy = "entrenador", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Pokemon> pokemones = new ArrayList<>();

    @Transient
    private String pokemonNames;

    private Integer numeroPrueba;
}


