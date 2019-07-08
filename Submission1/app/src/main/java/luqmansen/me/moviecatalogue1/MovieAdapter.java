package luqmansen.me.moviecatalogue1;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MovieAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Movie> movies;
    private String[] title;
    private String[] release;
    private String[] description;
    private TypedArray moviePoster;

    public MovieAdapter(Context context) {
        this.context = context;
        movies = new ArrayList<>(  );
        getDataFromArray();
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

        ViewHolder holder;
        if (view == null){
            view = (View) LayoutInflater.from( context ).inflate( R.layout.listview_row,viewGroup,false );
            holder  = new ViewHolder(view);
            holder.homeDetailButton = (Button) view.findViewById(R.id.homeDetailButton);
            holder.homeDetailButton.setOnClickListener(buttonClickListener);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        holder.homeDetailButton.setTag(position);

        Movie movie = (Movie) getItem( position );
        holder.bind( movie );
        return view;
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int position = (Integer) view.getTag();
            Movie movie = new Movie();

            movie.setTitle(title[position]);
            movie.setRelease(release[position]);
            movie.setDesc( description[position] );
            //noinspection ResourceType
            movie.setMovieBg( moviePoster.getResourceId( position, -1 ) );

            Intent movieDetail = new Intent( context, MovieDetailActivity.class );
            movieDetail.putExtra( MovieDetailActivity.EXTRA_MOVIE, movie );
            context.startActivity(movieDetail);
        }
    };

    private void getDataFromArray(){
        title = context.getResources().getStringArray( R.array.movie_title );
        release = context.getResources().getStringArray( R.array.movie_release );
        description = context.getResources().getStringArray( R.array.movie_desc );
        moviePoster = context.getResources().obtainTypedArray( R.array.movie_bg );
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
