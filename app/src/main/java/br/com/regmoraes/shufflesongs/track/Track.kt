package br.com.regmoraes.shufflesongs.track

data class Track(
	val id: Int,
	val trackName: String,
	val artworkUrl: String,
	val artistName: String,
	val primaryGenreName: String
)
