package luqmansen.me.moviecatalogue1;


import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowsFragment extends Fragment implements SearchView.OnQueryTextListener, MenuItemCompat.OnActionExpandListener, View.OnFocusChangeListener {
    public String[] dataTitle;
    public String[] dataRelease;
    public String[] dataDescription;
    public TypedArray dataBg;
    public String[] dataMovieTrailerId;
    private ArrayList<Movie> movies;

    private RecyclerView rv_movie;
    private ArrayList<Movie> list = new ArrayList<>();
    GridAdapter gridAdapter = new GridAdapter(list, getContext());

    public TvShowsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        rv_movie = view.findViewById(R.id.rv_movie);
        rv_movie.setHasFixedSize(true);

        prepare();
        addItem();
        list.addAll(this.movies);

        setHasOptionsMenu(true);

        showRecyclerGrid();

        return view;
    }

    private void showRecyclerGrid() {
        rv_movie.setLayoutManager(new GridLayoutManager(getContext(), 3));
        rv_movie.setAdapter(gridAdapter);

        gridAdapter.setOnItemClickCallback(new GridAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                selectItem(data);
            }
        });
    }

    private void addItem() {
        movies = new ArrayList<>();

        for (int i = 0; i < dataTitle.length; i++) {
            Movie movie = new Movie();
            movie.setTitle(dataTitle[i]);
            movie.setRelease(dataRelease[i]);
            movie.setDesc(dataDescription[i]);
            movie.setMovieBg(dataBg.getResourceId(i, -1));
            movie.setMovieTrailerId(dataMovieTrailerId[i]);
            movies.add(movie);
        }
    }

    public void prepare() {
        dataTitle = getResources().getStringArray(R.array.tvshow_title);
        dataRelease = getResources().getStringArray(R.array.tvshow_release);
        dataDescription = getResources().getStringArray(R.array.tvshow_desc);
        dataBg = getResources().obtainTypedArray(R.array.tvshow_bg);
        dataMovieTrailerId = getResources().getStringArray(R.array.tvshow_youtube_id);
    }

    public void selectItem(Movie movie) {
        movie.setTitle(movie.getTitle());
        movie.setRelease(movie.getRelease());
        movie.setDesc(movie.getDesc());
        movie.setMovieBg(movie.getMovieBg());
        movie.setMovieTrailerId(movie.getMovieTrailerId());

        Intent movieDetail = new Intent(getContext(), DetailActivity.class);
        movieDetail.putExtra(DetailActivity.EXTRA_MOVIE, movie);
        getContext().startActivity(movieDetail);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search, menu);
        final MenuItem searchItem = menu.findItem(R.id.search);
        MenuItemCompat.setShowAsAction(searchItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
        searchView.setOnQueryTextListener(this);
        searchView.setOnQueryTextFocusChangeListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onFocusChange(View view, boolean b) {
        gridAdapter.setFilter(list);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if (s == null || s.trim().isEmpty()) {
            gridAdapter.setFilter(list);
            return false;
        }
        s = s.toLowerCase();
        final ArrayList<Movie> filteredTitle = new ArrayList<>();
        for (Movie model : list) {
            final String title = model.getTitle().toLowerCase();
            if (title.contains(s)) {
                filteredTitle.add(model);
            }
        }
        gridAdapter.setFilter(filteredTitle);
        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem item) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem item) {
        gridAdapter.setFilter(list);
        return true;
    }
}
