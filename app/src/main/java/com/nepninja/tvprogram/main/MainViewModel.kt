package com.nepninja.tvprogram.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.nepninja.tvprogram.base.BaseViewModel
import com.nepninja.tvprogram.data.model.TvProgram
import com.nepninja.tvprogram.data.remote.TvProgramService
import com.nepninja.tvprogram.data.respository.TvProgramDataSource
import com.nepninja.tvprogram.utils.TvProgrammeParser
import kotlinx.coroutines.launch


class MainViewModel(
    app: Application,
    private val api: TvProgramService
) : BaseViewModel(app) {
    var tvProgrammes: MutableLiveData<List<TvProgram>> = MutableLiveData()
    var from: Int = 1
    var to: Int = 20
    var tvProgrammesPageList: LiveData<PagedList<TvProgram>> = MutableLiveData()

    init {
        val config = PagedList.Config.Builder()
            .setPrefetchDistance(10)
            .setPageSize(10)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()

        tvProgrammesPageList = initializedPagedListBuilder(config).build()
    }

    private fun initializedPagedListBuilder(config: PagedList.Config):
            LivePagedListBuilder<Int, TvProgram> {

        val dataSourceFactory = object : DataSource.Factory<Int, TvProgram>() {
            override fun create(): DataSource<Int, TvProgram> {
                return TvProgramDataSource(viewModelScope, api)
            }
        }
        return LivePagedListBuilder(dataSourceFactory, config)
    }

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