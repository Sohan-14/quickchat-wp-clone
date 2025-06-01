package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.home.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Badge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.zissoftworks.quickchat_wp_clone.R
import com.zissoftworks.quickchat_wp_clone.presentation.theme.LightPrimary
import com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.home.HomeViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(
    tabs:  List<String>,
    pagerState: PagerState,
    unreadCount: Int,
    viewModel: HomeViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val currentQuery by viewModel.searchQuery.collectAsState()

    val searchState = rememberTextFieldState(currentQuery)


    LaunchedEffect(searchState.text) {
        viewModel.onSearchQueryChange(searchState.text.toString())
    }


    Column(
        modifier = Modifier
            .wrapContentHeight()
    ) {
        TopAppBar(
            modifier = Modifier
                .wrapContentHeight(),

            title = {
                if (viewModel.isSearching) {
                    SearchBar(
                        state = searchState
                    )
                } else {
                    Text("QuickChat", color = Color.White)
                }
            },

            actions = {
                IconButton(onClick = { viewModel.toggleSearch() }) {
                    Icon(
                        imageVector = if (viewModel.isSearching) Icons.Default.Close else Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "Menu",
                        tint = Color.White
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = LightPrimary)
        )

        Row (
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .background(LightPrimary)
        ) {
            IconButton(onClick = {}) {
                Image(
                    painter = painterResource(id = R.drawable.camera_icon),
                    contentDescription = "App Logo",
                    modifier = Modifier.run { size(24.dp) }
                )
            }
            TabRow(
                modifier = Modifier.alignByBaseline(),
                selectedTabIndex = pagerState.currentPage,
                containerColor = LightPrimary,
                contentColor = Color.White,
                indicator = { tabPositions ->
                    TabRowDefaults.SecondaryIndicator(
                        Modifier.tabIndicatorOffset(tabPositions[pagerState.currentPage]),
                        color = Color.White
                    )
                }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = pagerState.currentPage == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            if (index == 0) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(text = title)
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Badge(
                                        containerColor = Color.White
                                    ) {
                                        Text(
                                            text = unreadCount.toString(),
                                            color = MaterialTheme.colorScheme.primary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    }
                                }
                            }
                            else {
                                Text(text = title)
                            }
                        },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.White.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}