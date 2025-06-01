package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.one_to_one_chat

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zissoftworks.quickchat_wp_clone.domain.model.chat.ChatMessage
import com.zissoftworks.quickchat_wp_clone.domain.model.chat.MessageType
import com.zissoftworks.quickchat_wp_clone.domain.usecase.chat.AddMessagesUseCase
import com.zissoftworks.quickchat_wp_clone.domain.usecase.chat.GetMessagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OneToOneViewModel @Inject constructor(
    private val getMessagesUseCase: GetMessagesUseCase,
    private val addMessagesUseCase: AddMessagesUseCase,
) : ViewModel(){

    private val _uiState = MutableStateFlow(OneToOneUiState())
    val uiState: StateFlow<OneToOneUiState> = _uiState

    fun onMessageChange(text: String) {
        _uiState.update { it.copy(currentMessage = text) }
    }

    fun onSendMessage() {
        val message = _uiState.value.currentMessage.trim()
        if (message.isNotEmpty()) {
            addMessagesUseCase(ChatMessage(
                sender = "You",
                content = message,
                type = MessageType.TEXT,
                isOwnMessage = true
            ))
            _uiState.update { it.copy(currentMessage = "") }
        }
    }

    fun onReceivedMessage() {
        val message = _uiState.value.currentMessage.trim()
        if (message.isNotEmpty()) {
            addMessagesUseCase(
                ChatMessage(
                    sender = "Friend",
                    content = message,
                    type = MessageType.TEXT,
                    isOwnMessage = false
                )
            )
            _uiState.update { it.copy(currentMessage = "") }
        }
    }

    fun sendImageMessage(imageUri: Uri) {
        addMessagesUseCase(
            ChatMessage(
                sender = "You",
                fileUrl = imageUri.toString(),
                type = MessageType.IMAGE,
                isOwnMessage = true
            )
        )
    }


    fun sendCameraImageMessage(bitmap: Bitmap) {
        addMessagesUseCase(
            ChatMessage(
                sender = "You",
                type = MessageType.IMAGE,
                cameraImage = bitmap,
                isOwnMessage = true
            )
        )
    }

    fun sendDocumentMessage(docUri: Uri) {
        addMessagesUseCase(
            ChatMessage(
                sender = "You",
                fileUrl = docUri.toString(),
                type = MessageType.DOCUMENT,
                isOwnMessage = true
            )
        )
    }

    init {
        viewModelScope.launch {
            getMessagesUseCase().collect { chatMessages ->
                _uiState.update { currentState ->
                    currentState.copy(messages = chatMessages)
                }
            }
        }
    }
}