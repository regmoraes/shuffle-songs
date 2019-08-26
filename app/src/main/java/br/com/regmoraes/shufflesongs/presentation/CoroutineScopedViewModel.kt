package br.com.regmoraes.shufflesongs.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext


open class CoroutineScopedViewModel : ViewModel(), CoroutineScope {

    private val _job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + _job

    override fun onCleared() {
        super.onCleared()
        _job.cancel()
    }
}
