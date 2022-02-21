package ru.rykunov.retrofittutuexample.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ru.rykunov.retrofittutuexample.activities.CharDetailsActivity
import ru.rykunov.retrofittutuexample.activities.MainActivity
import ru.rykunov.retrofittutuexample.databinding.CharactersItemsBinding
import ru.rykunov.retrofittutuexample.pojo.CharactersList
import ru.rykunov.retrofittutuexample.pojo.Character
import kotlin.coroutines.coroutineContext

class CharactersAdapter(context: Context): RecyclerView.Adapter<CharactersAdapter.CharactersViewHolder>() {
    private var charactersList = ArrayList<Character>()
    private val context = context

    fun setCharacters(charlist: ArrayList<Character>){
        this.charactersList = charlist
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        return CharactersViewHolder(CharactersItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(charactersList[position].image)
            .into(holder.binding.imAvatar)
        holder.binding.tvName.setText(charactersList[position].name)
        holder.binding.tvGender.setText(charactersList[position].gender)
        holder.binding.tvStatus.setText(charactersList[position].status)

        holder.binding.characterCard.setOnClickListener {
                val intent = Intent(context, CharDetailsActivity::class.java)
                Log.d("MyLog","Click on card $position")
        }
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    class CharactersViewHolder(val binding: CharactersItemsBinding): RecyclerView.ViewHolder(binding.root)
}