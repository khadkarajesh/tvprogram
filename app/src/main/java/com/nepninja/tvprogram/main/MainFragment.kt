package com.nepninja.tvprogram.main

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.nepninja.tvprogram.R
import com.nepninja.tvprogram.base.BaseFragment
import com.nepninja.tvprogram.base.NavigationCommand
import com.nepninja.tvprogram.data.model.TvProgram
import com.nepninja.tvprogram.data.respository.TvProgramDataSource
import com.nepninja.tvprogram.databinding.FragmentMainBinding
import com.nepninja.tvprogram.utils.setup
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : BaseFragment() {
    override val _viewModel: MainViewModel by viewModel()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding.viewModel = _viewModel
        binding.lifecycleOwner = this

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
//        setupRecyclerView()
        val adapter = TvProgramPageListAdapter(object : DiffUtil.ItemCallback<TvProgram>() {
            override fun areItemsTheSame(oldItem: TvProgram, newItem: TvProgram): Boolean {
                return oldItem.channel.id == newItem.channel.id
            }

            override fun areContentsTheSame(oldItem: TvProgram, newItem: TvProgram): Boolean {
                return oldItem == newItem
            }
        })
        binding.rvChannels.layoutManager = GridLayoutManager(activity, 3)
        binding.rvChannels.addItemDecoration(ItemOffsetDecoration(resources.getDimensionPixelSize(R.dimen.grid_item_spacing)))
        binding.rvChannels.adapter = adapter

        _viewModel.tvProgrammesPageList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun setupRecyclerView() {
        val adapter = TvProgramListAdapter {
            _viewModel.navigationCommand.postValue(
                NavigationCommand.To(
                    MainFragmentDirections.actionMainFragmentToDetailFragment(it)
                )
            )
        }
        val layoutManager = GridLayoutManager(activity, 3)
        binding.rvChannels.addItemDecoration(ItemOffsetDecoration(resources.getDimensionPixelSize(R.dimen.grid_item_spacing)))
        binding.rvChannels.layoutManager = layoutManager
        binding.rvChannels.hasFixedSize()
        binding.rvChannels.setup(adapter)
        _viewModel.getChannels()

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.help_faq -> findNavController().navigate(MainFragmentDirections.actionMainFragmentToHelpFaqFragment())
            R.id.about -> findNavController().navigate(MainFragmentDirections.actionMainFragmentToAboutUsFragment())
        }
        return true
    }
}