package com.muhammed.sword.weather.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhammed.sword.databinding.ItemDailyBinding
import com.muhammed.sword.weather.domain.weather.WeatherDataDailyModel


class DailyAdapter :
    RecyclerView.Adapter<DailyAdapter.ViewHolder>() {

    private var itemList: List<WeatherDataDailyModel> = arrayListOf()

    class ViewHolder(private val binding: ItemDailyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val view = itemView

        fun bind(data: WeatherDataDailyModel) {
            binding.data = data
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemDailyBinding.inflate(layoutInflater, parent, false)
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

    fun addData(data: List<WeatherDataDailyModel>) {
        this.itemList = data
        notifyDataSetChanged()
    }

}
