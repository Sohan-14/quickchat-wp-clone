package com.zissoftworks.quickchat_wp_clone.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthMockupRepository {
    fun login(phoneNumber :String) : Flow<Boolean>
}