package com.example.instagarmclone

import android.content.res.Resources
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.instagarmclone.common.snackbar.SnackbarManager
import com.example.instagarmclone.presentation.authentication.LoginScreen
import com.example.instagarmclone.presentation.instagramGraph
import com.example.instagarmclone.util.Constants.SIGN_UP_SCREEN
import com.example.instagarmclone.util.Constants.SPLASH_SCREEN
import kotlinx.coroutines.CoroutineScope

@Composable
fun InstagramCloneApp(){

    val appState = rememberAppState()
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = it,
                modifier = Modifier.padding(8.dp),
                snackbar = { snackbarData ->
                    Snackbar(snackbarData, contentColor = MaterialTheme.colors.onPrimary)
                }
            )
        },
        scaffoldState = appState.scaffoldState
    ){ innerPaddingModifier ->
        NavHost(
            navController = appState.navController,
            startDestination = SPLASH_SCREEN,
            modifier = Modifier.padding(innerPaddingModifier)
        ) {
            instagramGraph(appState)
        }
    }

}

@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(scaffoldState, navController, snackbarManager, resources, coroutineScope) {
        InstagramAppState(scaffoldState, navController, snackbarManager, resources, coroutineScope)
    }

@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}