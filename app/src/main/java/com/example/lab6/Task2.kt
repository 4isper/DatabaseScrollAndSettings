package com.example.lab6

import androidx.collection.mutableIntListOf
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Task2(modifier: Modifier){
    var items by remember { mutableStateOf(listOf<Pair<Int,Int>>()) }
    var counter by remember { mutableStateOf(1) }

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Button(
                onClick = {
                    items = items + (counter to counter)
                    counter++
                }
            ) {
                Text("Добавить")
            }
        }
        items(items) {item ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(86.dp)
                    .padding(vertical = 8.dp)
                    .background(color = Color.LightGray)
                    .clickable { items = items.filter { it.second != item.second } },
                contentAlignment = Alignment.Center
            ) {
                Text("Элемент ${item.first}",
                    fontSize = 30.sp,
                    color = Color.Black)
            }
        }

    }
}