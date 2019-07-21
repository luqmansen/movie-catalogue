package luqmansen.me.moviecatalogue1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class ListMovieAdapter extends RecyclerView.Adapter<ListMovieAdapter.ListViewHolder> {
    private ArrayList<Movie> listMovie;

    public ListMovieAdapter(ArrayList<Movie> list) {
        this.listMovie = list;
    }

    @NonNull
    @Override
    public ListMovieAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_row, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListMovieAdapter.ListViewHolder holder, int position) {
        Movie movie = listMovie.get(position);

        Glide.with(holder.itemView.getContext())
                .load(movie.getMovieBg())
                .apply(new RequestOptions().override(55, 55))
                .into(holder.imgPhoto);

        holder.tvName.setText(movie.getTitle());
        holder.tvRelease.setText(movie.getRelease());
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgPhoto;
        public TextView tvName;
        public TextView tvRelease;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.homeBg);
            tvName = itemView.findViewById(R.id.homeTitle);
            tvRelease = itemView.findViewById(R.id.homeRelease);

        }
    }
}
