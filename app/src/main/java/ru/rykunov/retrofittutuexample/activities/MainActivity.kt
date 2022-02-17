package ru.rykunov.retrofittutuexample.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import ru.rykunov.retrofittutuexample.databinding.ActivityMainBinding
import ru.rykunov.retrofittutuexample.pojo.CharacterRandom
import ru.rykunov.retrofittutuexample.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var homeMvvm: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        homeMvvm = ViewModelProviders.of(this)[HomeViewModel::class.java]

        homeMvvm.getRandomCharacter()
        observerRandomCharacter()



    }

    private fun observerRandomCharacter() {
        homeMvvm.observeRandomCharacterLiveData().observe(this, object : Observer<CharacterRandom>{
            override fun onChanged(t: CharacterRandom?) {
                Glide.with(this@MainActivity)
                    .load(t!!.image)
                    .into(binding.imageRandom)
                binding.tvrandom.text = t.name
                binding.tvgender.text = t.gender
                binding.tvspecies.text = t.species
                binding.tvstatus.text = t.status
            }
        })

    }
}