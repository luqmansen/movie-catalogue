package luqmansen.me.moviecatalogue1;


import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class movieFragment extends Fragment
{
    public String[] dataTitle;
    public String[] dataRelease;
    public String[] dataDescription;
    public TypedArray dataBg;
    public String[] dataMovieTrailerId;
    private MovieAdapter adapter;
    private ArrayList<Movie> movies;

    private RecyclerView rv_movie;
    private ArrayList<Movie> list = new ArrayList<>();

    public movieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        rv_movie = view.findViewById(R.id.rv_movie);
        rv_movie.setHasFixedSize(true);

        prepare();
        addItem();
        list.addAll(this.movies);

        showRecyclerGrid();
        // Inflate the layout for this fragment
        return view;
    }

    private void showRecyclerList()
    {
        rv_movie.setLayoutManager(new LinearLayoutManager(getContext()));
        ListMovieAdapter listMovieAdapter = new ListMovieAdapter(list);
        rv_movie.setAdapter(listMovieAdapter);
    }

    private void showRecyclerGrid()
    {
        rv_movie.setLayoutManager(new GridLayoutManager(getContext(),3));
        GridMovieAdapter gridMovieAdapter = new GridMovieAdapter(list);
        rv_movie.setAdapter(gridMovieAdapter);
    }

    private void addItem()
    {
        movies = new ArrayList<>();

        for (int i = 0; i < dataTitle.length; i++)
        {
            Movie movie = new Movie(  );
            movie.setTitle( dataTitle[i] );
            movie.setRelease( dataRelease[i] );
            movie.setDesc( dataDescription[i] );
            movie.setMovieBg( dataBg.getResourceId( i, -1 ) );
            movie.setMovieTrailerId( dataMovieTrailerId[i] );
            movies.add( movie );
        }
//        adapter.setMovies( movies );
    }

    public void prepare()
    {
        dataTitle = getResources().getStringArray( R.array.movie_title );
        dataRelease = getResources().getStringArray( R.array.movie_release );
        dataDescription = getResources().getStringArray( R.array.movie_desc );
        dataBg = getResources().obtainTypedArray( R.array.movie_bg );
        dataMovieTrailerId = getResources().getStringArray(R.array.youtube_id);
    }

}
