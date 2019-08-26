package br.com.regmoraes.shufflesongs.track.repository

import br.com.regmoraes.shufflesongs.track.Track

/**
 *   Copyright {2019} {Rômulo Eduardo G. Moraes}
 **/
interface TrackRepository {

    fun getAllTracksByArtistsId(ids: Array<Int>): List<Track>
}