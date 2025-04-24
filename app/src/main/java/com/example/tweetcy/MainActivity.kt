package com.example.tweetcy

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tweetcy.api.TwittesApi
import com.example.tweetcy.screens.CategoryScreen
import com.example.tweetcy.screens.TweetDetailScreen
import com.example.tweetcy.ui.theme.TweetCyTheme
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import javax.inject.Inject


private const val TAG = "MainActivity"

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var twittesApi: TwittesApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TweetCyTheme {
                @OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
                Scaffold(modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = {
                                Text(text = "TweetCy", fontWeight = FontWeight.Bold, style = MaterialTheme.typography.headlineMedium)
                            },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = Color.Red,
                                titleContentColor = Color.White,
                            ),
                        )
                    },
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        App()
                    }
                }
            }
        }

    }
}

@Composable
fun App() {
    val navigationController = rememberNavController()
    NavHost(
        navController = navigationController,
        startDestination = "Category"
    ) {
        composable("Category") {
            CategoryScreen(
                navController = navigationController
            )
        }
        composable(
            "TweetDetail/{category}",
            arguments = listOf(navArgument("category") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            Log.d(TAG, "onCreate: Catagory: ${backStackEntry.arguments?.getString("category")}")
            TweetDetailScreen(
                navController = navigationController,
            )

        }
    }
}

