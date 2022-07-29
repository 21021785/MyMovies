package sg.edu.rp.c346.id21021785.mymovies;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button insert, showList;
    EditText etTitle, etGenre, etYear;
    Spinner rating;
    String storeRating = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = findViewById(R.id.btnInsert);
        showList = findViewById(R.id.btnShowList);
        etTitle = findViewById(R.id.etMovie);
        etGenre = findViewById(R.id.etGenre);
        etYear = findViewById(R.id.etYear);
        rating = findViewById(R.id.spinner);

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

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!etTitle.getText().toString().isEmpty() && !etGenre.getText().toString().isEmpty() && !etYear.getText().toString().isEmpty()) {

                    String title = etTitle.getText().toString();
                    String singer = etGenre.getText().toString();
                    String year = etYear.getText().toString();
                    int yearToInt = Integer.parseInt(year);



                    DBHelper dbh = new DBHelper(MainActivity.this);
                    long inserted_id = dbh.insertMovie(title, singer, yearToInt, storeRating);
                    if (inserted_id != -1){
                        Toast.makeText(MainActivity.this, "Insert successful", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}