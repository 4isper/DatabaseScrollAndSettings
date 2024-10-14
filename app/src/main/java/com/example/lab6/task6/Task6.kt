package com.example.lab6.task6

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Task6(modifier: Modifier){
    val context = LocalContext.current
    val database = NoteDatabase.getDatabase(context)
    val repository = NoteRepository(database.noteDao())
    val viewModel: NoteViewModel = viewModel(factory = NoteViewModelFactory(repository))
    NoteApp(modifier = modifier, viewModel)
}

@Composable
fun NoteApp(modifier: Modifier, viewModel: NoteViewModel) {
    var noteText by remember { mutableStateOf("") }
    val notes by viewModel.allNotes.collectAsState(initial = emptyList())
    val scope = rememberCoroutineScope()

    Column(modifier = modifier.padding(16.dp)) {
        OutlinedTextField(
            value = noteText,
            onValueChange = { noteText = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Введите заметку", fontSize = 20.sp) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            modifier = Modifier
                .align(Alignment.End),
            onClick = {
            if (noteText.isNotEmpty()) {
                viewModel.insert(noteText)
                noteText = ""
            }
        }) {
            Text("Добавить", fontSize = 20.sp)
        }
        LazyColumn(modifier = Modifier.fillMaxHeight()) {
            items(notes) { note ->
                NoteItem(note, onClick = {
                    viewModel.delete(note)
                })
            }
        }
    }
}

@Composable
fun NoteItem(note: Note, onClick: () -> Unit) {
    Text(
        text = note.text,
        fontSize = 20.sp,
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(top = 10.dp)
    )
    HorizontalDivider(thickness = 2.dp)
}