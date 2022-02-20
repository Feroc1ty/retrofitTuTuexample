package ru.rykunov.retrofittutuexample.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import ru.rykunov.retrofittutuexample.R
import ru.rykunov.retrofittutuexample.activities.MainActivity.Companion.CHAR_ID
import ru.rykunov.retrofittutuexample.activities.MainActivity.Companion.CHAR_IMG
import ru.rykunov.retrofittutuexample.activities.MainActivity.Companion.CHAR_NAME
import ru.rykunov.retrofittutuexample.databinding.ActivityCharDetailsBinding
import ru.rykunov.retrofittutuexample.viewmodel.CharacterViewModel

class CharDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCharDetailsBinding
    private lateinit var characterMvvm: CharacterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //close btn
        binding.btnClose.setOnClickListener {
            finish()
        }

        characterMvvm = ViewModelProviders.of(this)[CharacterViewModel::class.java]
        val intent = intent
        val gender = intent.getStringExtra(MainActivity.CHAR_GENDER)!!
        val status = intent.getStringExtra(MainActivity.CHAR_STATUS)!!
        val species = intent.getStringExtra(MainActivity.CHAR_SPECIES)!!
        val origin = intent.getStringExtra(MainActivity.CHAR_PLACE_NAME)!!
        val location = intent.getStringExtra(MainActivity.CHAR_LOCATION)!!
        binding.collapsingToolbar.title = intent.getStringExtra(CHAR_NAME)!!
        binding.tvSpecies.setText("${getString(R.string.species_text)} $species")
        binding.tvStatus.setText("${getString(R.string.status_text)} $status")
        binding.tvGender.setText("${getString(R.string.gender_text)} $gender")
        binding.tvOrigin.setText("${getString(R.string.origin_text)} $origin")
        binding.tvLocation.setText("${getString(R.string.location_text)} $location")
        /*
        binding.btnUrlInfo.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
         */
        if(intent.getStringExtra(CHAR_IMG) != null){
            Glide.with(applicationContext)
                .load(intent.getStringExtra(CHAR_IMG))
                .into(binding.imCharDetails)
        }
        else{
            binding.imCharDetails.setImageDrawable(getDrawable(R.drawable.ic_close))
        }
        }


}