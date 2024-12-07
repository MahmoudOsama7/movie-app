package com.example.home.data.pager

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.resource.Resource
import com.example.resource.Status

class PagingSource<T : Any>(
    private val requestLoadingStateListener: RequestLoadingStateListener? = null,
    private val fetchData: suspend (page: Int) -> Resource<List<T>>,
    private val fetchCachedData: suspend (page: Int) -> Resource<List<T>>,
) : PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: 1
        return try {
            requestLoadingStateListener?.onLoading()
            val resource = fetchData(page)
            when (resource.status) {
                Status.SUCCESS -> {
                    val data = resource.data ?: emptyList()
                    requestLoadingStateListener?.onFinishLoading()
                    LoadResult.Page(
                        data = data,
                        prevKey = if (page == 1) null else page - 1,
                        nextKey = if (data.isEmpty()) null else page + 1
                    )
                }
                Status.ERROR -> {
                    val cachedData = fetchCachedData(page)
                    requestLoadingStateListener?.onError(resource.message.orEmpty())
                    if(cachedData.data?.isNotEmpty()==true)
                        LoadResult.Page(
                            data = cachedData.data?: listOf(),
                            prevKey = if (page == 1) null else page - 1,
                            nextKey = if (cachedData.data?.isEmpty()==true) null else page + 1
                        )
                    else
                        LoadResult.Error(Throwable(resource.message.orEmpty()))
                }
                else -> {
                    val cachedData = fetchCachedData(page)
                    requestLoadingStateListener?.onError(resource.message.orEmpty())
                    if(cachedData.data?.isNotEmpty()==true)
                        LoadResult.Page(
                            data = cachedData.data?: listOf(),
                            prevKey = if (page == 1) null else page - 1,
                            nextKey = if (cachedData.data?.isEmpty()==true) null else page + 1
                        )
                    else
                        LoadResult.Error(Throwable(resource.message.orEmpty()))
                }
            }
        } catch (e: Exception) {
            val cachedData = fetchCachedData(page)
            requestLoadingStateListener?.onError("can't fetch data")
            if(cachedData.data?.isNotEmpty()==true)
                LoadResult.Page(
                    data = cachedData.data?: listOf(),
                    prevKey = if (page == 1) null else page - 1,
                    nextKey = if (cachedData.data?.isEmpty()==true) null else page + 1
                )
            else
                LoadResult.Error(Throwable("cant fetch data"))
        }
    }
}

fun <T : Any> createPager(
    requestLoadingStateListener: RequestLoadingStateListener? = null,
    pageSize: Int=20,
    fetchData: suspend (page: Int) -> Resource<List<T>>,
    fetchCachedData: suspend (page: Int) -> Resource<List<T>>
): Pager<Int, T> {
    return Pager(
        config = PagingConfig(pageSize = pageSize, enablePlaceholders = false),
        pagingSourceFactory = { PagingSource(requestLoadingStateListener, fetchData,fetchCachedData) }
    )
}

interface RequestLoadingStateListener {
    suspend fun onLoading()
    suspend fun onFinishLoading()
    suspend fun onError(error: String)
}
