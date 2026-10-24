package com.example.PokemonApi.service;

import com.example.PokemonApi.model.Pokemon;
import com.example.PokemonApi.repository.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    @Autowired
    private PokemonRepository pokemonRepository;

    public List<Pokemon> obtenerTodos(){
        return pokemonRepository.findAll();
    }

    public Pokemon obtenerPorId(Long id){
        return pokemonRepository.findById(id).orElse(null);
    }

    public Pokemon crear(Pokemon pokemon){
        return pokemonRepository.save(pokemon);
    }

    public Optional<Pokemon> actualizar(Long id, Pokemon pokemon){
        return pokemonRepository.findById(id).map(pokemonexist -> {
            pokemonexist.setNombre(pokemon.getNombre());
            pokemonexist.setTipo(pokemon.getTipo());
            pokemonexist.setAtaque(pokemon.getAtaque());
            pokemonexist.setDefensa(pokemon.getDefensa());
            pokemonexist.setDescripcion(pokemon.getDescripcion());
            pokemonexist.setImagenUrl(pokemon.getImagenUrl());
            pokemonexist.setNivel(pokemon.getNivel());
            return pokemonRepository.save(pokemonexist);
        });
    }

    public Optional<Pokemon> actualizarParcialmente(Long id,Pokemon pokemon){
        return pokemonRepository.findById(id).map(pokemonexist -> {
            if(pokemon.getNombre() !=null)pokemonexist.setNombre(pokemon.getNombre());
            if(pokemon.getTipo() !=null)pokemonexist.setTipo(pokemon.getTipo());
            if(pokemon.getAtaque() !=null)pokemonexist.setAtaque(pokemon.getAtaque());
            if(pokemon.getDefensa() !=null)pokemonexist.setDefensa(pokemon.getDefensa());
            if(pokemon.getDescripcion() !=null)pokemonexist.setDescripcion(pokemon.getDescripcion());
            if(pokemon.getImagenUrl() !=null)pokemonexist.setImagenUrl(pokemon.getImagenUrl());
            if(pokemon.getNivel() !=null)pokemonexist.setNivel(pokemon.getNivel());
            return pokemonRepository.save(pokemonexist);
        });
    }

    public boolean eliminar(Long id){
        if (pokemonRepository.existsById(id)) {
            pokemonRepository.deleteById(id);
            return true;
        }
        return false;
    }




}
