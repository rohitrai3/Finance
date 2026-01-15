package com.rohitrai.finance.ui.component

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.startActivityForResult
import com.rohitrai.finance.R
import com.rohitrai.finance.ui.theme.DarkGray
import com.rohitrai.finance.ui.theme.White

const val DATA_FILE_NAME = "finance_data.csv"

@Composable
fun TopBar(activity: Activity) {
    Row(
        horizontalArrangement = Arrangement.End,
        modifier = Modifier
            .background(DarkGray)
            .fillMaxWidth()
            .statusBarsPadding(),
        verticalAlignment = Alignment.Bottom
    ) {
        Text(
            color = White,
            modifier = Modifier.clickable(onClick = { importData(activity) }),
            text = stringResource(R.string.import_label)
        )
        Text(
            color = White,
            modifier = Modifier
                .clickable(onClick = { exportData(activity) })
                .padding(16.dp, 0.dp),
            text = stringResource(R.string.export_label)
        )
    }
}

fun importData(activity: Activity) {
    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
        addCategory(Intent.CATEGORY_OPENABLE)
        type = "text/*"
        putExtra(Intent.EXTRA_TITLE, DATA_FILE_NAME)
    }

    startActivityForResult(activity, intent, 1, null)
}

fun exportData(activity: Activity) {
    val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
        addCategory(Intent.CATEGORY_OPENABLE)
        type = "text/csv"
        putExtra(Intent.EXTRA_TITLE, DATA_FILE_NAME)
    }

    startActivityForResult(activity, intent, 2, null)
}
