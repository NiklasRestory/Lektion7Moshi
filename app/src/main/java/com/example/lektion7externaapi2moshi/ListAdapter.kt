package com.example.lektion7externaapi2moshi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import coil.transform.*
import com.example.lektion7externaapi2moshi.network.Character

class ListAdapter(val characterList : List<Character>) : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    inner class ListViewHolder(val itemView: View) :
            RecyclerView.ViewHolder(itemView) {

                fun bindData(character: Character) {
                    val tv_name = itemView.findViewById<TextView>(R.id.tv_name)
                    val iv_image = itemView.findViewById<ImageView>(R.id.iv_image)

                    tv_name.text = character.name
                    iv_image.load(character.image) {
                        transformations(CircleCropTransformation())
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_list, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bindData(characterList[position])
    }

    override fun getItemCount(): Int {
        return characterList.size
    }
}