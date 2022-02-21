package ru.rykunov.retrofittutuexample.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import ru.rykunov.retrofittutuexample.adapter.CharactersAdapter
import ru.rykunov.retrofittutuexample.databinding.ActivityMainBinding
import ru.rykunov.retrofittutuexample.pojo.Character
import ru.rykunov.retrofittutuexample.pojo.Location
import ru.rykunov.retrofittutuexample.pojo.Origin
import ru.rykunov.retrofittutuexample.viewmodel.HomeViewModel

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var homeMvvm: HomeViewModel
    lateinit var randomChar: Character
    lateinit var origin: Origin
    lateinit var location: Location
    lateinit var charItemsAdapter: CharactersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        charItemsAdapter = CharactersAdapter(this)
        setContentView(binding.root)
        prepareCharactercRcView()
        homeMvvm = ViewModelProviders.of(this)[HomeViewModel::class.java]
        homeMvvm.getRandomCharacter()
        observerRandomCharacter()
        onRandomCharacterClick()
        homeMvvm.getCharactersList()
        observeCharactersItemsLiveData()
        onCharacterItemClick()
    }

    /*
    Слушатель нажатий в RecyclerView и передача данных в новое акативити через интент
     */
    private fun onCharacterItemClick() {
        charItemsAdapter.onItemClick = { character ->
            val intent = Intent(this, CharDetailsActivity::class.java)
            intent.putExtra(CHAR_ID, character.id)
            intent.putExtra(CHAR_NAME, character.name)
            intent.putExtra(CHAR_IMG, character.image)
            intent.putExtra(CHAR_GENDER, character.gender)
            intent.putExtra(CHAR_STATUS, character.status)
            intent.putExtra(CHAR_SPECIES, character.species)
            intent.putExtra(CHAR_PLACE_NAME, character.origin.name)
            intent.putExtra(CHAR_PLACE_URL, character.origin.url)
            intent.putExtra(CHAR_LOCATION, character.location.name)
            startActivity(intent)
        }
    }
    /*
    Подготовка Recycler View
     */
    private fun prepareCharactercRcView() {
        binding.rcCharacters.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = charItemsAdapter
        }
    }

    private fun observeCharactersItemsLiveData() {
        homeMvvm.observeCharactersLiveData().observe(this
        ) { characterList ->
            charItemsAdapter.setCharacters(charlist = characterList as ArrayList<Character>)
        }

    }
    /*
    Слушатель нажатий в блоке случайного персонажа и передача данных в новое акативити через интент
     */
    private fun onRandomCharacterClick() {
        binding.randomCharacterCard.setOnClickListener{
            val intent = Intent(this, CharDetailsActivity::class.java)
            intent.putExtra(CHAR_ID, randomChar?.id)
            intent.putExtra(CHAR_NAME, randomChar?.name)
            intent.putExtra(CHAR_IMG, randomChar?.image)
            intent.putExtra(CHAR_GENDER, randomChar?.gender)
            intent.putExtra(CHAR_SPECIES, randomChar?.species)
            intent.putExtra(CHAR_STATUS, randomChar?.status)
            intent.putExtra(CHAR_PLACE_NAME, randomChar?.origin.name)
            intent.putExtra(CHAR_PLACE_URL, randomChar?.origin.url)
            intent.putExtra(CHAR_LOCATION, randomChar?.location.name)
            startActivity(intent)
        }
    }

    private fun observerRandomCharacter() {
        homeMvvm.observeRandomCharacterLiveData().observe(this, object : Observer<Character>{
            override fun onChanged(t: Character?) {
                Glide.with(this@MainActivity)
                    .load(t!!.image)
                    .into(binding.imageRandom)
                binding.tvrandom.text = t.name
                binding.tvgender.text = t.gender
                binding.tvspecies.text = t.species
                binding.tvstatus.text = t.status
                randomChar = t
                origin = t.origin
                location = t.location
            }
        })


    }

    companion object{
        const val CHAR_ID = "idChar"
        const val CHAR_NAME = "nameChar"
        const val CHAR_IMG = "imgChar"
        const val CHAR_GENDER = "genderChar"
        const val CHAR_SPECIES = "speciesChar"
        const val CHAR_STATUS = "statusChar"
        const val CHAR_LOCATION = "locChar"
        const val CHAR_PLACE_NAME = "placeNameChar"
        const val CHAR_PLACE_URL = "placeUrlChar"
    }
}