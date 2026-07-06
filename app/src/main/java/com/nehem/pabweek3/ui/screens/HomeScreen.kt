package com.nehem.pabweek3.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nehem.pabweek3.navigation.LocalBackStack
import com.nehem.pabweek3.navigation.Route
import com.nehem.pabweek3.ui.theme.PabWeek3Theme

data class Course(val nama: String, val kode: String, val jumlahMahasiswa: Int)

private val dummyCourses = listOf(
    Course("Pengembangan Aplikasi Bergerak", "IF-4201", 42),
    Course("Rekayasa Perangkat Lunak", "IF-4105", 38),
    Course("Kecerdasan Buatan", "IF-4310", 45),
)

/**
 * HomeScreen - daftar mata kuliah SCAS
 * Week 5: tombol Profile & QR Code (Basic Routing via backStack.add),
 * dan klik item Course akan membawa ID+Nama ke CourseDetail (Passing Parameter).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val backStack = LocalBackStack.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("SCAS") },
                actions = {
                    IconButton(onClick = { backStack.add(Route.GenerateQr) }) {
                        Icon(Icons.Default.CheckCircle, contentDescription = "Generate QR")
                    }
                    IconButton(onClick = { backStack.add(Route.Profile) }) {
                        Icon(Icons.Default.AccountCircle, contentDescription = "Profile")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* join/add course */ }) {
                Icon(Icons.Default.Add, contentDescription = "Join Course")
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {

            Column(modifier = Modifier.padding(20.dp)) {
                Text(
                    text = "Hello, Nehemia.",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Your enrolled courses (tap to see detail)",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            LazyColumn(
                modifier = Modifier.padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(dummyCourses) { course ->
                    Card(
                        elevation = CardDefaults.cardElevation(2.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                // PASSING PARAMETER: kirim kode & nama course ke CourseDetail
                                backStack.add(
                                    Route.CourseDetail(
                                        courseId = course.kode,
                                        courseName = course.nama
                                    )
                                )
                            }
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(course.nama, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                "${course.kode} • ${course.jumlahMahasiswa} mahasiswa",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PabWeek3Theme {
        CompositionLocalProvider(
            LocalBackStack provides androidx.navigation3.runtime.rememberNavBackStack(Route.Home)
        ) {
            HomeScreen()
        }
    }
}
