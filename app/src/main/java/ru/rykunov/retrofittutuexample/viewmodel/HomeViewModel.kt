package ru.rykunov.retrofittutuexample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rykunov.retrofittutuexample.pojo.CharacterRandom
import ru.rykunov.retrofittutuexample.retrofit.RetrofitInstance

class HomeViewModel(): ViewModel() {
    private var randomCharacterLiveData = MutableLiveData<CharacterRandom>()

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

    fun observeRandomCharacterLiveData(): LiveData<CharacterRandom>{
        return randomCharacterLiveData
    }
}