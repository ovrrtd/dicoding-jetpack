package com.example.dicoding_jetpack.ui.main

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import com.example.dicoding_jetpack.utils.DataDummy
import com.example.dicoding_jetpack.R
import com.example.dicoding_jetpack.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainActivityTest{

    private val dummyCourseMovie = DataDummy.generateMovieData()
    private val dummyCourseTv = DataDummy.generateTvShowData()


    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.espressoTestIdlingResource)
    }
    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.espressoTestIdlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyCourseMovie.size))
    }

    @Test
    fun loadDetailMovie() {
        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.vm_name)).check(matches(isDisplayed()))
        onView(withId(R.id.vm_name)).check(matches(withText(dummyCourseMovie[0].title)))
        onView(withId(R.id.vm_user_score)).check(matches(isDisplayed()))
        onView(withId(R.id.vm_user_score)).check(matches(withText(dummyCourseMovie[0].userScore)))
        onView(withId(R.id.vm_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.vm_overview)).check(matches(withText(dummyCourseMovie[0].overview)))
    }

    @Test
    fun loadTvShow() {

        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyCourseTv.size))
    }

    @Test
    fun loadDetailTvShow() {
        onView(withText("Tv Show")).perform(click())
        onView(withId(R.id.rv_tv)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        onView(withId(R.id.vm_name)).check(matches(isDisplayed()))
        onView(withId(R.id.vm_name)).check(matches(withText(dummyCourseTv[0].title)))
        onView(withId(R.id.vm_user_score)).check(matches(isDisplayed()))
        onView(withId(R.id.vm_user_score)).check(matches(withText(dummyCourseTv[0].userScore)))
        onView(withId(R.id.vm_overview)).check(matches(isDisplayed()))
        onView(withId(R.id.vm_overview)).check(matches(withText(dummyCourseTv[0].overview)))
    }

    @Test
    fun loadTvShowFav() {

        onView(withText(R.string.fav_tv)).perform(click())
        onView(withId(R.id.rv_tv_fav)).check(matches(isDisplayed()))
    }

    @Test
    fun loadMovieFav() {

        onView(withText(R.string.fav_movie)).perform(click())
        onView(withId(R.id.rv_movie_fav)).check(matches(isDisplayed()))
    }
}