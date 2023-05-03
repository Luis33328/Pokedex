package com.example.apiapp.retrofit

import com.example.apiapp.Pokedex
import io.reactivex.Observable
import com.example.apiapp.PokedexMain
import retrofit2.http.GET

interface IPokemonList {

    @get:GET("pokedex.json")
    val listPokemon:Observable<Pokedex>
}