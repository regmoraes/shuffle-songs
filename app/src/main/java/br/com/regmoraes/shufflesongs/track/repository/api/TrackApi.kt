package br.com.regmoraes.shufflesongs.track.repository.api

import br.com.regmoraes.shufflesongs.network.RetrofitConfiguration
import br.com.regmoraes.shufflesongs.track.Track
import br.com.regmoraes.shufflesongs.track.repository.TrackRepository
import br.com.regmoraes.shufflesongs.track.repository.api.LookupEndpoint.ENDPOINT
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

/**
 *   Copyright {2019} {Rômulo Eduardo G. Moraes}
 **/
class TrackApi : TrackRepository {

    private val retrofit = RetrofitConfiguration.buildRetrofit()
    private val trackRestService = retrofit.create(LookupEndpoint.RestService::class.java)

    override fun getAllTracksByArtistsId(ids: Array<Int>): List<Track> {

        val httpUrl = RetrofitConfiguration.baseUrl.toHttpUrlOrNull()

        return if (httpUrl != null) {

            val url = httpUrl.newBuilder().apply {
                addPathSegment(ENDPOINT)
                addQueryParameter("id", "909253,1171421960,358714030,1419227,264111789")
                addQueryParameter("limit", "5")
            }.build().toString()

            val callResponse = trackRestService.lookup(url).execute()

            if (callResponse.isSuccessful) {
                val results = callResponse.body()?.results

                // GSON custom deserializer wasn't working, so Artist objects incorrectly mapped as Tracks
                // were removed "manually".
                return results?.toMutableList()?.apply { removeAll{ it.trackName.isNullOrBlank() } } ?: emptyList()

            }else
                emptyList()

        } else {
            emptyList()
        }
    }
}