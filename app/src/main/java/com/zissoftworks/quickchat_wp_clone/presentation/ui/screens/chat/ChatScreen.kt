package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.chat

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.zissoftworks.quickchat_wp_clone.presentation.navigation.Routes
import com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.chat.components.ChatItemCard
import kotlinx.coroutines.flow.StateFlow

@Composable
fun ChatScreen (
    navController: NavHostController,
    searchQuery: StateFlow<String>,
    viewModel: ChatViewModel = hiltViewModel<ChatViewModel>()
) {

    val eventFlow = viewModel.event
    val query by searchQuery.collectAsState()
    val chats by viewModel.chatList.collectAsState()

    LaunchedEffect(Unit) {
        eventFlow.collect { event ->
            when (event) {
                is ChatUiEvent.NavigateToOneToOneChat -> {
                    navController.navigate(Routes.OneToOneChat.route)
                }
            }
        }
    }


    val filteredChats = remember(chats, query) {
        if (query.isNotBlank()) {
            val regex = Regex(query.trim(), RegexOption.IGNORE_CASE)
            chats.filter { chat -> regex.containsMatchIn(chat.name) }
        } else {
            chats
        }
    }

    LazyColumn{
        items(
            count = filteredChats.size
        ) { index ->
            ChatItemCard(
                chat = filteredChats[index],
                onClick = {_ -> viewModel.navigateToOneToOneChat()}
            )
        }
    }
}
