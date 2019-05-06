package com.simalakama.kharisazhar.jadwalbola

import com.simalakama.kharisazhar.jadwalbola.coroutines.CoroutineContextProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlin.coroutines.CoroutineContext

class CoroutineContextProviderTest : CoroutineContextProvider() {
    @ExperimentalCoroutinesApi
    override val main: CoroutineContext = Dispatchers.Unconfined
}