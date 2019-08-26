package br.com.regmoraes.shufflesongs.presentation

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.regmoraes.shufflesongs.R
import br.com.regmoraes.shufflesongs.playlist.PlaylistServices
import br.com.regmoraes.shufflesongs.track.TrackServices
import br.com.regmoraes.shufflesongs.track.repository.api.TrackApi
import kotlinx.android.synthetic.main.activity_playlist.progressBar
import kotlinx.android.synthetic.main.activity_playlist.recyclerView_playlist
import kotlinx.android.synthetic.main.activity_playlist.toolbar
import kotlinx.android.synthetic.main.activity_playlist.view.imageButton_shuffle

class PlaylistActivity : AppCompatActivity(), PlaylistViewModel.ViewStateRenderer {

    private val viewModel by lazy { viewModelFactory.get(PlaylistViewModel::class.java) }
    private val viewModelFactory by lazy {
        ViewModelProviders.of(
            this, PlaylistViewModelFactory(
                PlaylistServices(
                    TrackServices(
                        TrackApi()
                    )
                )
            )
        )
    }
    private val tracksAdapter: TracksAdapter by lazy { TracksAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playlist)

        setActionBar(toolbar)

        recyclerView_playlist.apply {
            layoutManager =
                LinearLayoutManager(this@PlaylistActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(
                DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL).apply {
                    val drawable = getDrawable(R.drawable.track_item_divider)
                    if (drawable != null) setDrawable(drawable)
                }
            )
            adapter = tracksAdapter
        }

        viewModelFactory.get(PlaylistViewModel::class.java).also {
            it.getViewStateLiveData().observe(this, Observer(::render))
            it.loadPlaylist()
        }

        toolbar.imageButton_shuffle.setOnClickListener {
            viewModel.shuffle()
        }
    }

    override fun render(viewState: PlaylistViewModel.ViewState) {
        if (viewState.loading) {
            progressBar.visibility = View.VISIBLE
            recyclerView_playlist.visibility = View.INVISIBLE
        } else {
            progressBar.visibility = View.INVISIBLE
            recyclerView_playlist.visibility = View.VISIBLE

            tracksAdapter.setTracks(viewState.playlist.tracks)
        }
    }
}
