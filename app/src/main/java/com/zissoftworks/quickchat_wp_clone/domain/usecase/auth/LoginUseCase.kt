package com.zissoftworks.quickchat_wp_clone.domain.usecase.auth

import com.zissoftworks.quickchat_wp_clone.domain.repository.AuthMockupRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthMockupRepository
) {
    operator fun invoke(phoneNumber: String) : Flow<Boolean>{
        return repository.login(phoneNumber = phoneNumber)
    }
}