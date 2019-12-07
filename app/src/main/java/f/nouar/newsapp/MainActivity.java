package f.nouar.newsapp;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String GUARDIAN_API_URL = "https://content.guardianapis.com/search?q=debate&tag=politics/politics&from-date=2014-01-01&api-key=6140f015-b070-4b9c-b1bf-160b8694dd7f";
    private ListView listView;
    private AdapterNews adapter;
    private LoaderManager.LoaderCallbacks<List<News>> mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);
        adapter = new AdapterNews(this, 0, new ArrayList<News>());
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
        new asyncLoader(this).forceLoad();

    }


    /*class asyncRequest extends AsyncTask<String, Void, List<News>> {
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
    }*/

    class asyncLoader extends AsyncTaskLoader<List<News>> {
        public asyncLoader(@NonNull Context context) {
            super(context);
        }

        @Nullable
        @Override
        public List<News> loadInBackground() {
            List newsList = Utils.fetchNewsData(GUARDIAN_API_URL);
            return newsList;
        }

        @Override
        public void deliverResult(@Nullable List<News> newsList) {
            super.deliverResult(newsList);
            adapter.clear();
            if (newsList != null && !newsList.isEmpty()) {
                adapter.addAll(newsList);
            }

        }
    }
}
