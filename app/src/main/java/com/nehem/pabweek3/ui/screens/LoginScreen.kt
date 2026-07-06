package com.nehem.pabweek3.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nehem.pabweek3.navigation.LocalBackStack
import com.nehem.pabweek3.navigation.Route
import com.nehem.pabweek3.ui.theme.PabWeek3Theme

/**
 * LoginScreen - fitur Login SCAS
 * Week 5: menggunakan LocalBackStack (CompositionLocal) untuk navigasi,
 * dan menerapkan Conditional Navigation (tombol Sign In hanya aktif
 * jika field Email & Password tidak kosong).
 */
@Composable
fun LoginScreen() {
    val backStack = LocalBackStack.current
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val isFormValid = email.isNotBlank() && password.isNotBlank()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "SCAS PORTAL",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Identify yourself to continue",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            elevation = CardDefaults.cardElevation(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Email Address") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(12.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    modifier = Modifier.fillMaxWidth()
                )

                if (!isFormValid) {
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        text = "Email & Password wajib diisi",
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // CONDITIONAL NAVIGATION: hanya bisa lanjut ke Home jika form valid
                Button(
                    onClick = { backStack.add(Route.Home) },
                    enabled = isFormValid,
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    Text("SIGN IN / SIGN UP")
                }

                Spacer(modifier = Modifier.height(12.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    HorizontalDivider(modifier = Modifier.weight(1f))
                    Text(text = "  OR  ", style = MaterialTheme.typography.labelSmall)
                    HorizontalDivider(modifier = Modifier.weight(1f))
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Google SSO dianggap alur otentikasi terpisah, tidak wajib validasi form
                OutlinedButton(
                    onClick = { backStack.add(Route.Home) },
                    modifier = Modifier.fillMaxWidth().height(48.dp)
                ) {
                    Text("GOOGLE SSO")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    PabWeek3Theme {
        CompositionLocalProvider(
            LocalBackStack provides androidx.navigation3.runtime.rememberNavBackStack(Route.Login)
        ) {
            LoginScreen()
        }
    }
}
