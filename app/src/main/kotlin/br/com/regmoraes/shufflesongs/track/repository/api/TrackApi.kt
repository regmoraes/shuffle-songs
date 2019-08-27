package br.com.regmoraes.shufflesongs.track.repository.api

import br.com.regmoraes.shufflesongs.network.Resource
import br.com.regmoraes.shufflesongs.network.RetrofitConfiguration
import br.com.regmoraes.shufflesongs.track.Track
import br.com.regmoraes.shufflesongs.track.repository.TrackRepository
import br.com.regmoraes.shufflesongs.track.repository.api.LookupEndpoint.ENDPOINT
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.io.IOException

/**
 *   Copyright {2019} {Rômulo Eduardo G. Moraes}
 **/
class TrackApi(private val lookupRestService: LookupEndpoint.RestService) : TrackRepository {

    override fun lookupTracksByArtistsId(artistsId: Array<Int>): Resource<List<Track>> {

        val httpUrl = RetrofitConfiguration.baseUrl.toHttpUrlOrNull()

        return try {

            if (httpUrl != null) {

                val url = httpUrl.newBuilder().apply {
                    addPathSegment(ENDPOINT)
                    addQueryParameter(
                        LookupEndpoint.ID_QUERY_PARAMETER,
                        artistsId.joinToString(",")
                    )
                    addQueryParameter(
                        LookupEndpoint.LIMIT_QUERY_PARAMETER,
                        LookupEndpoint.LIMIT_QUERY_PARAMETER_DEFAULT_VALUE
                    )
                }.build().toString()


                val callResponse = lookupRestService.lookup(url).execute()

                if (callResponse.isSuccessful) {
                    val results = callResponse.body()?.results

                    // GSON custom deserializer wasn't working, so Artist objects incorrectly mapped as Tracks
                    // were removed "manually".
                    Resource.success(removeArtistsFromResponse(results))

                } else
                    Resource.error(RetrofitConfiguration.mapErrorResponse(callResponse.errorBody()))

            } else {
                Resource.error(IllegalArgumentException("URL cannot be null"))
            }

        } catch (exception: Throwable) {
            Resource.error(exception)
        }
    }

    private fun removeArtistsFromResponse(tracks: List<Track>?): List<Track> {
        return tracks?.toMutableList()?.apply { removeAll { it.trackName.isNullOrBlank() } }
            ?: emptyList()
    }
}