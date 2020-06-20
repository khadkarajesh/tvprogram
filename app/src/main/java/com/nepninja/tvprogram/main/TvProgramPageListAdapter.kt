package com.nepninja.tvprogram.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nepninja.tvprogram.R
import com.nepninja.tvprogram.data.model.TvProgram
import com.nepninja.tvprogram.databinding.ChannelItemBinding
import kotlinx.android.synthetic.main.channel_item.view.*

class TvProgramPageListAdapter(diffCallback: DiffUtil.ItemCallback<TvProgram>) :
    PagedListAdapter<TvProgram, TvProgramViewHolder>(diffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvProgramViewHolder {
        val binding: ChannelItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.channel_item,
            parent,
            false
        )
        return TvProgramViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvProgramViewHolder, position: Int) {
        holder.binding.item = getItem(position)
    }

}

class TvProgramViewHolder(itemView: ChannelItemBinding) :
    RecyclerView.ViewHolder(itemView.root) {
    var binding: ChannelItemBinding = itemView
}
