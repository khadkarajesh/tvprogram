package com.nepninja.tvprogram.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nepninja.tvprogram.R
import com.nepninja.tvprogram.data.model.TvProgram
import com.nepninja.tvprogram.databinding.ChannelItemBinding
import com.nepninja.tvprogram.databinding.LoadStateFooterViewItemBinding

class TvProgramPageListAdapter(private val callback: ((item: TvProgram) -> Unit)? = null) :
    PagingDataAdapter<TvProgram, TvProgramViewHolder>(DIFF_UTIL) {
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
        holder.itemView.setOnClickListener {
            getItem(position)?.let { it1 -> callback?.invoke(it1) }
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<TvProgram>() {
            override fun areItemsTheSame(oldItem: TvProgram, newItem: TvProgram): Boolean {
                return oldItem.channel.id == newItem.channel.id
            }

            override fun areContentsTheSame(oldItem: TvProgram, newItem: TvProgram): Boolean {
                return oldItem == newItem
            }
        }
    }

}


class TvProgramViewHolder(itemView: ChannelItemBinding) :
    RecyclerView.ViewHolder(itemView.root) {
    var binding: ChannelItemBinding = itemView
}

class LoadStateViewHolder(
    private val binding: LoadStateFooterViewItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState !is LoadState.Loading
        binding.errorMsg.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): LoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.load_state_footer_view_item, parent, false)
            val binding = LoadStateFooterViewItemBinding.bind(view)
            return LoadStateViewHolder(binding, retry)
        }
    }
}

class TvProgramLoadStateAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadStateViewHolder>() {
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder.create(parent, retry)
    }
}
