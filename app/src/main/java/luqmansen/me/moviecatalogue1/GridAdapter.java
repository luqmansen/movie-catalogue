package luqmansen.me.moviecatalogue1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridViewHolder>
{
    private ArrayList<Movie> listMovie;
    private ArrayList<Movie> filteredList;
    private Context context;
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback)
    {
        this.onItemClickCallback = onItemClickCallback;
    }


    public GridAdapter(ArrayList<Movie> list, Context context)
    {
        this.listMovie = list;
        this.context = context;
    }

    @NonNull
    @Override
    public GridAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GridAdapter.GridViewHolder holder, int position)
    {
        Glide.with(holder.itemView.getContext())
                .load(listMovie.get(position).getMovieBg())
                .apply(new RequestOptions().override(300,500))
                .into(holder.imgPhoto);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(listMovie.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return listMovie.size();
    }

    public void setFilter(ArrayList<Movie> dataFilter)
    {
        listMovie = new ArrayList<>();
        listMovie.addAll(dataFilter);
        notifyDataSetChanged();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView imgPhoto;

        public GridViewHolder(@NonNull View itemView)
        {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
        }
    }

    public interface OnItemClickCallback
    {
        void onItemClicked(Movie data);
    }
}
