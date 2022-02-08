package com.example.differencemvpandmoxy

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.lang.IllegalArgumentException
import kotlin.coroutines.CoroutineContext


private const val TAG = "TestCoroutine"

class TestCoroutine : ViewModel(), CoroutineScope {

    private val job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Default

    val repo = Repo()
    private val viewModelScope = this


    fun test() {

        val flow = repo.getCurrentProgress()
            .catch { Log.d(TAG, "catch exception: $it") }
            .onCompletion {
                Log.d(TAG, "flow onCompletion")
            }
//            .shareIn(this, replay = 1, started = SharingStarted.Lazily)

        this.launch {

            Log.d(TAG, "test: start()")
            async(Dispatchers.IO) {
                flow
                    .debounce(1000)
                    .onCompletion { Log.d(TAG, "debounce: onCompletion") }
                    .collectLatest {
                        Log.d(TAG, "debounce: $it. ${Thread.currentThread()}")
                    }
            }

            async(Dispatchers.IO) {
                flow
                    .sample(1000)
                    .onCompletion { Log.d(TAG, "sample: onCompletion") }
                    .collectLatest {
                        Log.d(TAG, "sample: $it. ${Thread.currentThread()}")
                    }


            }


        }

    }
}

class Repo {

    private val stateFlow = MutableStateFlow(10)

    fun getCurrentProgress(): Flow<Int> {
        return flow {
            for (i in 0..1000) {
                delay(500)
                if (i == 10) {
                    throw IllegalArgumentException()
                }
                emit(i)
            }
        }.onCompletion {
            Log.d(TAG, "getCurrentProgress: onCompletion")
        }.onStart {
            Log.d(TAG, "getCurrentProgress: onStart")
        }.onEach {
//            Log.d(TAG, "getCurrentProgress: onEach: $it")
        }
    }

//    fun checkSyncMethods(): Flow<Int> {
//        return flow {
//            for (i in 0..100) {
//                delay(500)
//                if(i == 10 ) {
//
//                }
//                emit(i)
//            }
//        }
//    }
}