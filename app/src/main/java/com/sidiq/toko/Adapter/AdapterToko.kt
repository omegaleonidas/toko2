package com.sidiq.toko.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import com.sidiq.toko.Model.Login
import com.sidiq.toko.Model.Store
import com.sidiq.toko.Model.TokoModel
import com.sidiq.toko.View.DetailFragment
import com.sidiq.toko.databinding.CardViewBinding

class AdapterToko(
    private val list: List<TokoModel>,
    private var clickListener: (TokoModel) -> Unit
) : RecyclerView.Adapter<AdapterToko.tokoHolder>() {


    class tokoHolder(var binding: CardViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(item: TokoModel?, clickListener: (TokoModel) -> Unit) = with(binding) {

            binding.tvNamaToko.text = item?.store_name
            binding.tvType.text = item?.channel_name
            binding.tvUkuran.text = item?.address
            binding.cardView.setOnClickListener {

                clickListener(item!!)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = tokoHolder(
        CardViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: tokoHolder, position: Int) {
        with(holder) {
            bindTo(
                list[position], clickListener
            )
        }


    }

    override fun getItemCount() = list.size
}


