package com.nepninja.tvprogram.main

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.nepninja.tvprogram.base.BaseViewModel
import com.nepninja.tvprogram.data.model.TvProgram
import com.nepninja.tvprogram.data.remote.TvProgramService
import com.nepninja.tvprogram.utils.TvProgrammeParser
import kotlinx.coroutines.launch


class MainViewModel(
    app: Application,
    private val api: TvProgramService
) : BaseViewModel(app) {
    var tvProgrammes: MutableLiveData<List<TvProgram>> = MutableLiveData()
    var from: Int = 1
    var to: Int = 20

    fun getChannels() {
        showLoading.postValue(true)
        viewModelScope.launch {
            try {
                val asyncProgrammes = api.getProgrammesAsync(from, to)
                val responseBody = asyncProgrammes.await()
                val data = TvProgrammeParser.getTvProgrammes(responseBody.byteStream())
                if (data.isNotEmpty()) {
                    tvProgrammes.postValue(data)
                } else {
                    showEmptyData()
                }
            } catch (e: Exception) {
                showSnackBar.postValue(e.localizedMessage)
            } finally {
                showLoading.postValue(false)
            }
        }
    }

    private fun showEmptyData() {
        showNoData.postValue(tvProgrammes.value.isNullOrEmpty())
    }
}