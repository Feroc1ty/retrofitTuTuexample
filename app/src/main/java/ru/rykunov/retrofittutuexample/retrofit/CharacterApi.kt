package ru.rykunov.retrofittutuexample.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import ru.rykunov.retrofittutuexample.pojo.CharacterRandom

interface CharacterApi {

    @GET("character/{random}")
    fun getRandomCharacter(@Path("random") random: String): Call<CharacterRandom>
}