package com.rohitrai.finance.ui.component

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable

@Composable
fun NotificationBar(snackBarHostState: SnackbarHostState) {
    SnackbarHost(hostState = snackBarHostState)
}