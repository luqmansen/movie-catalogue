package luqmansen.me.moviecatalogue1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Movie> movies;

    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>(  );
    }

    public void setMovies(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    @Override
    public int getCount() {
        return movies.size();
    }

    @Override
    public Object getItem(int i) {
        return movies.get( i );
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View view, ViewGroup viewGroup) {
        
        if (view == null){
            view = (View) LayoutInflater.from( context ).inflate( R.layout.listview_row,viewGroup,false );
        }

        ViewHolder holder = new ViewHolder(view);
        holder.homeDetailButton = view.findViewById(R.id.homeDetailButton);
        holder.homeDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Row " + position + " was clicked!", Toast.LENGTH_SHORT).show();
            }
        });

        Movie movie = (Movie) getItem( position );
        holder.bind( movie );
        return view;
    }

    private class ViewHolder {
        private TextView title;
        private TextView release;
        private TextView  desc;
        private ImageView movieBg;
        public Button homeDetailButton;
        public final Integer position;


        ViewHolder(View view){
            title = view.findViewById( R.id.homeTitle );
            release = view.findViewById( R.id.homeRelease );
            movieBg = view.findViewById( R.id.homeBg );

            position = 0;
        }

        void bind(Movie movie){
            title.setText( movie.getTitle() );
            title.setTextColor( Color.WHITE );
            release.setText( movie.getRelease() );
            release.setTextColor( Color.WHITE );
            movieBg.setImageResource( movie.getMovieBg() );
        }
    }
}
