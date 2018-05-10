package com.example.sistemas.pokeapivias;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.sistemas.pokeapivias.models.ViasRespuesta;
import com.example.sistemas.pokeapivias.pokeapi.PokeApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;


    private RecyclerView recyclerView;
    private ListaViasAdapter listaViasAdapter;
    private boolean aptoParaCargar;
    //private int offset;
    public static final String TAG = "POKEAPI";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        listaViasAdapter = new ListaViasAdapter(this);
        recyclerView.setAdapter(listaViasAdapter);
        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if (aptoParaCargar) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            Log.i(TAG, " Llegamos al final.");

                            aptoParaCargar = false;
                            //offset += 20;
                            obtenerDatos();
                        }
                    }
                }
            }
        });

        //nuevo
        //nuevo

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.datos.gov.co/resource/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        aptoParaCargar = true;
        //offset = 0;
        obtenerDatos();
    }
    private void obtenerDatos() {

        PokeApiService service = retrofit.create(PokeApiService.class);
        Call<ArrayList<ViasRespuesta>> pokemonRespuestaCall=service.obtenerListaPokemon();

        pokemonRespuestaCall.enqueue(new Callback<ArrayList<ViasRespuesta>>() {
            public static final String TAG = "POKEAPI";

            @Override
            public void onResponse(Call<ArrayList<ViasRespuesta>> call, Response<ArrayList<ViasRespuesta>> response) {
                if(response.isSuccessful())
                {
                    //pokemorespuestatodo
                    //PokemonRespuesta pokemonRespuesta = response.body();
                    //Log.i(TAG, "count: "+pokemonRespuesta.getCount());
                    //Log.i(TAG, "next: "+pokemonRespuesta.getNext());

                    ArrayList<ViasRespuesta> listaPokemon = response.body();
                    listaViasAdapter.adicionarListaPokemon(listaPokemon);
                    for(int i=0; i<listaPokemon.size(); i++)
                    {
                        ViasRespuesta p = listaPokemon.get(i);

                        //Log.i(TAG,"name: "+p.getName()+" url: "+p.getUrl());
                        Log.i(TAG, "Codigo: "+p.getCodigo());
                        Log.i(TAG, "Identificador: "+p.getIdentificador());
                        Log.i(TAG, "Longitud firmado: "+p.getLongitudafirmado());
                        Log.i(TAG, "Longitud pavimento: "+p.getLongitudpavimento());
                        Log.i(TAG, "Municipio: "+p.getMuncipio());
                        Log.i(TAG, "Nombre via: "+p.getNombrevia());


                    }
                }
                else
                {
                    Log.e(TAG, "onResponse: "+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ArrayList<ViasRespuesta>> call, Throwable t) {

                Log.e(TAG," onFailure: "+t.getMessage() );
            }
        });
    }
}
