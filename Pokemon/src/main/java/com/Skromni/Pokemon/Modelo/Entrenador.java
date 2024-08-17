package com.Skromni.Pokemon.Modelo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Entrenador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idEntrenador;

    String nombreEntrenador;
    Integer edadEntrenador;
    String ciudadEntrenador;

    @OneToMany(mappedBy = "entrenador")
    @JsonManagedReference
    private List<Pokemon> pokemones;
}
