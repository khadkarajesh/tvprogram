package com.nepninja.tvprogram.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nepninja.tvprogram.base.BaseViewModel
import com.nepninja.tvprogram.data.model.TvProgram
import com.nepninja.tvprogram.data.remote.TvProgramService
import com.nepninja.tvprogram.utils.TvProgrammeParser
import kotlinx.coroutines.launch


class MainViewModel(
    private val app: Application,
    private val api: TvProgramService
) : BaseViewModel(app) {
    var tvProgrammes: MutableLiveData<List<TvProgram>> = MutableLiveData()
    var from: Int = 1
    var to: Int = 20
    var initialLoad: Boolean = true
    var loading: MutableLiveData<Boolean> = MutableLiveData()


    fun getChannels() {
        loading.postValue(true)
        viewModelScope.launch {
            try {

                if (!initialLoad) {
                    from += 1
                    from *= 15
                    to = from + 15
                }
                Log.d("page", "$from - $to")
                val asyncProgrammes = api.getProgrammesAsync(from, to)
                val responseBody = asyncProgrammes.await()
                loading.postValue(false)
                val data = TvProgrammeParser.getTvProgrammes(responseBody.byteStream())
                if (data.isNotEmpty()) {
                    tvProgrammes.postValue(data)
                }
            } catch (e: Exception) {
                loading.postValue(false)
            }
        }
    }
}