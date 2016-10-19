package be.teknyske.myfirstdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import static android.R.attr.onClick;
import static android.R.id.list;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    private MyDBAdapter dbAdapter;
    private ListView list;
    private Spinner faculties;
    private Button addStudent;
    private Button deleteEngineers;
    private EditText studentName;
    private String[] allFaculties ={"Engineering","Business","Arts"};
    private CharSequence mTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        faculties = (Spinner) findViewById(R.id.faculties_spinner);
        studentName = (EditText) findViewById(R.id.student_name);
        addStudent = (Button) findViewById(R.id.add_student);
        deleteEngineers = (Button) findViewById(R.id.delete_engineers);
        list = (ListView) findViewById(R.id.student_list);
        dbAdapter = new MyDBAdapter(MainActivity.this);
        dbAdapter.open();
        addStudent.setOnClickListener(this);
        deleteEngineers.setOnClickListener(this);
        faculties.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, allFaculties));
        loadList();

        }

    private void loadList()
        {
        ArrayList<String> allStudents = new ArrayList <String>();
        allStudents = dbAdapter.selectAllStudents();
        final ArrayAdapter<String> adapter = new ArrayAdapter (this, android.R.layout.simple_list_item_1, allStudents);
        list.setAdapter(adapter);
        }

    public void onClick(View v)
        {
        switch (v.getId())
            {
            case R.id.add_student:
                dbAdapter.insertStudent(studentName.getText().toString(), faculties.getSelectedItemPosition() + 1);
                loadList();
                break;

            case R.id.delete_engineers:
                dbAdapter.deleteAllEngineers();
                loadList();
                break;
            }
        }
}
