package br.com.regmoraes.shufflesongs.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.regmoraes.shufflesongs.playlist.PlaylistServices
import java.lang.IllegalArgumentException

/**
 *   Copyright {2019} {Rômulo Eduardo G. Moraes}
 **/
class PlaylistViewModelFactory(private val playlistServices: PlaylistServices) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(PlaylistViewModel::class.java))
            @Suppress("UNCHECKED_CAST")
            PlaylistViewModel(playlistServices) as T
        else
            throw IllegalArgumentException("Unknown ViewModel named ${modelClass.name}")
    }
}