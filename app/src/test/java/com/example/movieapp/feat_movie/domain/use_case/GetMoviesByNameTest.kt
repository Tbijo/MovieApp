package com.example.movieapp.feat_movie.domain.use_case

import com.example.movieapp.feat_movie.data.local.model.Movie
import com.example.movieapp.feat_movie.data.local.repository.TestRepository
import com.example.movieapp.feat_movie.domain.util.OrderType
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMoviesByNameTest {

    private lateinit var getMoviesByName: GetMoviesByName
    private lateinit var testRepository: TestRepository

    @Before
    fun setUp() {
        testRepository = TestRepository()
        getMoviesByName = GetMoviesByName(testRepository)

        val movieList = mutableListOf<Movie>()
        (1..3).forEach {
            movieList.add(
                Movie(name = "$it")
            )
        }

        runBlocking { testRepository.insertMovies(movieList.shuffled()) }
    }

    @Test
    fun `Get movies by name with x and Ascending order`() = runBlocking {
        val movies = getMoviesByName("x", OrderType.Ascending).first()

        for (i in 0..movies.size - 2) {
            assertThat(movies[i].name).contains("x")
            assertThat(movies[i].name).isLessThan(movies[i+1].name)
        }
    }

    @Test
    fun `Get movies by name with M and Descending order`() = runBlocking {
        val movies = getMoviesByName("M").first()

        for (i in 0..movies.size - 2) {
            assertThat(movies[i].name).contains("M")
            assertThat(movies[i].name).isGreaterThan(movies[i+1].name)
        }
    }
}