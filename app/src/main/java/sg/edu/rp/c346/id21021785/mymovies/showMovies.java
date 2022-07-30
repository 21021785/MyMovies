package sg.edu.rp.c346.id21021785.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class showMovies extends AppCompatActivity {

    Button showPG13;
    ArrayList<Movie> al;
    ListView lv;
    //ArrayAdapter<Song> aa;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_movies);

        showPG13 = findViewById(R.id.btnShowPG13);
        lv = findViewById(R.id.lv);

        al = new ArrayList<Movie>();
        //aa = new ArrayAdapter<Song>(this, R.layout.listview, al);
        //lv.setAdapter(aa);
        adapter = new CustomAdapter(this, R.layout.row, al);
        lv.setAdapter(adapter);

        DBHelper dbh = new DBHelper(showMovies.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        adapter.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song song = al.get(position);
                Intent i = new Intent(SecondActivity.this,
                        ThirdActivity.class);
                i.putExtra("song", song);
                startActivity(i);
            }
        });

        show5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(SecondActivity.this);
                al.clear();
                al.addAll(dbh.getAll5StarSongs());


                adapter.notifyDataSetChanged();
            }
        });

        /*
        yearSelection.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        al.clear();
                        al.addAll(dbh.getAllSongs());
                        adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

         */


    }

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper dbh = new DBHelper(SecondActivity.this);
        al.clear();
        al.addAll(dbh.getAllSongs());
        adapter.notifyDataSetChanged();
    }
}