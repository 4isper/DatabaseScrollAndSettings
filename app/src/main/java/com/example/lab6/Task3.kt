package com.example.lab6

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Task3(){
    val boxWithColor = listOf(
        mapOf("colorBox" to Color.White, "colorText" to Color.Black, "text" to "Белый"),
        mapOf("colorBox" to Color.Black, "colorText" to Color.White, "text" to "Черный"),
        mapOf("colorBox" to Color.Blue, "colorText" to Color.White, "text" to "Синий")
    )
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(boxWithColor){ item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(86.dp)
                    .padding(vertical = 8.dp)
                    .background(color = item["colorBox"] as Color),
                contentAlignment = Alignment.Center
            ) {
                Text(text = item["text"] as String,
                    color = item["colorText"] as Color,
                    fontSize = 30.sp)
            }
        }

    }
}