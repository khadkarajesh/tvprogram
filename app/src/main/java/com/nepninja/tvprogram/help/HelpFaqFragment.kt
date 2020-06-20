package com.nepninja.tvprogram.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.nepninja.tvprogram.R
import com.nepninja.tvprogram.base.BaseFragment
import com.nepninja.tvprogram.databinding.FragmentHelpFaqBinding
import com.nepninja.tvprogram.utils.setup
import org.koin.androidx.viewmodel.ext.android.viewModel

class HelpFaqFragment : BaseFragment() {
    override val _viewModel: FaqViewModel by viewModel()
    private lateinit var binding: FragmentHelpFaqBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_help_faq, container, false)
        binding.viewModel = _viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val adapter = HelpFaqAdapter {}
        binding.rvFaq.setup(adapter)
    }
}