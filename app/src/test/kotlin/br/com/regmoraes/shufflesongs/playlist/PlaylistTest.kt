package br.com.regmoraes.shufflesongs.playlist

import br.com.regmoraes.shufflesongs.BaseTest
import br.com.regmoraes.shufflesongs.track.Track
import org.junit.Assert.assertNotEquals
import org.junit.Test

class PlaylistTest : BaseTest() {

    @Test
    fun `shuffle tracks correctly`() {

        val tracks = arrayListOf(
            Track(1, "track1", "artworkUrl1", "artistName1", "genre1"),
            Track(2, "track2", "artworkUrl2", "artistName2", "genre2"),
            Track(3, "track3", "artworkUrl3", "artistName3", "genre3")
        )

        val playlist = Playlist(tracks)

        val originalTracks = playlist.tracks.toList()

        playlist.shuffle()

        val sortedTracks = playlist.tracks

        assertNotEquals(originalTracks, sortedTracks)
    }
}