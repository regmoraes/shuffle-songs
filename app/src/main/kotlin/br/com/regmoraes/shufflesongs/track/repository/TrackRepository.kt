package br.com.regmoraes.shufflesongs.track.repository

import br.com.regmoraes.shufflesongs.network.Resource
import br.com.regmoraes.shufflesongs.track.Track

/**
 *   Copyright {2019} {Rômulo Eduardo G. Moraes}
 **/
interface TrackRepository {

    fun lookupTracksByArtistsId(artistsId: Array<Int>): Resource<List<Track>>
}