package luqmansen.me.moviecatalogue1;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = (View) LayoutInflater.from( context ).inflate( R.layout.listview_row,viewGroup,false );
        }

        ViewHolder viewHolder = new ViewHolder(view);
        Movie movie = (Movie) getItem( i );
        viewHolder.bind( movie );
        return view;
    }

    private class ViewHolder {
        private TextView title;
        private TextView release;
        private TextView  desc;
        private ImageView movieBg;


        ViewHolder(View view){
            title = view.findViewById( R.id.homeTitle );
            release = view.findViewById( R.id.homeRelease );
            movieBg = view.findViewById( R.id.homeBg );
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
