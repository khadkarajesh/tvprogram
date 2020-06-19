package com.nepninja.tvprogram.main

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.nepninja.tvprogram.base.BaseViewModel
import com.nepninja.tvprogram.data.remote.TvProgramService
import com.nepninja.tvprogram.utils.TvProgrammeParser
import kotlinx.coroutines.launch
import org.w3c.dom.Element
import org.w3c.dom.Node

class MainViewModel(
    private val app: Application,
    private val api: TvProgramService
) : BaseViewModel(app) {


    fun getChannels() {
        viewModelScope.launch {
//            val programmeDeferred = api.getProgrammesAsync()
            try {
//                val result = programmeDeferred.await()

                TvProgrammeParser.getTvProgrammes(app.assets.open("tv_program.xml"))


            } catch (e: Exception) {
                print("error found ${e.localizedMessage}")
            }

        }
    }
}