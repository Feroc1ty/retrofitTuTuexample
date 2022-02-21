package ru.rykunov.retrofittutuexample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rykunov.retrofittutuexample.pojo.CharactersList
import ru.rykunov.retrofittutuexample.pojo.Character
import ru.rykunov.retrofittutuexample.retrofit.RetrofitInstance

class HomeViewModel(): ViewModel() {
    private var randomCharacterLiveData = MutableLiveData<Character>()
    private var charactersListLiveData = MutableLiveData<List<Character>>()

    fun getRandomCharacter(){
        RetrofitInstance.api.getRandomCharacter((1..20).random().toString()).enqueue(object : Callback<Character> {
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if(response.body() != null){
                    val randomChar: Character = response.body()!!
                    randomCharacterLiveData.value = randomChar
                }
            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
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

    fun observeRandomCharacterLiveData(): LiveData<Character>{
        return randomCharacterLiveData
    }

    fun observeCharactersLiveData(): LiveData<List<Character>>{
        return charactersListLiveData
    }
}