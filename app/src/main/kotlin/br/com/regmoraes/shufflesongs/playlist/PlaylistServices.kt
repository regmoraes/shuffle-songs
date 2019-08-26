package br.com.regmoraes.shufflesongs.playlist

import br.com.regmoraes.shufflesongs.track.TrackServices

/**
 *   Copyright {2019} {Rômulo Eduardo G. Moraes}
 **/
class PlaylistServices(private val trackServices: TrackServices) {

    fun getPlaylist(): Playlist {
        val allTracks = trackServices.getAllTracks()
        return Playlist(allTracks)
    }
}