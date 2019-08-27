package br.com.regmoraes.shufflesongs.playlist

import br.com.regmoraes.shufflesongs.network.Resource
import br.com.regmoraes.shufflesongs.track.TrackServices
import java.lang.IllegalArgumentException

/**
 *   Copyright {2019} {Rômulo Eduardo G. Moraes}
 **/
class PlaylistServices(private val trackServices: TrackServices) {

    fun getPlaylist(): Resource<Playlist> {

        val allTracksResource = trackServices.getAllTracks()

        return if (allTracksResource.status == Resource.Status.SUCCESS && allTracksResource.data != null) {
            val playlist = Playlist(allTracksResource.data).apply { shuffle() }
            Resource.success(playlist)
        } else
            Resource.error(IllegalArgumentException("Error fetching tracks"))
    }
}