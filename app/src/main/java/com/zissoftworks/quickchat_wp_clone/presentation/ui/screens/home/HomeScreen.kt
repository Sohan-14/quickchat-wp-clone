package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.chat.ChatScreen
import com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.home.components.HomeTopBar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel<HomeViewModel>()
){
    val tabs = listOf("CHATS", "STATUS", "CALLS")

    val pagerState = rememberPagerState(initialPage = 0,  pageCount = { tabs.size })

    val unreadCount by viewModel.unreadChats.collectAsState()

    Scaffold(
        topBar = {
            HomeTopBar(
                pagerState = pagerState,
                unreadCount = unreadCount,
                tabs = tabs,
                viewModel = viewModel
            )
        }
    ) { _ ->
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxHeight()
        ) { page ->
            when (page) {
                0 -> ChatScreen(navController = navController, searchQuery = viewModel.searchQuery)
                1 -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Status Screen", modifier = Modifier.padding(16.dp))
                }
                2 -> Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Calls Screen", modifier = Modifier.padding(16.dp))
                }
            }
        }
    }

}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Preview(showSystemUi = true)
fun HomeScreenPreview(){
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}




