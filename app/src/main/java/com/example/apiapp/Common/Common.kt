package com.example.apiapp.Common

import com.example.apiapp.Pokemon
import com.example.apiapp.PokemonList

object Common {
    fun findPokemonByNum(num: String?): Pokemon? {
        for(pokemon in Common.pokemonList){
            if(pokemon.num.equals(num)){
                return pokemon
            }
        }
        return null
    }

    var pokemonList :List<Pokemon> = ArrayList()

    val KEY_ENABLE_HOME = "position"

}