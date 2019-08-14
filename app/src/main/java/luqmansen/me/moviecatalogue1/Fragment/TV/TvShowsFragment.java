package luqmansen.me.moviecatalogue1.Fragment.TV;


import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;
import java.util.Locale;

import luqmansen.me.moviecatalogue1.Adapter.GridAdapter;
import luqmansen.me.moviecatalogue1.App.DetailActivity;
import luqmansen.me.moviecatalogue1.BuildConfig;
import luqmansen.me.moviecatalogue1.Model.Popular.Data;
import luqmansen.me.moviecatalogue1.Model.Popular.DataResponse;
import luqmansen.me.moviecatalogue1.Util.NetworkUtil;
import luqmansen.me.moviecatalogue1.R;
import luqmansen.me.moviecatalogue1.Rest.ApiClient;
import luqmansen.me.moviecatalogue1.Rest.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowsFragment extends Fragment implements SearchView.OnQueryTextListener, MenuItem.OnActionExpandListener, View.OnFocusChangeListener {

//    private final String TAG = this.getActivity().getClass().getSimpleName();
    private final String TAG = "TvShowsFragmentTAG";
    private final static String API_KEY = BuildConfig.API_KEY;
    private GridAdapter gridAdapter;
    RecyclerView recyclerView;

    String language =Locale.getDefault().getLanguage();
    ProgressBar progressBar;

    public TvShowsFragment() {} // Public Constructor


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        progressBar = view.findViewById(R.id.progressBarFragment);

        if (API_KEY.isEmpty()) {
            Toast.makeText(this.getContext(), "Please obtain your API KEY first from themoviedb.org", Toast.LENGTH_LONG).show();
        }

        NetworkUtil check = new NetworkUtil(getContext());
        check.isNetworkAvailable();

        recyclerView = view.findViewById(R.id.recyler_layout);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        recyclerView.setHasFixedSize(true);
        setHasOptionsMenu(true);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        progressBar.setVisibility(View.VISIBLE);
        Call<DataResponse> call = apiService.getPopularTV(API_KEY, language );
        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                List<Data> datas = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + datas.size());
//                Toast.makeText(getContext(), "Number of movies received: " + datas.size(), Toast.LENGTH_LONG).show();
                gridAdapter = new GridAdapter(datas, R.layout.item_grid);
                progressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(gridAdapter);
                showRecyclerGrid();
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                Toast.makeText(getContext(), "API Request Failed: " , Toast.LENGTH_LONG).show();
                Log.e(TAG, t.toString());
            }
        });
        return view;
    }


    private void showRecyclerGrid() {
        gridAdapter.setOnItemClickCallback(new GridAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Data data) {
                selectItem(data);
            }
        });
    }

    public void selectItem(Data data) {
        data.setTitle(data.getTitle());
        data.setReleaseDate(data.getReleaseDate());
        data.setOverview(data.getOverview());
        data.setPosterPath(data.getPosterPath());
        data.setBackdropPath(data.getBackdropPath());
//        data.setVideo(data.getVideo());

        Intent movieDetail = new Intent(getContext(), DetailActivity.class);
        movieDetail.putExtra(DetailActivity.EXTRA_MOVIE, data);
        getContext().startActivity(movieDetail);
    }

//    Uncomment This Function for column change in onConfigurationChange, but reset the recyleview
//
//    @Override
//    public void onConfigurationChanged(Configuration newConfig) {
//        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? 3 : 2));
//        super.onConfigurationChanged(newConfig);
//
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        inflater.inflate(R.menu.search, menu);
//        final MenuItem searchItem = menu.findItem(R.id.search);
//        MenuItemCompat.setShowAsAction(searchItem, MenuItemCompat.SHOW_AS_ACTION_ALWAYS);
//        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
//        searchView.setOnQueryTextListener(this);
//        searchView.setOnQueryTextFocusChangeListener(this);
//        searchView.setQueryHint(getString(R.string.searchview_hint));
//        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
//        if (s == null || s.trim().isEmpty()) {
//            gridAdapter.setFilter(list);
//            return false;
//        }
//        s = s.toLowerCase();
//        final ArrayList<Movie> filteredTitle = new ArrayList<>();
//        for (Movie model : list) {
//            final String title = model.getTitle().toLowerCase();
//            if (title.contains(s)) {
//                filteredTitle.add(model);
//            }
//        }
//        gridAdapter.setFilter(filteredTitle);
        return true;
    }

    @Override
    public boolean onMenuItemActionExpand(MenuItem menuItem) {
        return true;
    }

    @Override
    public boolean onMenuItemActionCollapse(MenuItem menuItem) {
//        gridAdapter.setFilter(list);
        return true;
    }

    @Override
    public void onFocusChange(View view, boolean b) {
//        gridAdapter.setFilter(list);
    }

}
