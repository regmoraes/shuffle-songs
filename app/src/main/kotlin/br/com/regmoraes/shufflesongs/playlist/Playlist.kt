package br.com.regmoraes.shufflesongs.playlist

import br.com.regmoraes.shufflesongs.track.Track

/**
 *   Copyright {2019} {Rômulo Eduardo G. Moraes}
 **/
class Playlist(var tracks: List<Track> = emptyList()) {

    fun shuffle() {

        val shuffledTracks = tracks.shuffled().toMutableList()

        val sortedTracks = mutableListOf<Track>()

        if (shuffledTracks.isNotEmpty()) {
            sortedTracks.add(shuffledTracks.first())

            while (sortedTracks.size != tracks.size) {

                for (i in 1..shuffledTracks.lastIndex) {
                    if (sortedTracks.last().artistName != shuffledTracks[i].artistName) {
                        sortedTracks.add(shuffledTracks[i])
                        shuffledTracks.removeAt(i)
                        break
                    }
                }
            }

            this.tracks = sortedTracks
        }
    }
}