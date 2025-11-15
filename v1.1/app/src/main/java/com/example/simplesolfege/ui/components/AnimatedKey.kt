package com.example.simplesolfege.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.simplesolfege.logic.Note

@Composable
fun AnimatedBlackKey(
    note: Note,
    onPress: (Note) -> Unit
) {
    var pressed by remember { mutableStateOf(false) }

    val height by animateDpAsState(
        targetValue = if (pressed) 90.dp else 105.dp,
        animationSpec = spring(stiffness = 200f)
    )

    val shadow by animateDpAsState(
        targetValue = if (pressed) 6.dp else 14.dp
    )

    Box(
        modifier = Modifier
            .width(32.dp)
            .height(height)
            .shadow(shadow, RoundedCornerShape(6.dp))
            .background(Color.Black, RoundedCornerShape(6.dp))
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                pressed = true
                onPress(note)
                pressed = false
            }
    )
}
