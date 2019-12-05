package f.nouar.newsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String GUARDIAN_API_URL = "https://content.guardianapis.com/search?api-key=6140f015-b070-4b9c-b1bf-160b8694dd7f";
    private ListView listView;
    private AdapterNews adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);

        adapter = new AdapterNews(this,0, new ArrayList<News>());
        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        listView.setAdapter(adapter);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                News currentNews = adapter.getItem(position);
                Uri newsUri = Uri.parse(currentNews.getmUrl());
                Intent websiteItent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteItent);
            }
        });*/

        new asyncRequest().execute(GUARDIAN_API_URL);


    }


    class asyncRequest extends AsyncTask<String, Void, List<News>> {
        @Override
        protected List<News> doInBackground(String... strings) {
            if(strings.length<1 || strings[0] == null){
                return null;
            }
            List newsList = Utils.fetchNewsData(strings[0]);
            return newsList;
        }

        @Override
        protected void onPostExecute(List<News> newsList) {
            super.onPostExecute(newsList);
            adapter.clear();
            if(newsList != null && !newsList.isEmpty()){
                adapter.addAll(newsList);
            }

        }
    }
}
