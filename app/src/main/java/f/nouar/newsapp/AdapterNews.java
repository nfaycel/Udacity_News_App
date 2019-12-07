package f.nouar.newsapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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

        TextView article_title =  convertView.findViewById(R.id.article_title);
        article_title.setText((currentNews.getmTitle()));

        TextView article_text =  convertView.findViewById(R.id.article_text);
        article_text.setText((currentNews.getmText()));

        TextView article_section =  convertView.findViewById(R.id.article_section);
        article_section.setText((currentNews.getmSection()));

        TextView article_authors =  convertView.findViewById(R.id.article_authors);
        article_authors.setText((currentNews.getmAuthors()));

        //Date dateObject = new Date(currentNews.getmDate());
        //String formattedDate = formatDate(dateObject);
        TextView article_date =  convertView.findViewById(R.id.date);
        article_date.setText(currentNews.getmDate());

        Log.i("xxxx", "url: " + currentNews.getmUrl());


        // Create a new Date object from the time in milliseconds of the earthquake
        //Date dateObject = new Date(currentNews.getmTimeInMilliseconds());

       /* // Find the TextView with view ID date
        TextView dateView = (TextView) convertView.findViewById(R.id.date);
        // Format the date string (i.e. "Mar 3, 1984")
        String formattedDate = formatDate(dateObject);
        // Display the date of the current earthquake in that TextView
        dateView.setText(formattedDate);

        // Find the TextView with view ID time
        TextView timeView = (TextView) convertView.findViewById(R.id.time);
        // Format the time string (i.e. "4:30PM")
        String formattedTime = formatTime(dateObject);
        // Display the time of the current earthquake in that TextView
        timeView.setText(formattedTime);*/

        return convertView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(dateObject);
    }

    /**
     * Return the formatted date string (i.e. "4:30 PM") from a Date object.
     */
    private String formatTime(Date dateObject) {
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(dateObject);
    }
}
