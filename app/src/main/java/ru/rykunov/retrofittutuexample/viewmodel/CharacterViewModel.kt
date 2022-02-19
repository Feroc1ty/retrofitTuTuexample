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

class CharacterViewModel(): ViewModel() {
    private val characterLiveData = MutableLiveData<CharacterRandom>()

    fun getCharacterDetail(id : String){
        RetrofitInstance.api.getCharacterDetails(id).enqueue(object : Callback<CharacterRandom>{
            override fun onResponse(call: Call<CharacterRandom>, response: Response<CharacterRandom>) {
                if (response.body() != null){
                    characterLiveData.value = response.body()
                }
                else return

            }

            override fun onFailure(call: Call<CharacterRandom>, t: Throwable) {
                Log.d("MyLog", t.message.toString())
            }
        })
    }

    fun observerCharacterDetailsLiveData(): LiveData<CharacterRandom>{
        return characterLiveData
    }

}