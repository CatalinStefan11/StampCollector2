package ro.ase.stampcollector;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NoteActivity extends AppCompatActivity {

    private EditText mNoteTitle;
    private EditText mNoteDescription;
    private Button mButtonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });




        mNoteTitle = findViewById(R.id.note_title);
        mNoteDescription = findViewById(R.id.note_description);
        mButtonSave = findViewById(R.id.button_save_note);
        mButtonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewNote();
                Snackbar.make(v, "Note saved to Wishlist!", Snackbar.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "Note saved to Wishlist!",
                        Toast.LENGTH_LONG).show();
            }
        });

    }



    private void createNewNote(){
        DataManager dm = DataManager.getInstance();
        dm.createNewNote(mNoteTitle.getText().toString(), mNoteDescription.getText().toString());
    }

}
