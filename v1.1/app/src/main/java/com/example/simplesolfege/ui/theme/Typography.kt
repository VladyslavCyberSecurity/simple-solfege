package com.example.simplesolfege.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val Typography = Typography(
    headlineSmall = Typography().headlineSmall.copy(
        fontSize = 24.sp,
        fontWeight = FontWeight.SemiBold
    ),
    bodyMedium = Typography().bodyMedium.copy(
        fontSize = 14.sp
    )
)
