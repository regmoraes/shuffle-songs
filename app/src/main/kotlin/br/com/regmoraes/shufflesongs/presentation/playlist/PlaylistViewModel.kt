package br.com.regmoraes.shufflesongs.presentation.playlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.regmoraes.shufflesongs.network.Resource
import br.com.regmoraes.shufflesongs.playlist.Playlist
import br.com.regmoraes.shufflesongs.playlist.PlaylistServices
import br.com.regmoraes.shufflesongs.presentation.CoroutineScopedViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 *   Copyright {2019} {Rômulo Eduardo G. Moraes}
 **/
class PlaylistViewModel(private val playlistServices: PlaylistServices) : CoroutineScopedViewModel() {

    private val viewState = MutableLiveData<ViewState>()

    fun getViewStateLiveData(): LiveData<ViewState> = viewState

    init {
        loadPlaylist()
    }

    fun loadPlaylist() = launch {

        viewState.value = ViewState(loading = true)

        val playlistResource = withContext(Dispatchers.Default) { playlistServices.getPlaylist() }

        if(playlistResource.status == Resource.Status.SUCCESS && playlistResource.data != null)
            viewState.value = ViewState(playlist = playlistResource.data)
        else
            viewState.value = ViewState(error = playlistResource.error)
    }

    fun shuffle() {
        val currentPlaylist = viewState.value?.playlist
        if(currentPlaylist != null && currentPlaylist.tracks.isNotEmpty()) {
            val shuffledPlaylist = currentPlaylist.apply { shuffle() }
            viewState.value = ViewState(playlist = shuffledPlaylist)
        }
    }

    data class ViewState(val playlist: Playlist? = null,
                         val loading: Boolean = false,
                         val error: Throwable? = null)

    interface ViewStateRenderer {
        fun render(viewState: ViewState)
    }
}