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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class Item(
    val colorBox: Color,
    val colorText: Color,
    val text: String
)

@Composable
fun Task1(){
    val boxWithColor = listOf(
        Item(Color.White, Color.Black, "Белый"),
        Item(Color.Black, Color.White, "Черный"),
        Item(Color.Blue, Color.White, "Синий")
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
                    .background(color = item.colorBox),
                contentAlignment = Alignment.Center
            ) {
               Text(item.text, color = item.colorText,
                   fontSize = 30.sp)
            }
        }

    }
}