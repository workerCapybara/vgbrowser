package ar.edu.unicen.vgbrowser.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import ar.edu.unicen.vgbrowser.ui.videogame.VideogameRoute
import ar.edu.unicen.vgbrowser.ui.videogame.VideogameDetails
import ar.edu.unicen.vgbrowser.ui.videogames.VideogamesScreen
import ar.edu.unicen.vgbrowser.ui.filter.FilterPageContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<VideogameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = "videogames"
            ) {

                composable(
                    route = "videogames"
                ){
                    VideogamesScreen(
                        viewModel = viewModel,
                        goDetails = { videogameUiModel ->
                            navController.navigate(
                                VideogameRoute(
                                    name=videogameUiModel.name,
                                    year=videogameUiModel.year,
                                    description=videogameUiModel.description,
                                    image=videogameUiModel.image
                                )
                            )
                        },

                        goFilters = {
                            navController.navigate("filters")
                        }


                        )
                }

                composable(route = "filters") {
                    FilterPageContent(
                        viewModel = viewModel,
                        onApplyFilters = { ordering, genres, platforms ->
                            viewModel.getVideogames(ordering, genres, platforms)
                        },
                        onBack = {
                            navController.popBackStack()
                        }
                    )
                }

                composable<VideogameRoute> { navBackStackEntry ->
                    val videogameRoute = navBackStackEntry.toRoute<VideogameRoute>()
                    VideogameDetails(
                        name = videogameRoute.name,
                        year = videogameRoute.year,
                        description = videogameRoute.description,
                        image = videogameRoute.image
                    )
                }

            }


        }


    }



}