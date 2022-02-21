package ru.rykunov.retrofittutuexample.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ru.rykunov.retrofittutuexample.pojo.Character
import ru.rykunov.retrofittutuexample.retrofit.RetrofitInstance

class CharacterViewModel(): ViewModel() {
    private val characterLiveData = MutableLiveData<Character>()

    fun getCharacterDetail(id : String){
        RetrofitInstance.api.getCharacterDetails(id).enqueue(object : Callback<Character>{
            override fun onResponse(call: Call<Character>, response: Response<Character>) {
                if (response.body() != null){
                    characterLiveData.value = response.body()
                }
                else return

            }

            override fun onFailure(call: Call<Character>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
            }
        })
    }

    fun observerCharacterDetailsLiveData(): LiveData<Character>{
        return characterLiveData
    }

}