package f.nouar.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
    private TextView txtNoData;
    private ProgressBar loading_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        loading_spinner = findViewById(R.id.loading_spinner);
        listView.setEmptyView(txtNoData);
        txtNoData = findViewById(R.id.text_no_data);

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



        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager.getInstance(this).initLoader(0, null, this);
        }
        else {
            txtNoData.setText(this.getString(R.string.no_internet_connection));
            loading_spinner.setVisibility(View.GONE);
        }
    }

    @NonNull
    @Override
    public Loader<List<News>> onCreateLoader(int id, @Nullable Bundle args) {
        Log.i("Loader", "oncreateloader !!!");
        //loading_spinner.setVisibility(View.VISIBLE);
        //txtNoData.setText("Loading data ...");
        return new asyncLoader(MainActivity.this, GUARDIAN_API_URL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<News>> loader, List<News> newsList) {
        loading_spinner.setVisibility(View.GONE);
        Log.i("Loader", "deliver result executed !!!");
        adapter.clear();
        if (newsList != null && !newsList.isEmpty()) {
            adapter.addAll(newsList);
        }
        else{
           // txtNoData.setText(response);
            txtNoData.setText("No Data found.");
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<News>> loader) {
        adapter.clear();
        txtNoData.setText("No Data found.");

    }
}
