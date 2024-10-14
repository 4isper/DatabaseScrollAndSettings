package com.example.lab6.task8

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun Task8(modifier: Modifier) {
    val context = LocalContext.current
    val configurationGroup = ConfigurationGroup(context)
    App(modifier = modifier, configurationGroup)
}

@Composable
fun App(modifier: Modifier, configurationGroup: ConfigurationGroup) {
    var text by remember { mutableStateOf(configurationGroup.textValue) }
    var isChecked by remember { mutableStateOf(configurationGroup.checkboxValue) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
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
                    configurationGroup.textValue = text
                    configurationGroup.checkboxValue = isChecked
                },
            ) {
                Text("Сохранить")
            }
        }
    }
}