package com.example.apiapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apiapp.Common.Common
import com.example.apiapp.Common.ItemOffsetDecoration
import com.example.apiapp.retrofit.IPokemonList
import com.example.apiapp.retrofit.RetrofitClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


class PokemonList : Fragment() {

    internal var compositeDisposable = CompositeDisposable()
    internal var iPokemonList:IPokemonList
    internal lateinit var recycler_view:RecyclerView



    init{
        val retrofit = RetrofitClient.instance
        iPokemonList = retrofit.create(IPokemonList::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val itemView =  inflater.inflate(R.layout.fragment_pokemon_list, container, false)
        // Inflate the layout for this fragment

        recycler_view = itemView.findViewById(R.id.pokemon_recyclerView) as RecyclerView

        recycler_view.setHasFixedSize(true)
        recycler_view.layoutManager = GridLayoutManager(activity, 2)

        val itemDecoration = ItemOffsetDecoration(requireActivity(), R.dimen.spacing)

        recycler_view.addItemDecoration(itemDecoration)

        fetchData()


        return itemView

    }

    private fun fetchData() {
        compositeDisposable.add(iPokemonList.listPokemon
            .subscribeOn (Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ pokemonDex ->
                Common.pokemonList = pokemonDex.pokemon!!
                val adapter = PokeAdapter(requireActivity(), Common.pokemonList)

                recycler_view.adapter = adapter
                //println(pokemonDex.pokemon!! + "oiii")
            }
        );

    }

}