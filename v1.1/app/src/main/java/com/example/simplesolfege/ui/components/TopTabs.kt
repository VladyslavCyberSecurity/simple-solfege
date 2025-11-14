package com.example.simplesolfege.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopTabs(
    tabs: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit
) {
    Row(modifier = Modifier.fillMaxWidth()) {
        tabs.forEachIndexed { i, label ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onSelect(i) }
                    .padding(vertical = 4.dp),
                horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
            ) {
                Text(
                    text = label,
                    color = if (i == selectedIndex)
                        MaterialTheme.colorScheme.primary
                    else
                        MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5f)
                )
                if (i == selectedIndex)
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .width(40.dp)
                            .background(MaterialTheme.colorScheme.primary)
                    )
            }
        }
    }
}
