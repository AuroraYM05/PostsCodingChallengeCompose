package com.aurora03.postscodingchallengecompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.aurora03.postscodingchallengecompose.presentation.home.PostHomeScreen
import com.aurora03.postscodingchallengecompose.presentation.posts.PostListScreen


/**
 * Grafo de navegación
 */

@Composable
fun AppNavGraph(
    navController: NavHostController
){
    NavHost(
        navController = navController,
        startDestination = Screen.PostHome
    ){
        composable<Screen.PostHome>{
            PostHomeScreen(
                onNavigateToPosts = {
                    navController.navigate(Screen.PostList)
                }
            )
        }

        composable<Screen.PostList>{
            PostListScreen(
                onNavigateBack = {navController.popBackStack()}
            )
        }

    }

}