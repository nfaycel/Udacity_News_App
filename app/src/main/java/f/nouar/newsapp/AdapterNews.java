package f.nouar.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class AdapterNews extends ArrayAdapter<News> {
    private List<News> NewsList = new ArrayList<News>();

    public AdapterNews(@NonNull Context context, int resource, @NonNull List<News> objects) {
        super(context, resource, objects);
        NewsList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        News currentNews = NewsList.get(position);

        TextView article_title = convertView.findViewById(R.id.article_title);
        article_title.setText((currentNews.getmTitle()));

        TextView article_text = convertView.findViewById(R.id.article_text);
        article_text.setText((currentNews.getmText()));

        TextView article_section = convertView.findViewById(R.id.article_section);
        article_section.setText((currentNews.getmSection()));

        TextView article_authors = convertView.findViewById(R.id.article_authors);
        article_authors.setText((currentNews.getmAuthors()));

        TextView article_date = convertView.findViewById(R.id.date);
        article_date.setText(currentNews.getmDate());

        return convertView;
    }

}
