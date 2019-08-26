package br.com.regmoraes.shufflesongs.track

import br.com.regmoraes.shufflesongs.track.repository.TrackRepository

/**
 *   Copyright {2019} {Rômulo Eduardo G. Moraes}
 **/
class TrackServices(private val trackRepository: TrackRepository) {

    fun getAllTracks(): List<Track> {

        val artistsId = arrayOf(909253, 1171421960, 358714030, 1419227, 264111789)

        return trackRepository.getAllTracksByArtistsId(artistsId)
    }
}