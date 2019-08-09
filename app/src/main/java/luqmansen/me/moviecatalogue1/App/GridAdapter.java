package luqmansen.me.moviecatalogue1.App;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import luqmansen.me.moviecatalogue1.Model.Data;
import luqmansen.me.moviecatalogue1.Model.DataResponse;
import luqmansen.me.moviecatalogue1.Movie;
import luqmansen.me.moviecatalogue1.R;
import retrofit2.Callback;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.GridViewHolder> {
    private List<Data> items;
    private List<Movie> filteredList;
    private int rowLayout;
    private OnItemClickCallback onItemClickCallback;

    public class GridViewHolder extends RecyclerView.ViewHolder {
        public ImageView posterThumbnail;
        LinearLayout itemLayout;

        public GridViewHolder(@NonNull View itemView) {
            super(itemView);
            posterThumbnail = itemView.findViewById(R.id.img_item_photo);
            itemLayout = itemView.findViewById(R.id.item_grid_layout);
        }
    }

    public GridAdapter(List<Data> list, int rowLayout) {
        this.items = list;
        this.rowLayout = rowLayout;
    }

    @NonNull
    @Override
    public GridAdapter.GridViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GridViewHolder holder, int position) {
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w300_and_h450_bestv2" + items.get(position)
                        .getPosterPath())
                .resize(200,300)
                .into(holder.posterThumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickCallback.onItemClicked(items.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setFilter(ArrayList<Data> dataFilter) {
        items = new ArrayList<>();
        items.addAll(dataFilter);
        notifyDataSetChanged();
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public interface OnItemClickCallback {
        void onItemClicked(Data data);
    }
}
