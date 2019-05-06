package com.simalakama.kharisazhar.jadwalbola.idling

import android.support.test.espresso.IdlingResource


/**
 * Contains a static reference IdlingResource, and should be available only in a mock build type.
 */
object EspressoIdlingResource {

    private val RESOURCE = "GLOBAL"

    private val mCountingIdlingResource = SimpleCountingIdlingResource(RESOURCE)

    val idlingResource: IdlingResource
        get() = mCountingIdlingResource

    fun increment() {
        mCountingIdlingResource.increment()
    }

    fun decrement() {
        mCountingIdlingResource.decrement()
    }
}