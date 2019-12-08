package f.nouar.newsapp;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.List;

class asyncLoader extends AsyncTaskLoader<List<News>> {
    private String mUrl;
    private List<News> resultFromHttp;
    public asyncLoader(@NonNull Context context, String url) {
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