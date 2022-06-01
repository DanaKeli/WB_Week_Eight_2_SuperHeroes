package com.example.wb_week_five_2.presentation.heroes

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.wb_week_five_2.databinding.HeroesListItemBinding
import com.example.wb_week_five_2.domain.Heroes
import com.squareup.picasso.Picasso

class HeroesAdapter(private val context: Context)  : ListAdapter<Heroes, HeroesVewHolder>(DiffCallback()) {

    var onItemClickListener: ((Heroes) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeroesVewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HeroesListItemBinding.inflate(layoutInflater, parent, false)
        return HeroesVewHolder(binding, context)
    }

    override fun onBindViewHolder(holder: HeroesVewHolder, position: Int) {
        val heroItem = getItem(position)
        holder.bind(heroItem)
        holder.itemView.setOnClickListener {
            onItemClickListener?.invoke(heroItem)
        }
    }
}

class HeroesVewHolder(private val binding: HeroesListItemBinding, private val context: Context) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Heroes) {
        Picasso.with(context)
            .load(item.image.url)
            .into(binding.ivImage)
        binding.tvName.text = item.name
    }
}

private class DiffCallback : DiffUtil.ItemCallback<Heroes>() {
    override fun areItemsTheSame(oldItem: Heroes, newItem: Heroes): Boolean =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Heroes, newItem: Heroes): Boolean =
        oldItem.name == newItem.name
}