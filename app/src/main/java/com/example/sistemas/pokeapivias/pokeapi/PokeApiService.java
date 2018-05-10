package com.example.sistemas.pokeapivias.pokeapi;

import com.example.sistemas.pokeapivias.models.ViasRespuesta;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by sistemas on 4/05/18.
 */

public interface PokeApiService {

    @GET("qvqk-dtmf.json")
    Call<ArrayList<ViasRespuesta>> obtenerListaPokemon();
}
