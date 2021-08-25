package com.eddy.healiostest.domain.dispatchers

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class AppDispatchers(
    val ioDispatchers: CoroutineDispatcher = Dispatchers.IO,
    val mainDispatchers: CoroutineDispatcher = Dispatchers.Main,
    val unconfinedDispatchers: CoroutineDispatcher = Dispatchers.Unconfined,
    val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
)