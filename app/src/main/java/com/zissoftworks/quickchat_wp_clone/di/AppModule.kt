package com.zissoftworks.quickchat_wp_clone.di

import com.zissoftworks.quickchat_wp_clone.data.repository.AuthMockRepositoryImpl
import com.zissoftworks.quickchat_wp_clone.data.repository.ChatMockRepositoryImpl
import com.zissoftworks.quickchat_wp_clone.domain.repository.AuthMockupRepository
import com.zissoftworks.quickchat_wp_clone.domain.repository.ChatMockupRepository
import com.zissoftworks.quickchat_wp_clone.domain.usecase.auth.LoginUseCase
import com.zissoftworks.quickchat_wp_clone.domain.usecase.chat.AddMessagesUseCase
import com.zissoftworks.quickchat_wp_clone.domain.usecase.chat.GetAllChatsUseCase
import com.zissoftworks.quickchat_wp_clone.domain.usecase.chat.GetMessagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthMockRepository(): AuthMockupRepository = AuthMockRepositoryImpl()

    @Provides
    @Singleton
    fun provideChatMockRepository(): ChatMockupRepository = ChatMockRepositoryImpl()

    @Provides
    @Singleton
    fun provideLoginUseCase(repository: AuthMockupRepository): LoginUseCase = LoginUseCase(repository)

    @Provides
    @Singleton
    fun provideGetAllChatsUseCase(repository: ChatMockupRepository): GetAllChatsUseCase = GetAllChatsUseCase(repository)

    @Provides
    @Singleton
    fun provideGetMessageUseCase(repository: ChatMockupRepository): GetMessagesUseCase = GetMessagesUseCase(repository)

    @Provides
    @Singleton
    fun provideAddMessageUseCase(repository: ChatMockupRepository): AddMessagesUseCase = AddMessagesUseCase(repository)
}