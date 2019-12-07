package f.nouar.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<News>> {

    //public static final String GUARDIAN_API_URL = "https://content.guardianapis.com/search?api-key=6140f015-b070-4b9c-b1bf-160b8694dd7f";
    public static final String GUARDIAN_API_URL = "https://content.guardianapis.com/search?api-key=6140f015-b070-4b9c-b1bf-160b8694dd7f&show-tags=contributor";
    private ListView listView;
    private AdapterNews adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        adapter = new AdapterNews(this, 0, new ArrayList<News>());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = adapter.getItem(position);
                Uri newsUri = Uri.parse(currentNews.getmUrl());
                Intent websiteItent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteItent);
            }
        });

        LoaderManager.getInstance(this).initLoader(0, null, this);
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.i("Loader", "oncreateloader !!!");
        return new asyncLoader(MainActivity.this, GUARDIAN_API_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> newsList) {
        Log.i("Loader", "deliver result executed !!!");
        adapter.clear();
        if (newsList != null && !newsList.isEmpty()) {
            adapter.addAll(newsList);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        adapter.clear();
    }
}
