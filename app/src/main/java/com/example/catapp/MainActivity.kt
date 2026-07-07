package com.example.catapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import com.example.catapp.ui.CatBreedDetailScreen
import com.example.catapp.ui.CatBreedScreen
import com.example.catapp.ui.CatImageScreen
import com.example.catapp.ui.CatViewModel
import com.example.catapp.ui.theme.CatappTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatappTheme {
                CatApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun CatApp() {
    val navController = rememberNavController()
    val viewModel: CatViewModel = hiltViewModel()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onNavigateToRandom = { navController.navigate("random") },
                onNavigateToBreeds = { navController.navigate("breeds") }
            )
        }
        composable("random") {
            CatImageScreen(viewModel = viewModel)
        }
        composable("breeds") {
            BreedAdaptiveScreen(viewModel = viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun BreedAdaptiveScreen(viewModel: CatViewModel) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Nothing>()
    val scope = rememberCoroutineScope()

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
            CatBreedScreen(
                viewModel = viewModel,
                onBreedClick = { breed ->
                    viewModel.selectBreed(breed)
                    scope.launch {
                        navigator.navigateTo(ListDetailPaneScaffoldRole.Detail)
                    }
                }
            )
        },
        detailPane = {
            CatBreedDetailScreen(
                viewModel = viewModel,
                onBack = {
                    scope.launch {
                        if (navigator.canNavigateBack()) {
                            navigator.navigateBack()
                        }
                    }
                }
            )
        }
    )
}

@Composable
fun HomeScreen(
    onNavigateToRandom: () -> Unit,
    onNavigateToBreeds: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Selamat Datang di Catapp!",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = onNavigateToRandom) {
            Text("Lihat Gambar Kucing Acak")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onNavigateToBreeds) {
            Text("Lihat Daftar Ras Kucing")
        }
    }
}
