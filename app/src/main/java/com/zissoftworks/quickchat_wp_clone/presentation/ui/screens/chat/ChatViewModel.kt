package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zissoftworks.quickchat_wp_clone.domain.model.chat.ChatItem
import com.zissoftworks.quickchat_wp_clone.domain.usecase.chat.GetAllChatsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val getAllChatsUseCase: GetAllChatsUseCase,
) : ViewModel() {
    private val _chatList = MutableStateFlow<List<ChatItem>>(emptyList())
    val chatList: StateFlow<List<ChatItem>> = _chatList.asStateFlow()

    private val _event = MutableSharedFlow<ChatUiEvent>()
    val event = _event.asSharedFlow()


    fun navigateToOneToOneChat(){
        viewModelScope.launch {
            _event.emit(ChatUiEvent.NavigateToOneToOneChat)
        }
    }

    init {
        viewModelScope.launch {
            getAllChatsUseCase().collect { chatItems ->
                _chatList.value = chatItems
            }
        }
    }

}