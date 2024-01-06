

package com.example.instagarmclone

import android.content.res.Resources
import androidx.compose.material.ScaffoldState

import androidx.compose.runtime.Stable
import androidx.navigation.NavHostController
import com.example.instagarmclone.common.snackbar.SnackbarManager
import com.example.instagarmclone.common.snackbar.SnackbarMessage.Companion.toMessage


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@Stable
class InstagramAppState(
  val scaffoldState: ScaffoldState,
  val navController: NavHostController,
  private val snackbarManager: SnackbarManager,
  private val resources: Resources,
  coroutineScope: CoroutineScope
) {
  init {
    coroutineScope.launch {
      snackbarManager.snackbarMessages.filterNotNull().collect { snackbarMessage ->
        val text = snackbarMessage.toMessage(resources)
        scaffoldState.snackbarHostState.showSnackbar(text)
        snackbarManager.clearSnackbarState()
      }
    }
  }

  fun popUp() {
    navController.popBackStack()
  }

  fun navigate(route: String) {
    navController.navigate(route) { launchSingleTop = true }
  }

  fun navigateAndPopUp(route: String, popUp: String) {
    navController.navigate(route) {
      launchSingleTop = true
      popUpTo(popUp) { inclusive = true }
    }
  }

  fun clearAndNavigate(route: String) {
    navController.navigate(route) {
      launchSingleTop = true
      popUpTo(0) { inclusive = true }
    }
  }
}