package com.example.finance.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import com.example.finance.data.TransactionType
import kotlin.collections.forEach

@Composable
fun SelectType(types: List<TransactionType>, selectedType: TransactionType, onTypeSelected: (TransactionType) -> Unit) {
    Row(Modifier.selectableGroup()) {
        types.forEach { type ->
            Row(
                Modifier.selectable(
                    selected = (type == selectedType),
                    onClick = { onTypeSelected(type) },
                    role = Role.RadioButton,
                )
            ) {
                RadioButton(
                    selected = type == selectedType,
                    onClick = null,
                )
                Text(type.toString())
            }
        }
    }
}
