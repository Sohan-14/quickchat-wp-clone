package com.zissoftworks.quickchat_wp_clone.data.repository

import com.zissoftworks.quickchat_wp_clone.domain.repository.AuthMockupRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class AuthMockRepositoryImpl : AuthMockupRepository{
    override fun login(phoneNumber: String): Flow<Boolean>  {
        return flowOf(true)
    }
}