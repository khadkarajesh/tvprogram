package com.nepninja.tvprogram.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.nepninja.tvprogram.base.BaseViewModel
import com.nepninja.tvprogram.data.model.TvProgram
import com.nepninja.tvprogram.data.remote.TvProgramService
import com.nepninja.tvprogram.data.respository.TvProgramDataSource
import com.nepninja.tvprogram.utils.TvProgrammeParser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class MainViewModel(
    app: Application,
    private val api: TvProgramService
) : BaseViewModel(app) {
    fun getChannels(): Flow<PagingData<TvProgram>> {
        return Pager(
            config = PagingConfig(pageSize = 10, enablePlaceholders = true, initialLoadSize = 15),
            pagingSourceFactory = { TvProgramDataSource(api) }
        ).flow.cachedIn(viewModelScope)
    }
}