package com.example.vaniga.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.vaniga.data.mapper.toDomain
import com.example.vaniga.domain.model.Product


class ProductPagingSource(
    private val api: ProductApi,
    private val categoryId: Int? = null
) : PagingSource<Int, Product>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        val position = params.key ?: 0
        return try {
            val response = api.getProducts(offset = position, limit = params.loadSize, categoryId = categoryId)

            LoadResult.Page(
                data = response.map { it.toDomain() },
                prevKey = if (position == 0) null else position - params.loadSize,
                nextKey = if (response.isEmpty()) null else position + params.loadSize
            )
        } catch (e: java.io.IOException) {
            LoadResult.Error(Exception("Please check your internet connection."))
        } catch (e: retrofit2.HttpException) {
            LoadResult.Error(Exception("Server error. Please try again later."))
        } catch (e: Exception) {
            LoadResult.Error(Exception("An unexpected error occurred."))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(state.config.pageSize)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(state.config.pageSize)
        }
    }
}