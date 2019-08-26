package br.com.regmoraes.shufflesongs.track.repository.api

import br.com.regmoraes.shufflesongs.track.Track
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 *   Copyright {2019} {Rômulo Eduardo G. Moraes}
 **/
object LookupEndpoint {

    const val ENDPOINT = "lookup"

    interface RestService {
        @GET
        fun lookup(@Url url: String): Call<LookupResponse>
    }

    data class LookupResponse(
        val results: List<Track>
    )
}