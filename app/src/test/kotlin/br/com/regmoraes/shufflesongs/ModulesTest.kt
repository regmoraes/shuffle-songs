package br.com.regmoraes.shufflesongs

import br.com.regmoraes.shufflesongs.di.playlistModule
import br.com.regmoraes.shufflesongs.di.presentationModule
import br.com.regmoraes.shufflesongs.di.tracksModule
import org.junit.Test
import org.koin.dsl.koinApplication
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

class ModulesTest : KoinTest {

    @Test
    fun allModulesAreDefinedCorrectly() {
        koinApplication {
            modules(arrayListOf(
                tracksModule,
                playlistModule,
                presentationModule
            ))
        }.checkModules()
    }
}