package br.com.regmoraes.shufflesongs

import android.widget.TextView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.regmoraes.shufflesongs.presentation.playlist.PlaylistActivity
import kotlinx.coroutines.runBlocking
import org.hamcrest.core.AllOf.allOf
import org.hamcrest.core.IsInstanceOf.instanceOf
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class PlaylistActivityTest {

    @Test
    fun assertBasicLayout() = runBlocking<Unit> {

        ActivityScenario.launch(PlaylistActivity::class.java)

        onView(allOf(instanceOf(TextView::class.java), withParent(withId(R.id.toolbar))))
            .check(matches(withText("Shuffle Songs")))

        onView(withId(R.id.imageButton_shuffle)).check(matches(isDisplayed()))
    }
}