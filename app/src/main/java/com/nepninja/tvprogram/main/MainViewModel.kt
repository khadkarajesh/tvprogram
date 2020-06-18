package com.nepninja.tvprogram.main

import android.app.Application
import androidx.lifecycle.ViewModel
import com.nepninja.tvprogram.base.BaseViewModel
import com.nepninja.tvprogram.data.remote.TvProgramService

class MainViewModel(
    private val app: Application,
    private val api: TvProgramService
) : BaseViewModel(app) {}