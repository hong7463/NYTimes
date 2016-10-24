package com.haisenhong.nytimes.ui.mvp.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import com.haisenhong.nytimes.R;
import com.haisenhong.nytimes.data.responses.ArticleSearchResponse;
import com.haisenhong.nytimes.data.responses.Doc;
import com.haisenhong.nytimes.ui.adapter.ArticleAdapter;
import com.haisenhong.nytimes.utils.AppUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by hison7463 on 10/20/16.
 */

public class MainActivity extends AppCompatActivity implements FilterDialog.OnFilterClick{

    private final String TAG = MainActivity.class.getSimpleName();

    private List<Doc> articles;
    private MainPresenter presenter;
    private ArticleAdapter adapter;
    private String beginDate;
    private String sortOrder;
    private String newsDesk;
    private String query;
    private FilterDialog dialog;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.main_recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        presenter = new MainPresenter(this);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        setupRecyclerView();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(!AppUtils.isNetworkAvailable(this) || !AppUtils.isOnline()) {
            Toast.makeText(this, "No Internet available", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupRecyclerView() {
        articles = new ArrayList<>();
        adapter = new ArticleAdapter(articles, this);
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        presenter.fetchMoreBooks(0, query, beginDate, sortOrder, newsDesk);
        recyclerView.addOnScrollListener(new EndlessRecyclerViewScrollListener((StaggeredGridLayoutManager) recyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int page, int totalItemsCount) {
                presenter.fetchMoreBooks(page, query, beginDate, sortOrder, newsDesk);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_screen_menu, menu);
        MenuItem item = menu.findItem(R.id.main_search);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                searchArticles(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                handleClose();
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }

    private void handleClose() {
        if(query == null) {
            return;
        }
        query = null;
        articles.clear();
        presenter.fetchMoreBooks(0, query, beginDate, sortOrder, newsDesk);
    }

    private void searchArticles(String query) {
        this.query = query;
        articles.clear();
        presenter.fetchMoreBooks(0, query, beginDate, sortOrder, newsDesk);
    }

    public void showResults(ArticleSearchResponse response) {
        Log.d(TAG, response.toString());
        articles.addAll(Arrays.asList(response.getResponse().getDocs()));
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.main_search: {
                break;
            }
            case R.id.main_filter: {
                showFilterDialog();
                break;
            }
        }
        return true;
    }

    private void showFilterDialog() {
        if(dialog == null) {
            dialog = new FilterDialog();
        }
        dialog.show(getSupportFragmentManager(), "filter");
    }

    @Override
    public void onFilterClick(String beginDate, String sortOrder, String newsDesk) {
        this.beginDate = beginDate;
        this.sortOrder = sortOrder;
        this.newsDesk = newsDesk;
        articles.clear();
        presenter.fetchMoreBooks(0, this.query, beginDate, sortOrder, newsDesk);
    }

}
