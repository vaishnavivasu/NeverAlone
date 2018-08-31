package com.example.user.neveralone2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class FeedAdaptor extends ArrayAdapter<Feed> {

    public FeedAdaptor(@NonNull Context context, int resource, @NonNull List<Feed> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.show_feed,null);
        }

        Feed feed = getItem(position);

        if(feed != null){
            TextView title = convertView.findViewById(R.id.feed_text);
            TextView loc = convertView.findViewById(R.id.feed_location);
            TextView content = convertView.findViewById(R.id.feed_content);

            title.setText(feed.getFeedTitle());
            loc.setText(feed.getFeedLocation());
            content.setText(feed.getFeedText());
        }

        return convertView;
    }
}
