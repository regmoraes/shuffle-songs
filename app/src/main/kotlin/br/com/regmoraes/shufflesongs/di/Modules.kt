package br.com.regmoraes.shufflesongs.di

import br.com.regmoraes.shufflesongs.network.RetrofitConfiguration
import br.com.regmoraes.shufflesongs.playlist.PlaylistServices
import br.com.regmoraes.shufflesongs.presentation.playlist.PlaylistViewModelFactory
import br.com.regmoraes.shufflesongs.track.TrackServices
import br.com.regmoraes.shufflesongs.track.repository.TrackRepository
import br.com.regmoraes.shufflesongs.track.repository.api.LookupEndpoint
import br.com.regmoraes.shufflesongs.track.repository.api.TrackApi
import org.koin.dsl.module

val tracksModule = module {
    single { RetrofitConfiguration.buildRetrofit().create(LookupEndpoint.RestService::class.java) }
    single<TrackRepository> { TrackApi(get()) }
    single { TrackServices(get()) }
}

val playlistModule = module {
    single { PlaylistServices(get()) }
}

val presentationModule = module {
    single { PlaylistViewModelFactory(get()) }
}

