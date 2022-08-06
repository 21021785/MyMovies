package sg.edu.rp.c346.id21021785.mymovies;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class ModifyMovie extends AppCompatActivity {

    Button update, delete, cancel;
    EditText etTitle, etGenre, etYear;
    Spinner rating;
    String storeRating = "";
    Movie movieDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_movie);

        update = findViewById(R.id.btnUpdate);
        cancel = findViewById(R.id.btnCancel);
        delete = findViewById(R.id.btnDelete);
        etTitle = findViewById(R.id.etMovie);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        rating = findViewById(R.id.spinner);

        Intent i = getIntent();
        movieDetails = (Movie) i.getSerializableExtra("movie");

        etTitle.setText(movieDetails.getTitle());
        etGenre.setText(movieDetails.getGenre());
        etYear.setText(movieDetails.getYear() + "");

        rating.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                switch (position) {
                    case 0:
                        storeRating = "G";
                        break;
                    case 1:
                        storeRating = "PG";
                        break;
                    case 2:
                        storeRating = "PG13";
                        break;
                    case 3:
                        storeRating = "NC16";
                        break;
                    case 4:
                        storeRating = "M18";
                        break;
                    case 5:
                        storeRating = "R21";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(ModifyMovie.this);
                int id = movieDetails.getId();
                String newTitle = etTitle.getText().toString();
                String newGenre = etGenre.getText().toString();
                String newYear = etYear.getText().toString();
                int newYearToInt = Integer.parseInt(newYear);

                movieDetails.setMovieContent(id, newTitle, newGenre, newYearToInt, storeRating);
                dbh.updateMovie(movieDetails);
                dbh.close();
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyMovie.this);
                myBuilder.setTitle("Danger");
                myBuilder.setMessage("Are you sure you want to delete the movie " + etTitle.getText() + "?");
                myBuilder.setCancelable(false);

                //Configure the positive button
                myBuilder.setPositiveButton("Cancel", null);

                myBuilder.setNegativeButton("Delete" , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DBHelper dbh = new DBHelper(ModifyMovie.this);
                        dbh.deleteSong(movieDetails.getId());
                        finish();
                    }
                });



                AlertDialog myDialog = myBuilder.create();
                myDialog.show();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder myBuilder = new AlertDialog.Builder(ModifyMovie.this);
                myBuilder.setTitle("Discard");
                myBuilder.setMessage("Are you sure you want to discard the changes?");
                myBuilder.setCancelable(false);

                //Configure the positive button
                myBuilder.setPositiveButton("Do not discard", null);

                myBuilder.setNegativeButton("Discard" , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });



                AlertDialog myDialog = myBuilder.create();
                myDialog.show();


            }
        });
    }
}