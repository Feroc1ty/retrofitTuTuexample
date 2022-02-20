package ru.rykunov.retrofittutuexample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rykunov.retrofittutuexample.pojo.CharacterRandom
import ru.rykunov.retrofittutuexample.pojo.CharactersList
import ru.rykunov.retrofittutuexample.pojo.Result
import ru.rykunov.retrofittutuexample.retrofit.RetrofitInstance

class HomeViewModel(): ViewModel() {
    private var randomCharacterLiveData = MutableLiveData<CharacterRandom>()
    private var charactersListLiveData = MutableLiveData<List<Result>>()

    fun getRandomCharacter(){
        RetrofitInstance.api.getRandomCharacter((1..20).random().toString()).enqueue(object : Callback<CharacterRandom> {
            override fun onResponse(call: Call<CharacterRandom>, response: Response<CharacterRandom>) {
                if(response.body() != null){
                    val randomChar: CharacterRandom = response.body()!!
                    randomCharacterLiveData.value = randomChar
                }
                else{
                    return
                }
            }

            override fun onFailure(call: Call<CharacterRandom>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
            }

        })

    }

    fun getCharactersList(){
        RetrofitInstance.api.getCharactersList().enqueue(object : Callback<CharactersList>{
            override fun onResponse(call: Call<CharactersList>, response: Response<CharactersList>) {
                if (response.body() != null){
                    charactersListLiveData.value = response.body()!!.results
                }
            }

            override fun onFailure(call: Call<CharactersList>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
            }


        })
    }

    fun observeRandomCharacterLiveData(): LiveData<CharacterRandom>{
        return randomCharacterLiveData
    }

    fun observeCharactersLiveData(): LiveData<List<Result>>{
        return charactersListLiveData
    }
}