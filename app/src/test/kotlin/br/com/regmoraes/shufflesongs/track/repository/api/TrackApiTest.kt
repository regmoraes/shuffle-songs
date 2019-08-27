package br.com.regmoraes.shufflesongs.track.repository.api

import org.junit.Before

import br.com.regmoraes.shufflesongs.BaseTest
import br.com.regmoraes.shufflesongs.NetworkTestUtils
import br.com.regmoraes.shufflesongs.network.RetrofitConfiguration
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Assert

import org.junit.Test

class TrackApiTest : BaseTest() {

    private lateinit var trackApi: TrackApi

    @Before
    override fun setUp() {
        super.setUp()

        val retrofit = RetrofitConfiguration.buildRetrofit()
        val lookupRestService = retrofit.create(LookupEndpoint.RestService::class.java)

        trackApi = TrackApi(lookupRestService)
    }

    @Test
    fun `return tracks from api correctly`() {

        val responseBody = NetworkTestUtils.getStringContentFromFile("api-response-mocks/lookup_response.json")

        val response = MockResponse()
            .setResponseCode(200)
            .addHeader("Content-Type", "application/json; charset=utf-8")
            .setBody(responseBody)

        val apiMock = MockWebServer()
        apiMock.enqueue(response)
        apiMock.start()

        RetrofitConfiguration.baseUrl = apiMock.url("/").toString()

        val expectedResponse = RetrofitConfiguration.gson
            .fromJson(responseBody, LookupEndpoint.LookupResponse::class.java)
            .results.toMutableList().apply { removeAll{ it.trackName.isNullOrBlank() } }

        val actualResponse = trackApi.lookupTracksByArtistsId(emptyArray())

        apiMock.shutdown()

        Assert.assertEquals(expectedResponse, actualResponse.data)
    }
}