package com.nepninja.tvprogram.program

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.nepninja.tvprogram.R
import com.nepninja.tvprogram.databinding.FragmentProgramDetailBinding
import com.nepninja.tvprogram.utils.setTitle


class ProgramDetailFragment : Fragment() {
    lateinit var binding: FragmentProgramDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProgramDetailBinding.inflate(inflater)

        val program = ProgramDetailFragmentArgs.fromBundle(arguments!!).program
        binding.program = program

        program.title?.let { setTitle(it) }

        return binding.root
    }
}