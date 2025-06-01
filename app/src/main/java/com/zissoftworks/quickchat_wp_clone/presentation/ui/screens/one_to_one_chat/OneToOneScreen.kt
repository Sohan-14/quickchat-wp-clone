package com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.one_to_one_chat

import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.one_to_one_chat.components.AttachmentBottomSheet
import com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.one_to_one_chat.components.ChatInputBar
import com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.one_to_one_chat.components.ChatMessageItem
import com.zissoftworks.quickchat_wp_clone.presentation.ui.screens.one_to_one_chat.components.ChatTopBar
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OneToOneChatScreen (
    navController: NavController,
    viewModel: OneToOneViewModel = hiltViewModel<OneToOneViewModel>()
) {
    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsState()

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden
    )
    val coroutineScope = rememberCoroutineScope()

    val documentLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument(),
        onResult = { uri ->
            uri?.let { viewModel.sendDocumentMessage(it) }
        }
    )

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            uri?.let { viewModel.sendImageMessage(it) }
        }
    )

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview(),
        onResult = { bitmap ->
            bitmap?.let { viewModel.sendCameraImageMessage(it) }
        }
    )

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            if (isGranted) {
                cameraLauncher.launch(null)
            }
        }
    )



    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetContent = {
            AttachmentBottomSheet { selected ->
                coroutineScope.launch { sheetState.hide() }
                when (selected) {
                    "Document" -> documentLauncher.launch(arrayOf("*/*"))
                    "Camera" -> {
                        val cameraPermissionGranted = ContextCompat.checkSelfPermission(
                            context, android.Manifest.permission.CAMERA
                        ) == PackageManager.PERMISSION_GRANTED

                        if (cameraPermissionGranted) {
                            cameraLauncher.launch(null)
                        } else {
                            cameraPermissionLauncher.launch(android.Manifest.permission.CAMERA)
                        }
                    }
                    "Gallery" -> galleryLauncher.launch("image/*")
                }
                coroutineScope.launch { sheetState.hide() }
            }
        }
    ){
        Scaffold (
            topBar = {
                ChatTopBar(
                    title = "Sohan",
                    subtitle = "Active Now",
                    onBackPressed = { navController.popBackStack() }
                )
            },
            bottomBar = {
                ChatInputBar(
                    message = uiState.currentMessage,
                    onMessageChange = viewModel::onMessageChange,
                    onSendClick = viewModel::onSendMessage,
                    onReceivedClick = viewModel::onReceivedMessage,
                    onAttachmentClick = {
                        coroutineScope.launch { sheetState.show() }
                    }

                )
            }
        ) { paddingValues ->
            LazyColumn(
                contentPadding = paddingValues,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFFECE5DD))
            ) {
                items(
                    count = uiState.messages.size,
                    itemContent = { index ->
                        ChatMessageItem(message = uiState.messages[index])
                    }
                )
            }
        }
    }
}