package com.rohitrai.finance.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import com.rohitrai.finance.FinanceDestination

@Composable
fun NavBar(
    currentScreen: FinanceDestination,
    onTabSelected: (FinanceDestination) -> Unit,
    screens: List<FinanceDestination>
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectableGroup()
    ) {
        screens.forEach { screen ->
            NavTab(
                modifier = Modifier.weight(1f),
                onSelected = { onTabSelected(screen) },
                selected = currentScreen == screen,
                text = screen.route
            )
        }
    }
}

@Composable
private fun NavTab(
    modifier: Modifier = Modifier,
    onSelected: () -> Unit,
    selected: Boolean,
    text: String
) {
    Row(
        modifier = modifier
            .background(color = if (selected) Color.Black else Color.DarkGray)
            .padding(16.dp)
            .selectable(
                onClick = onSelected,
                role = Role.Tab,
                selected = selected
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        Text( text )
    }
}
