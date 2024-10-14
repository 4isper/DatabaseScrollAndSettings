package com.example.lab6.task7

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun Task7(modifier: Modifier) {
    val context = LocalContext.current
    App(modifier, context)
}

@Composable
fun App(modifier: Modifier, context: Context) {
    val scope = rememberCoroutineScope()

    var text by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

    val savedText by getTextFromPreferences(context).collectAsState(initial = "")
    val savedCheckbox by getCheckboxFromPreferences(context).collectAsState(initial = false)

    LaunchedEffect(savedText) { text = savedText }

    LaunchedEffect(savedCheckbox) { isChecked = savedCheckbox }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Введите текст") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Флажок")
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = {
                    scope.launch {
                        saveToPreferences(context, text, isChecked)
                    }
                },
                modifier = Modifier.align(Alignment.Bottom)
            ) {
                Text("Сохранить")
            }
        }
    }
}