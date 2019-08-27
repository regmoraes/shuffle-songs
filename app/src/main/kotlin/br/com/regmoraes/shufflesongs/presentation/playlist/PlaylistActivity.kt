package br.com.regmoraes.shufflesongs.presentation.playlist

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.regmoraes.shufflesongs.R
import kotlinx.android.synthetic.main.activity_playlist.*
import kotlinx.android.synthetic.main.activity_playlist.view.*
import org.koin.android.ext.android.inject

class PlaylistActivity : AppCompatActivity(),
    PlaylistViewModel.ViewStateRenderer {

    private val viewModelFactory: PlaylistViewModelFactory by inject()
    private val viewModel by lazy { ViewModelProviders.of(this, viewModelFactory).get(PlaylistViewModel::class.java) }
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

        viewModel.also { it.getViewStateLiveData().observe(this, Observer(::render)) }

        toolbar.imageButton_shuffle.setOnClickListener {
            viewModel.shuffle()
        }

        button_retry.setOnClickListener {
            viewModel.loadPlaylist()
        }
    }


    override fun render(viewState: PlaylistViewModel.ViewState) {
        if (viewState.loading) {
            progressBar.visibility = View.VISIBLE

            recyclerView_playlist.visibility = View.INVISIBLE
            textView_message.visibility = View.INVISIBLE
            button_retry.visibility = View.INVISIBLE
        }

        if(viewState.error != null) {
            textView_message.visibility = View.VISIBLE
            button_retry.visibility = View.VISIBLE

            progressBar.visibility = View.INVISIBLE
            recyclerView_playlist.visibility = View.INVISIBLE
        }

        if(viewState.playlist != null) {

            if(viewState.playlist.tracks.isEmpty()) {
                textView_message.visibility = View.VISIBLE
                textView_message.text = getString(R.string.tracks_lookup_empty)
                button_retry.visibility = View.VISIBLE

                recyclerView_playlist.visibility = View.INVISIBLE
                progressBar.visibility = View.INVISIBLE

            } else {
                recyclerView_playlist.visibility = View.VISIBLE
                progressBar.visibility = View.INVISIBLE
                textView_message.visibility = View.INVISIBLE
                button_retry.visibility = View.INVISIBLE

                tracksAdapter.setTracks(viewState.playlist.tracks)
            }
        }
    }
}
