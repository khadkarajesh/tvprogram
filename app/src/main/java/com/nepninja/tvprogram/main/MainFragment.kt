package com.nepninja.tvprogram.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.nepninja.tvprogram.R
import com.nepninja.tvprogram.base.BaseFragment
import com.nepninja.tvprogram.databinding.FragmentMainBinding
import com.nepninja.tvprogram.utils.setup
import com.paginate.Paginate
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : BaseFragment(), Paginate.Callbacks {
    override val _viewModel: MainViewModel by viewModel()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.viewModel = _viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = TvProgramListAdapter {
        }
        val layoutManager = GridLayoutManager(activity, 3)
        binding.rvChannels.addItemDecoration(ItemOffsetDecoration(resources.getDimensionPixelSize(R.dimen.grid_item_spacing)))
        binding.rvChannels.layoutManager = layoutManager
        binding.rvChannels.hasFixedSize()
        binding.rvChannels.setup(adapter)
        _viewModel.getChannels()

    }

    override fun onLoadMore() {
        _viewModel.getChannels()
    }

    override fun isLoading(): Boolean {
        return _viewModel.loading.value!!
    }

    override fun hasLoadedAllItems(): Boolean {
        return _viewModel.tvProgrammes.value!!.size > 100
    }
}