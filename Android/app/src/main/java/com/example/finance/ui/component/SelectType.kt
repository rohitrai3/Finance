package com.example.finance.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.example.finance.data.TransactionType
import kotlin.collections.forEach

@Composable
fun SelectType(types: List<TransactionType>, selectedType: TransactionType, onTypeSelected: (TransactionType) -> Unit) {
    Row( modifier = Modifier.selectableGroup().fillMaxWidth() ) {
        types.forEach { type ->
            Row(
                modifier = Modifier.selectable(
                    selected = (type == selectedType),
                    onClick = { onTypeSelected(type) },
                    role = Role.RadioButton,
                ).weight(1f)
            ) {
                RadioButton(
                    selected = type == selectedType,
                    onClick = null,
                )
                Text(modifier = Modifier.padding(horizontal = 8.dp), text = type.toString())
            }
        }
    }
}
