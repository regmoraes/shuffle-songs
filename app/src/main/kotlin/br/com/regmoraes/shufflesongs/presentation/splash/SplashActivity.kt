package br.com.regmoraes.shufflesongs.presentation.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.regmoraes.shufflesongs.presentation.playlist.PlaylistActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Avoid unnecessary Thread sleep
        startActivity(Intent(this, PlaylistActivity::class.java))
        finish()
    }
}
