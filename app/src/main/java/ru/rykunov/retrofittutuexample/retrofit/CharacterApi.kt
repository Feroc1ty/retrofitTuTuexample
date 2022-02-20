package ru.rykunov.retrofittutuexample.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import ru.rykunov.retrofittutuexample.pojo.CharacterRandom
import ru.rykunov.retrofittutuexample.pojo.CharactersList
import ru.rykunov.retrofittutuexample.pojo.Result

interface CharacterApi {

    @GET("character/{random}")
    fun getRandomCharacter(@Path("random") random: String): Call<CharacterRandom>

    @GET("character")
    fun getCharacterDetails(@Query("i") id: String): Call<CharacterRandom>

    @GET("character")
    fun getCharactersList(): Call<CharactersList>
}