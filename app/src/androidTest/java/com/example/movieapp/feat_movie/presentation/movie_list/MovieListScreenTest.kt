package com.example.movieapp.feat_movie.presentation.movie_list

import android.content.Context
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import coil.annotation.ExperimentalCoilApi
import com.example.movieapp.R
import com.example.movieapp.di.AppModule
import com.example.movieapp.feat_movie.presentation.MainActivity
import com.example.movieapp.feat_movie.presentation.SetUpNavGraph
import com.example.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AppModule::class)
class MovieListScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()

    lateinit var context: Context

    @ExperimentalCoilApi
    @ExperimentalFoundationApi
    @Before
    fun setUp() {
        hiltRule.inject()

        context = ApplicationProvider.getApplicationContext<Context>()

        composeRule.setContent {
            MovieAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    SetUpNavGraph(navController)
                }
            }
        }
    }

    @Test
    fun clickToggleOrderSection_isVisible() {
        val orderSectionTag: String = context.resources.getString(R.string.order_section)
        val searchBthCD: String = context.resources.getString(R.string.search)

        // check Order section not visible
        composeRule.onNodeWithTag(orderSectionTag).assertDoesNotExist()

        // Click to make Order section visible
        composeRule.onNodeWithContentDescription(searchBthCD).performClick()

        // check Order section visible
        composeRule.onNodeWithTag(orderSectionTag).assertIsDisplayed()
    }

    @Test
    fun clickAscendingOrder_isAscending() {
        val orderSectionTag: String = context.resources.getString(R.string.order_section)
        val radioButtonCD = context.resources.getString(R.string.Ascending)
        val movieItemTag = context.resources.getString(R.string.movie_item)
        val searchBthCD: String = context.resources.getString(R.string.search)

        // Click to make Order section visible
        composeRule.onNodeWithContentDescription(searchBthCD).performClick()

        // check Order section visible
        composeRule.onNodeWithTag(orderSectionTag).assertIsDisplayed()

        // click on Ascending radio button
        composeRule.onNodeWithContentDescription(radioButtonCD).performClick()

        // check if order Ascending
        composeRule.onAllNodesWithTag(movieItemTag)[0].onChild()
            .assertTextContains("Angel")
        composeRule.onAllNodesWithTag(movieItemTag)[1].onChild()
            .assertTextContains("Bunny")
    }

    @Test
    fun inputMovieName_getMovie() {
        val orderSectionTag: String = context.resources.getString(R.string.order_section)
        val textFieldTag: String = context.resources.getString(R.string.text_field)
        val movieItemTag: String = context.resources.getString(R.string.movie_item)
        val searchBthCD: String = context.resources.getString(R.string.search)

        // Click to make Order section visible
        composeRule.onNodeWithContentDescription(searchBthCD).performClick()

        // check Order section visible
        composeRule.onNodeWithTag(orderSectionTag).assertIsDisplayed()

        // input movie name to text field
        composeRule
            .onNodeWithTag(textFieldTag)
            .performTextInput("Bunny")

        // check if movie is displayed
        composeRule.onNodeWithTag(movieItemTag).assertIsDisplayed()
    }

}