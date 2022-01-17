package com.tugas_besar.rentalaja.ui.activity

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.tugas_besar.rentalaja.R
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)

class LoginTest {
    val email = "cobalogin@gmail.com"
    val password = "cobacoba"
    private val emptyInput = ""
    private val emailEmpty = "harap masukan email"
    private val passwordEmpty = "harap masukan password"

    @Before
    fun setup(){
        ActivityScenario.launch(Login::class.java)
    }

    @Test
    fun signIn() {// test input email dan password saat signIn
        onView(withId(R.id.inputEmail)).perform(typeText(email), closeSoftKeyboard())
        onView(withId(R.id.inputPassword)).perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.btnMasuk)).perform(click())
    }

    @Test
    fun assertEmptyInput() {
       // pengecekan input untuk emailnya
        onView(withId(R.id.inputEmail)).perform(typeText(emptyInput), closeSoftKeyboard())

        onView(withId(R.id.btnMasuk)).check(matches(isDisplayed()))
        onView(withId(R.id.btnMasuk)).perform(click())

        onView(withId(R.id.inputEmail)).check(matches(hasErrorText(emailEmpty)))
        onView(withId(R.id.inputEmail)).perform(typeText(email), closeSoftKeyboard())

        // pengecekan input untuk paswordnya
        onView(withId(R.id.inputPassword)).perform(typeText(emptyInput), closeSoftKeyboard())

        onView(withId(R.id.btnMasuk)).check(matches(isDisplayed()))
        onView(withId(R.id.btnMasuk)).perform(click())

        onView(withId(R.id.inputPassword)).check(matches(hasErrorText(passwordEmpty)))
        onView(withId(R.id.inputPassword)).perform(typeText(password), closeSoftKeyboard())

        onView(withId(R.id.btnMasuk)).check(matches(isDisplayed()))
        onView(withId(R.id.btnMasuk)).perform(click())
    }

}