package br.com.regmoraes.shufflesongs.track

import org.junit.Before

import br.com.regmoraes.shufflesongs.BaseTest
import br.com.regmoraes.shufflesongs.network.Resource
import br.com.regmoraes.shufflesongs.track.repository.TrackRepository
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals

import org.junit.Test

class TrackServicesTest : BaseTest() {

    @MockK
    private lateinit var trackRepositoryMock: TrackRepository

    private lateinit var trackServices: TrackServices

    @Before
    override fun setUp() {
        super.setUp()

        trackServices = TrackServices(trackRepositoryMock)
    }

    @Test
    fun `return tracks correctly for specific artists ids`() {

        val expectedArtistsIds = arrayOf(909253, 1171421960, 358714030, 1419227, 264111789)

        val expectedTracks = arrayListOf(
            Track(1, "track1", "artworkUrl1", "artistName1", "genre1"),
            Track(2, "track2", "artworkUrl2", "artistName2", "genre2"),
            Track(3, "track3", "artworkUrl3", "artistName3", "genre3")
        )

        every { trackRepositoryMock.lookupTracksByArtistsId(expectedArtistsIds) } returns Resource.success(expectedTracks)

        assertEquals(expectedTracks, trackServices.getAllTracks().data)
    }
}
