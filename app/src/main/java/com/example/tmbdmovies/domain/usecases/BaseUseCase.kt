package com.example.tmbdmovies.domain.usecases

import androidx.paging.PagingConfig

open class BaseUseCase {
     val pagingConfig = PagingConfig(pageSize = 20)
}