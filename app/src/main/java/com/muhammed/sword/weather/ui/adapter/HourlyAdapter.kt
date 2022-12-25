package com.muhammed.sword.weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammed.sword.databinding.ItemHourlyBinding
import com.muhammed.sword.weather.domain.weather.WeatherDataHourlyModel


class HourlyAdapter :
    RecyclerView.Adapter<HourlyAdapter.ViewHolder>() {


    private var itemList: List<WeatherDataHourlyModel> = arrayListOf()

    class ViewHolder(private val binding: ItemHourlyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val view = itemView

        fun bind(data: WeatherDataHourlyModel) {
            binding.data = data
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHourlyBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(
                    binding
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(
            parent
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.bind(currentItem)
    }


    override fun getItemCount(): Int {
        return itemList.size
    }

    fun addData(data: List<WeatherDataHourlyModel>) {
        this.itemList = data
        notifyDataSetChanged()
    }

}
