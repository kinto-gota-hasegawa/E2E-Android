package com.github.georgeh1998.e2e_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.georgeh1998.e2e_android.ui.theme.E2EAndroidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            E2EAndroidTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun AppNavigation(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "Login",
        modifier = modifier,
    ) {
        composable("Login") {
            LoginScreen {
                navController.navigate("List")
            }
        }
        composable("List") {
            ListScreen {
                navController.navigate("Detail")
            }
        }
        composable("Detail") { DetailScreen() }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginScreen(
    onLogin: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(128.dp))
        Text(
            text = "username",
        )
        Spacer(modifier = Modifier.height(6.dp))
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .testTag("username")
                .semantics { testTagsAsResourceId = true }
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "password"
        )
        Spacer(modifier = Modifier.height(6.dp))
        TextField(
            value = "",
            onValueChange = {},
            modifier = Modifier
                .testTag("password")
                .semantics { testTagsAsResourceId = true }
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onLogin,
            modifier = Modifier
                .testTag("login_button")
                .semantics { testTagsAsResourceId = true }
        ) {
            Text(
                text = "Login"
            )
        }
        Spacer(modifier = Modifier.height(32.dp))
    }
}

@Composable
fun ListScreen(
    onDetail: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(8.dp),
    ) {
        repeat(30) {
            Box(
                modifier = Modifier
                    .padding(2.dp)
                    .clickable(onClick = onDetail)
                    .border(width = 1.dp, color = Color.Gray)
                    .padding(2.dp),
            ) {
                Text(
                    text = "ITEM ${it+1}"
                )
            }
        }
    }
}

@Composable
fun DetailScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text("DetailScreen")
    }
}