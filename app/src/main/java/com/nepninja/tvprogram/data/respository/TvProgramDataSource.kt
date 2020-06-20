package com.nepninja.tvprogram.data.respository

import android.util.Log
import androidx.paging.PositionalDataSource
import com.nepninja.tvprogram.data.model.TvProgram
import com.nepninja.tvprogram.data.remote.TvProgramService
import com.nepninja.tvprogram.utils.TvProgrammeParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class TvProgramDataSource(
    private val scope: CoroutineScope,
    private val service: TvProgramService
) : PositionalDataSource<TvProgram>() {
    val TAG = TvProgramDataSource::class.simpleName
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TvProgram>) {
        scope.launch {
            try {
                val response =
                    service.getProgrammesAsync(
                        params.startPosition,
                        params.startPosition + params.loadSize
                    ).await()
                val tvPrograms = TvProgrammeParser.getTvProgrammes(response.byteStream())
                callback.onResult(tvPrograms)
            } catch (e: Exception) {
                Log.e(TAG, "loadRange ${e.localizedMessage}")
            }
        }
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<TvProgram>) {
        scope.launch {
            try {
                val response = service.getProgrammesAsync(
                    params.requestedStartPosition,
                    params.requestedLoadSize
                ).await()
                val tvPrograms = TvProgrammeParser.getTvProgrammes(response.byteStream())
                callback.onResult(
                    tvPrograms,
                    params.requestedStartPosition,
                    params.requestedLoadSize
                )
            } catch (e: Exception) {
                Log.e(TAG, "loadInitial ${e.localizedMessage}")
            }
        }
    }

}