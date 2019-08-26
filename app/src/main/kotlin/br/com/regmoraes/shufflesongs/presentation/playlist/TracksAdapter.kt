package br.com.regmoraes.shufflesongs.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.regmoraes.shufflesongs.R
import br.com.regmoraes.shufflesongs.track.Track
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.adapter_track_item.view.imageView_artwork
import kotlinx.android.synthetic.main.adapter_track_item.view.textView_artistNameAndGenre
import kotlinx.android.synthetic.main.adapter_track_item.view.textView_trackName

/**
 *   Copyright {2019} {Rômulo Eduardo G. Moraes}
 **/
class TracksAdapter :
    RecyclerView.Adapter<TracksAdapter.TrackItemViewHolder>() {

    private var tracks: List<Track> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.adapter_track_item, parent, false)
        return TrackItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackItemViewHolder, position: Int) {

        val track = tracks[position]

        Glide.with(holder.itemView.context)
            .load(track.artworkUrl)
            .apply(RequestOptions.circleCropTransform())
            .placeholder(R.drawable.ic_music_note)
            .into(holder.itemView.imageView_artwork)

        holder.itemView.textView_trackName.text = track.trackName
        holder.itemView.textView_artistNameAndGenre.text = track.artistName
    }

    override fun getItemCount(): Int {
        return tracks.size
    }

    fun setTracks(tracks: List<Track>) {
        this.tracks = tracks
        notifyDataSetChanged()
    }

    inner class TrackItemViewHolder(view: View) : RecyclerView.ViewHolder(view)
}