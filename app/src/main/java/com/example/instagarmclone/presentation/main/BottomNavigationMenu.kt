package com.example.instagarmclone.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.instagarmclone.ui.theme.InstagarmCloneTheme
import com.example.instagarmclone.util.Constants.FEEDS_SCREEN
import com.example.instagarmclone.util.Constants.PROFILE_SCREEN
import com.example.instagarmclone.util.Constants.SEARCH_SCREEN

enum class BottomNavigationItem(val icon: ImageVector, val route: String) {
    FEED(Icons.Default.Home, FEEDS_SCREEN),
    PROFILE(Icons.Default.Person, PROFILE_SCREEN),
    SEARCH(Icons.Default.Search, SEARCH_SCREEN)

}

@Composable
fun BottomNavigationMenu(
   modifier: Modifier = Modifier,
   navController: NavController,
) {
    var selectedItem by remember { mutableIntStateOf(0) }
    val items =
        listOf(BottomNavigationItem.FEED, BottomNavigationItem.PROFILE, BottomNavigationItem.SEARCH)
    NavigationBar(containerColor = Color.White) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.route) },

                onClick = {
                    selectedItem = index
                    navController.navigate(item.route)
                },
                selected = (selectedItem == index),
            )
        }

    }
}


