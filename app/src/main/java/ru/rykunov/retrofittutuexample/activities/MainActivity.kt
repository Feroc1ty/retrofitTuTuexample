package ru.rykunov.retrofittutuexample.activities

import android.content.Intent
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
    lateinit var randomChar: CharacterRandom

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        homeMvvm = ViewModelProviders.of(this)[HomeViewModel::class.java]
        homeMvvm.getRandomCharacter()
        observerRandomCharacter()
        onRandomCharacterClick()
    }

    private fun onRandomCharacterClick() {
        binding.randomCharacterCard.setOnClickListener{
            val intent = Intent(this, CharDetailsActivity::class.java)
            intent.putExtra(CHAR_ID, randomChar.id)
            intent.putExtra(CHAR_NAME, randomChar.name)
            intent.putExtra(CHAR_IMG, randomChar.image)
            startActivity(intent)
        }
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
                randomChar = t
            }
        })


    }

    companion object{
        const val CHAR_ID = "idChar"
        const val CHAR_NAME = "nameChar"
        const val CHAR_IMG = "imgChar"
    }
}