package f.nouar.newsapp;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.net.URL;
import java.util.List;

class AsyncLoader extends AsyncTaskLoader<List<News>> {
    private URL mUrl;
    private List<News> resultFromHttp;
    public AsyncLoader(@NonNull Context context, URL url) {
        super(context);
        mUrl = url;
    }

    @Nullable
    @Override
    public List<News> loadInBackground() {
        List newsList = Utils.fetchNewsData(mUrl);
        resultFromHttp = newsList;
        return newsList;
    }

    @Override
    protected void onStartLoading() {
        if (resultFromHttp!=null) {
            deliverResult(resultFromHttp);
        }else{
            forceLoad();
        }
    }


}