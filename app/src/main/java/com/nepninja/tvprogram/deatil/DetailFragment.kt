package com.nepninja.tvprogram.deatil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.nepninja.tvprogram.R
import com.nepninja.tvprogram.base.BaseFragment
import com.nepninja.tvprogram.databinding.FragmentDetailBinding
import com.nepninja.tvprogram.main.ItemOffsetDecoration
import com.nepninja.tvprogram.utils.setup
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailFragment : BaseFragment() {
    lateinit var binding: FragmentDetailBinding
    override val _viewModel: DetailViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater)
        val tvProgram = DetailFragmentArgs.fromBundle(arguments!!).tvprogram
        binding.viewModel = _viewModel
        binding.lifecycleOwner = this
        _viewModel.programmes.value = tvProgram.programmes
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val adapter = ProgrammeListAdapter {
        }
        val layoutManager = GridLayoutManager(activity, 2)
        binding.rvProgrammes.addItemDecoration(
            ItemOffsetDecoration(
                resources.getDimensionPixelSize(
                    R.dimen.grid_item_spacing
                )
            )
        )
        binding.rvProgrammes.layoutManager = layoutManager
        binding.rvProgrammes.hasFixedSize()
        binding.rvProgrammes.setup(adapter)
    }

}