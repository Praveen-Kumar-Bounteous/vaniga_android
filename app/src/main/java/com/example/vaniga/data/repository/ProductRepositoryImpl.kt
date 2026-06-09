package com.example.vaniga.data.repository

import com.example.vaniga.core.common.Resource
import com.example.vaniga.data.mapper.toDomain
import com.example.vaniga.data.remote.ProductApi
import com.example.vaniga.data.remote.ProductPagingSource
import com.example.vaniga.domain.model.Product
import com.example.vaniga.domain.repository.ProductRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.vaniga.domain.model.Category

class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi
) : ProductRepository {

//    override fun getProducts(): Flow<Resource<List<Product>>> = flow {
//        emit(Resource.Loading())
//        try {
//            val remoteData = api.getProducts()
//            emit(Resource.Success(remoteData.map { it.toDomain() }))
//        } catch (e: HttpException) {
//            emit(Resource.Error("An unexpected error occurred"))
//        } catch (e: IOException) {
//            emit(Resource.Error("Couldn't reach server. \nCheck your internet connection."))
//        }
//    }

    override fun getProductsPaging(categoryId: Int?): Flow<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 2,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ProductPagingSource(api, categoryId) }
        ).flow
    }

    override fun getCategories(): Flow<Resource<List<Category>>> = flow {
        emit(Resource.Loading())
        try {
            val categories = api.getCategories().map { Category(it.id, it.name) }
            emit(Resource.Success(categories))
        } catch (e: Exception) {
            emit(Resource.Error("Couldn't load categories"))
        }
    }

    override fun getProductById(id: Int): Flow<Resource<Product>> = flow {
        emit(Resource.Loading())
        try {
            val product = api.getProductById(id).toDomain()
            emit(Resource.Success(product))
        } catch (e: Exception) {
            emit(Resource.Error("Product not found"))
        } catch (e: HttpException) {
            emit(Resource.Error("An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. \nCheck your internet connection."))
        }
    }
}