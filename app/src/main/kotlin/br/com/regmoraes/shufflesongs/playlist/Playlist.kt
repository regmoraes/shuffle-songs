package br.com.regmoraes.shufflesongs.playlist

import br.com.regmoraes.shufflesongs.track.Track

/**
 *   Copyright {2019} {Rômulo Eduardo G. Moraes}
 **/
class Playlist(var tracks: List<Track> = emptyList()) {

    fun isEmpty(): Boolean = tracks.isEmpty()

    fun shuffle() {
        this.tracks = tracks.shuffled()
    }
}