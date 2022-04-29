package io.github.hoanv810.core.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.hoanv810.core.data.repository.ArticleRepositoryImpl
import io.github.hoanv810.core.domain.repositories.ArticleRepository

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {

    @Binds
    abstract fun bindArticleRepository(impl: ArticleRepositoryImpl): ArticleRepository
}