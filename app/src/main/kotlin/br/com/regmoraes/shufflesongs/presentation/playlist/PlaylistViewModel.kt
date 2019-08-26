package br.com.regmoraes.shufflesongs.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.regmoraes.shufflesongs.network.ApiError
import br.com.regmoraes.shufflesongs.playlist.Playlist
import br.com.regmoraes.shufflesongs.playlist.PlaylistServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *   Copyright {2019} {Rômulo Eduardo G. Moraes}
 **/
class PlaylistViewModel(private val playlistServices: PlaylistServices) : CoroutineScopedViewModel() {

    private val viewState = MutableLiveData<ViewState>()

    fun getViewStateLiveData(): LiveData<ViewState> = viewState

    fun loadPlaylist() = launch {
        viewState.value = ViewState(loading = true)

        val playlist = withContext(Dispatchers.Default) { playlistServices.getPlaylist() }

        viewState.value = ViewState(playlist = playlist)
    }

    fun shuffle() {
        val currentPlaylist = viewState.value?.playlist
        if(currentPlaylist != null) {
            val shuffledPlaylist = currentPlaylist.apply { shuffle() }
            viewState.value = ViewState(playlist = shuffledPlaylist)
        }
    }

    data class ViewState(val playlist: Playlist = Playlist(),
                         val loading: Boolean = false,
                         val error: ApiError? = null)

    interface ViewStateRenderer {
        fun render(viewState: ViewState)
    }
}