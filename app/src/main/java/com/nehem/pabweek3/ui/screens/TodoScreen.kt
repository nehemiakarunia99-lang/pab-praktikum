package com.nehem.pabweek3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.nehem.pabweek3.data.TodoItem
import com.nehem.pabweek3.navigation.LocalBackStack
import com.nehem.pabweek3.navigation.Route

/**
 * TodoScreen - Tugas Praktikum PAB (Week 6 + Week 9 + Week Hilt)
 * Week 6: Lazy List, Alert Dialog, Bottom Sheet
 * Week 9: Data layer persistence menggunakan DataStore (lihat TodoRepository & TodoViewModel)
 * Hilt: TodoViewModel sekarang di-inject otomatis lewat hiltViewModel()
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    viewModel: TodoViewModel = hiltViewModel()
) {
    val backStack = LocalBackStack.current

    // STATE: daftar todo sekarang diambil dari ViewModel (sumbernya DataStore)
    val todos by viewModel.todos.collectAsState()

    // STATE: kontrol Bottom Sheet (tambah tugas)
    var showBottomSheet by remember { mutableStateOf(false) }
    var newTodoText by remember { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState()

    // STATE: kontrol Alert Dialog (konfirmasi hapus)
    var todoToDelete by remember { mutableStateOf<TodoItem?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("My To-Do List") },
                navigationIcon = {
                    IconButton(onClick = { backStack.removeLastOrNull() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showBottomSheet = true }) {
                Icon(Icons.Default.Add, contentDescription = "Tambah Tugas")
            }
        }
    ) { innerPadding ->

        if (todos.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    "Belum ada tugas. Tekan + untuk menambah.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        } else {
            // LAZY LIST: menampilkan seluruh todo secara efisien
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(todos, key = { it.id }) { todo ->
                    Card(elevation = CardDefaults.cardElevation(2.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Checkbox(
                                checked = todo.isDone,
                                onCheckedChange = { checked ->
                                    // Update lewat ViewModel, otomatis tersimpan ke DataStore
                                    viewModel.toggleDone(todo.id, checked)
                                }
                            )

                            Text(
                                text = todo.title,
                                modifier = Modifier.weight(1f),
                                textDecoration = if (todo.isDone) TextDecoration.LineThrough else null,
                                color = if (todo.isDone)
                                    MaterialTheme.colorScheme.onSurfaceVariant
                                else
                                    MaterialTheme.colorScheme.onSurface
                            )

                            IconButton(onClick = { todoToDelete = todo }) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Hapus",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // ALERT DIALOG: konfirmasi hapus, hanya muncul jika todoToDelete != null
    if (todoToDelete != null) {
        AlertDialog(
            onDismissRequest = { todoToDelete = null },
            title = { Text("Hapus Tugas?") },
            text = { Text("Yakin ingin menghapus \"${todoToDelete?.title}\"?") },
            confirmButton = {
                TextButton(onClick = {
                    viewModel.deleteTodo(todoToDelete!!.id)
                    todoToDelete = null
                }) {
                    Text("Hapus", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { todoToDelete = null }) {
                    Text("Batal")
                }
            }
        )
    }

    // BOTTOM SHEET: form tambah tugas baru, hanya muncul jika showBottomSheet true
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
                newTodoText = ""
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
            ) {
                Text(
                    "Tambah Tugas Baru",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = newTodoText,
                    onValueChange = { newTodoText = it },
                    label = { Text("Nama tugas") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (newTodoText.isNotBlank()) {
                            viewModel.addTodo(newTodoText.trim())
                            newTodoText = ""
                            showBottomSheet = false
                        }
                    },
                    enabled = newTodoText.isNotBlank(),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Simpan")
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}