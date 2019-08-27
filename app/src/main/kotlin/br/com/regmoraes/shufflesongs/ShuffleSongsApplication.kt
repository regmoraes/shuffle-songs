package br.com.regmoraes.shufflesongs

import android.app.Application
import br.com.regmoraes.shufflesongs.di.playlistModule
import br.com.regmoraes.shufflesongs.di.presentationModule
import br.com.regmoraes.shufflesongs.di.tracksModule
import org.koin.core.context.startKoin

class ShuffleSongsApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(listOf(tracksModule, playlistModule, presentationModule))
        }
    }
}