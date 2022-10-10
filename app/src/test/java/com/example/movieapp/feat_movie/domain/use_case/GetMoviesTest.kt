package com.example.movieapp.feat_movie.domain.use_case

import com.example.movieapp.feat_movie.data.local.model.Movie
import com.example.movieapp.feat_movie.data.local.repository.TestRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetMoviesTest {

    private lateinit var getMovies: GetMovies
    private lateinit var testRepository: TestRepository

    @Before
    fun setUp() {
        testRepository = TestRepository()
        getMovies = GetMovies(testRepository)

        val movieList = mutableListOf<Movie>()
        (1..3).forEach {
            movieList.add(
                Movie(name = "Matrix $it")
            )
        }

        runBlocking { testRepository.insertMovies(movieList.shuffled()) }
    }

    @Test
    fun `Get movies from repository, list of movies`() = runBlocking {
        val movieList = getMovies()
        assertThat(movieList).isNotNull()
    }
}