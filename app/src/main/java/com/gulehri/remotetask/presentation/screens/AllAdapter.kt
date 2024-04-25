package com.gulehri.remotetask.presentation.screens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gulehri.remotetask.databinding.SingleRvItemBinding
import com.gulehri.remotetask.models.CountryItem

/*
 * Created by Shahid Iqbal on 4/26/2024.
 */

class AllAdapter : ListAdapter<CountryItem, AllAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            SingleRvItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val country = getItem(position)
        if (country != null)
            holder.bindTo(country)
    }

    inner class ViewHolder(private val binding: SingleRvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

            fun bindTo(country: CountryItem) {
            binding.imgFlag.load(country.flags.png)
            binding.tvName.text = country.name.common
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<CountryItem>() {
        override fun areItemsTheSame(oldItem: CountryItem, newItem: CountryItem) =
            oldItem.capital == newItem.capital

        override fun areContentsTheSame(oldItem: CountryItem, newItem: CountryItem) =
            oldItem == newItem
    }
}