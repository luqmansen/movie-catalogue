package luqmansen.me.moviecatalogue1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridMovieAdapter extends RecyclerView.Adapter<GridMovieAdapter.GridViewHolder>
{
    private ArrayList<Movie> listMovie;
    public GridMovieAdapter(ArrayList<Movie> list)
    {
        this.listMovie = list;
    }

    @NonNull
    @Override
    public GridMovieAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GridMovieAdapter.GridViewHolder holder, int position)
    {
        Glide.with(holder.itemView.getContext())
                .load(listMovie.get(position).getMovieBg())
                .apply(new RequestOptions().override(350,550))
                .into(holder.imgPhoto);

//        holder.itemView.setOnClickListener();
    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgPhoto;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }
}
