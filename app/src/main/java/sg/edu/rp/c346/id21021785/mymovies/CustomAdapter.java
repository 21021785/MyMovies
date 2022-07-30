package sg.edu.rp.c346.id21021785.mymovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter {
    Context parent_context;
    int layout_id;
    ArrayList<Movie> movieList;

    public CustomAdapter(Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);
        parent_context = context;
        layout_id = resource;
        movieList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(layout_id, parent, false);

        // Obtain the UI components and do the necessary binding
        TextView tvTitle = rowView.findViewById(R.id.textView);
        TextView tvGenre = rowView.findViewById(R.id.textView2);
        TextView tvYear = rowView.findViewById(R.id.textView3);
        ImageView ivRating = rowView.findViewById(R.id.imageView);

        // Obtain the Android Version information based on the position
        Movie currentVersion = movieList.get(position);

        // Set values to the TextView to display the corresponding information
        tvTitle.setText(currentVersion.getTitle());
        tvGenre.setText(currentVersion.getGenre());
        tvYear.setText(currentVersion.getYear() + "");
        String rating = currentVersion.getRating();

        if (rating.equalsIgnoreCase("G")) {
            ivRating.setImageResource(R.drawable.rating_g);
        } else if (rating.equalsIgnoreCase("PG13")) {
            ivRating.setImageResource(R.drawable.rating_pg13);
        } else if (rating.equalsIgnoreCase("PG")) {
            ivRating.setImageResource(R.drawable.rating_pg);
        } else if (rating.equalsIgnoreCase("NC16")) {
            ivRating.setImageResource(R.drawable.rating_nc16);
        } else if (rating.equalsIgnoreCase("M18")) {
            ivRating.setImageResource(R.drawable.rating_m18);
        } else if (rating.equalsIgnoreCase("R21")) {
            ivRating.setImageResource(R.drawable.rating_r21);
        }

        return rowView;
    }
}
