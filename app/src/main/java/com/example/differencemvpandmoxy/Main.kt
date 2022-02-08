package com.example.differencemvpandmoxy

import kotlinx.coroutines.*
import java.net.ConnectException
import kotlin.coroutines.CoroutineContext

suspend fun main() = runBlocking {
    suspendFun1()
}

private suspend fun suspendFun1() {

}


class ViewModel {

    private val job: Job = SupervisorJob()
    private val dispatcher = Dispatchers.IO
    private val exceptionHandler = CoroutineExceptionHandler() { coroutineContext, throwable ->
        if(throwable is ConnectException) {
    }

    private val coroutineContext: CoroutineContext
        get() = dispatcher + job + exceptionHandler


    protected val viewModelScope: CoroutineScope = CoroutineScope(coroutineContext)


    fun onCleared() {
        job.cancel()
    }

}