package com.nehem.pabweek3.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nehem.pabweek3.navigation.LocalBackStack
import com.nehem.pabweek3.navigation.Route
import com.nehem.pabweek3.ui.theme.PabWeek3Theme

/**
 * GenerateQrScreen - fitur presensi SCAS
 * Week 5: dilengkapi Back Arrow (Back Navigation) via LocalBackStack.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenerateQrScreen() {
    val backStack = LocalBackStack.current
    var countdown by remember { mutableStateOf(10) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Session QR Code") },
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
            Box(
                modifier = Modifier
                    .size(220.dp)
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "QR CODE\n(TOTP, refresh 10s)",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Refreshing in ${countdown}s",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(elevation = CardDefaults.cardElevation(2.dp), modifier = Modifier.fillMaxWidth()) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(onClick = { countdown = 10 }) {
                        Icon(Icons.Default.Refresh, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Refresh")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GenerateQrScreenPreview() {
    PabWeek3Theme {
        CompositionLocalProvider(
            LocalBackStack provides androidx.navigation3.runtime.rememberNavBackStack(Route.Home)
        ) {
            GenerateQrScreen()
        }
    }
}
