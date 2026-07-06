package com.nehem.pabweek3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nehem.pabweek3.navigation.LocalBackStack
import com.nehem.pabweek3.navigation.Route
import com.nehem.pabweek3.ui.theme.PabWeek3Theme

/**
 * CourseDetailScreen - Week 5
 * Menampilkan data (courseId, courseName) yang dikirim dari HomeScreen
 * melalui Route.CourseDetail (Passing Parameter).
 * Dilengkapi tombol Back Arrow (Back Navigation).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CourseDetailScreen(courseId: String, courseName: String) {
    val backStack = LocalBackStack.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Course Detail") },
                navigationIcon = {
                    IconButton(onClick = { backStack.removeLastOrNull() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(elevation = CardDefaults.cardElevation(4.dp), modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = courseName,
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Kode Mata Kuliah: $courseId",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "Data ini (courseId & courseName) diterima dari HomeScreen " +
                            "melalui parameter Route.CourseDetail, membuktikan proses Passing Parameter " +
                            "berhasil dilakukan menggunakan Navigation 3.",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CourseDetailScreenPreview() {
    PabWeek3Theme {
        CompositionLocalProvider(
            LocalBackStack provides androidx.navigation3.runtime.rememberNavBackStack(Route.Home)
        ) {
            CourseDetailScreen(courseId = "IF-4201", courseName = "Pemrograman Aplikasi Bergerak")
        }
    }
}
