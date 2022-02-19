package ru.rykunov.retrofittutuexample.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import ru.rykunov.retrofittutuexample.activities.MainActivity.Companion.CHAR_ID
import ru.rykunov.retrofittutuexample.activities.MainActivity.Companion.CHAR_IMG
import ru.rykunov.retrofittutuexample.activities.MainActivity.Companion.CHAR_NAME
import ru.rykunov.retrofittutuexample.databinding.ActivityCharDetailsBinding
import ru.rykunov.retrofittutuexample.viewmodel.CharacterViewModel

class CharDetailsActivity : AppCompatActivity() {
    private lateinit var charId: String
    private lateinit var charName: String
    private lateinit var charImg: String
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
            binding.collapsingToolbar.title = intent.getStringExtra(CHAR_NAME)!!
            Glide.with(applicationContext)
                .load(intent.getStringExtra(CHAR_IMG))
                .into(binding.imCharDetails)
        }
        fun getCharacterInfoFromIntent() {
            val intent = intent
            charName = intent.getStringExtra(CHAR_NAME)!!
            Log.d("MyLog", charName)
        }

}