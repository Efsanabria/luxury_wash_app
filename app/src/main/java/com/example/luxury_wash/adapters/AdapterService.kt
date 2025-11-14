package com.example.luxury_wash.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.luxury_wash.R
import com.example.luxury_wash.models.ItemService
import com.google.android.material.chip.Chip

class AdapterService(
    private val onClick: (ItemService) -> Unit
) : RecyclerView.Adapter<AdapterService.VH>() {

    private val items = mutableListOf<ItemService>()

    fun submit(list: List<ItemService>) {
        items.clear()
        items.addAll(list)
        notifyDataSetChanged()
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        private val img = view.findViewById<ImageView>(R.id.iv_services)
        private val chip = view.findViewById<Chip>(R.id.chip_services_title)
        private val subtitle = view.findViewById<TextView>(R.id.tv_Subtitle)
        private val desc = view.findViewById<TextView>(R.id.tv_Description)
        private val price = view.findViewById<TextView>(R.id.tv_Price)

        fun bind(item: ItemService) {
            chip.text = item.title
            subtitle.text = item.subtitle
            desc.text = item.desc
            price.text = item.priceLabel
            img.setImageResource(item.imageRes)
            itemView.setOnClickListener { onClick(item) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_services, parent, false) // ojo: nombre del layout
        return VH(v)
    }

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(items[position])
    override fun getItemCount(): Int = items.size
}