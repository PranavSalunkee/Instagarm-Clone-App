package com.example.instagarmclone.presentation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.instagarmclone.InstagramAppState
import com.example.instagarmclone.domain.model.User
import com.example.instagarmclone.presentation.authentication.LoginScreen
import com.example.instagarmclone.presentation.authentication.SplashScreen
import com.example.instagarmclone.presentation.main.FeedsScreen
import com.example.instagarmclone.presentation.main.ProfileScreenContent
import com.example.instagarmclone.presentation.signUp.SignUpScreen
import com.example.instagarmclone.util.Constants.FEEDS_SCREEN
import com.example.instagarmclone.util.Constants.LOGIN_SCREEN
import com.example.instagarmclone.util.Constants.PROFILE_SCREEN
import com.example.instagarmclone.util.Constants.SIGN_UP_SCREEN
import com.example.instagarmclone.util.Constants.SPLASH_SCREEN

fun NavGraphBuilder.instagramGraph(appState: InstagramAppState){

      composable(SPLASH_SCREEN) {
        SplashScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) })
       }

        composable(LOGIN_SCREEN) {
            LoginScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) },
                open = {appState.navigate(SIGN_UP_SCREEN)}
            )
        }

        composable(SIGN_UP_SCREEN) {
            SignUpScreen(openAndPopUp = { route, popUp -> appState.navigateAndPopUp(route, popUp) },
                open = {appState.navigate(LOGIN_SCREEN)})
        }
      composable(PROFILE_SCREEN){
          ProfileScreenContent(user = User(
              userName =" pranav_salunke",
              name  ="Pranav Salunke",
              bio  ="Hi this is Pranav. I an android Developer.",
              userId  ="",
              imageUrl ="",
              following = "3",
              followers = "546",
              url  ="www.xyz.com",
              email  ="",
              password  =""

          ) )
      }

}