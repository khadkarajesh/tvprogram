package com.nepninja.tvprogram.data.respository

import android.util.Log
import androidx.paging.PagingSource
import com.nepninja.tvprogram.data.model.TvProgram
import com.nepninja.tvprogram.data.remote.TvProgramService
import com.nepninja.tvprogram.utils.TvProgrammeParser
import retrofit2.HttpException
import java.io.IOException

private const val STARTING_POSITION = 1

class TvProgramDataSource(
    private val service: TvProgramService
) : PagingSource<Int, TvProgram>() {
    val TAG = TvProgramDataSource::class.simpleName

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TvProgram> {
        val position = params.key ?: STARTING_POSITION
        return try {
            Log.d(TAG, "$position  ${params.loadSize + position}")
            val response = service.getProgrammesAsync(position, params.loadSize + position).await()
            val tvPrograms = TvProgrammeParser.getTvProgrammes(response.byteStream())
            LoadResult.Page(
                tvPrograms,
                prevKey = null,
                nextKey = if (tvPrograms.isEmpty()) null else position + params.loadSize
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}