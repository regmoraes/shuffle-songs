package br.com.regmoraes.shufflesongs.playlist

import br.com.regmoraes.shufflesongs.BaseTest
import br.com.regmoraes.shufflesongs.network.Resource
import br.com.regmoraes.shufflesongs.track.Track
import br.com.regmoraes.shufflesongs.track.TrackServices
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class PlaylistServicesTest : BaseTest() {

    @MockK
    private lateinit var trackServicesMock: TrackServices

    private lateinit var playlistServices: PlaylistServices

    @Before
    override fun setUp() {
        super.setUp()
        playlistServices = PlaylistServices(trackServicesMock)
    }

    @Test
    fun `create playlist correctly`() {
        val tracks = arrayListOf(
            Track(1, "track1", "artworkUrl1", "artistName1", "genre1"),
            Track(2, "track2", "artworkUrl2", "artistName2", "genre2"),
            Track(3, "track3", "artworkUrl3", "artistName3", "genre3")
        )

        every { trackServicesMock.getAllTracks() } returns Resource.success(tracks)

        val actualResult = playlistServices.getPlaylist().data?.tracks

        assertEquals(tracks, actualResult)
    }
}