package com.vanskarner.cleanexamplekt.util

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.matcher.BoundedDiagnosingMatcher
import org.hamcrest.Description
import org.hamcrest.Matcher
import java.util.Objects

class CustomRecyclerMatcher {
    companion object {
        fun withRecyclerViewItemCount(expectedCount: Int): Matcher<View> {
            return object : BoundedDiagnosingMatcher<View, RecyclerView>(RecyclerView::class.java) {
                override fun matchesSafely(
                    item: RecyclerView?,
                    mismatchDescription: Description?
                ): Boolean {
                    val adapter = item!!.adapter
                    val itemCount = Objects.requireNonNull(adapter).itemCount
                    mismatchDescription!!
                        .appendText("adapter.getItemCount() was: ")
                        .appendValue(itemCount)
                    return itemCount == expectedCount
                }

                override fun describeMoreTo(description: Description?) {
                    description!!.appendText("adapter.getItemCount() to be: $expectedCount")
                }

            }
        }
    }
}