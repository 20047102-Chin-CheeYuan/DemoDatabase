package sg.edu.rp.c346.id20047102.demodatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnGetTasks;
    EditText etDesc;
    EditText etDate;
    ListView lvResults;
    ArrayAdapter<Task> aa;
    ArrayList<Task> alTask;

    boolean asc = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnGetTasks = findViewById(R.id.btnGetTasks);
        etDesc = findViewById(R.id.editTextDesc);
        etDate = findViewById(R.id.editTextDate);
        lvResults = findViewById(R.id.lv);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etDesc.getText().toString().trim().length() != 0 && etDate.getText().toString().trim().length() != 0) {
                    // Create the DBHelper object, passing in the
                    // activity's Context
                    DBHelper db = new DBHelper(MainActivity.this);

                    // Insert a task
                    db.insertTask(etDesc.getText().toString(), etDate.getText().toString());
                    String text = String.format("Task: %s\nDate: %s \nSuccessfully added!!",etDesc.getText().toString(), etDate.getText().toString());
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
                } else {
                    String text = ("Description or Date cannot be empty!");
                    Toast.makeText(MainActivity.this, text, Toast.LENGTH_LONG).show();
                }
            }
        });

        btnGetTasks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create the DBHelper object, passing in the
                // activity's Context
                DBHelper db = new DBHelper(MainActivity.this);
                alTask = db.getTasks(asc);
                db.close();
                asc = !asc;
                aa = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, alTask);
                lvResults.setAdapter(aa);
            }
        });


    }
}