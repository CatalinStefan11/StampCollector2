package ro.ase.stampcollector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StampActivity extends AppCompatActivity {

    private EditText mTextStampTitle;
    private EditText mTextStampDescription;
    private EditText mTextStampIssuedOn;
    private EditText mTextStampColour;
    private EditText mTextStampQuantity;
    private Stamp2 mStamp;
    private int mStampPosition;
    private boolean mIsNewStamp;
    private static final int POSITION_NOT_SET = -1;
    public static final String STAMP_POSITION = "ro.ase.stampcollector.STAMP_POSITION";
    private final String TAG = getClass().getSimpleName();
    private boolean mIsCancelling;
    private UserRepository mUserRepository;
    private User currentUser;
    private Button deleteButton;
    private Button writeToFile;
    private Button readFromFile;

    private StampRecyclerAdapter mStampRecyclerAdapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);

        mUserRepository = UserRepository.getInstance(getApplicationContext());
        currentUser = mUserRepository.getCurrentUser();

        deleteButton = findViewById(R.id.delete_button);
        readFromFile = findViewById(R.id.read_from_file);
        writeToFile = findViewById(R.id.write_to_file);


        mTextStampTitle = findViewById(R.id.stamp_title);
        mTextStampDescription = findViewById(R.id.stamp_description);
        mTextStampIssuedOn = findViewById(R.id.stamp_issuedOn);
        mTextStampColour = findViewById(R.id.stamp_color);
        mTextStampQuantity = findViewById(R.id.stamp_quantity);

        readDisplayStateValues();



        if(!mIsNewStamp) {
            displayStamp(mTextStampTitle, mTextStampDescription, mTextStampIssuedOn,
                    mTextStampColour, mTextStampQuantity);
        }

        if(mIsNewStamp){
            deleteButton.setEnabled(false);
        }

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                deleteStamp();
                setResult(RESULT_OK);
                finish();

            }
        });


        readFromFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Stamp2 stamp = readFromFile("file.dat");
                    Toast.makeText(getApplicationContext(), stamp.toString(), Toast.LENGTH_LONG).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        writeToFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    writeToFile("file.dat", mStamp);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });





        Log.d(TAG, "onCreate");
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    private void displayStamp(EditText title, EditText description, EditText issuedOn,
                              EditText color, EditText quantity){

        title.setText(mStamp.getTitle());
        description.setText(mStamp.getDescription());
        issuedOn.setText(mStamp.getIssuedOn());
        color.setText(mStamp.getColor());
        quantity.setText(String.valueOf(mStamp.getQuantity()));

    }

    private void writeToFile(String fileName, Stamp2 stamp) throws IOException {
        FileOutputStream file = openFileOutput(fileName, Activity.MODE_PRIVATE);
        DataOutputStream dos = new DataOutputStream(file);

        dos.writeUTF(stamp.getTitle());
        dos.writeUTF(stamp.getDescription());
        dos.writeUTF(stamp.getIssuedOn());
        dos.writeUTF(stamp.getColor());
        dos.writeInt(stamp.getQuantity());
        dos.flush();
        file.close();
    }

    private Stamp2 readFromFile(String fileName) throws IOException {
        FileInputStream file = openFileInput(fileName);
        DataInputStream dis = new DataInputStream(file);


        String title = dis.readUTF();
        String description = dis.readUTF();
        String issuedOn = dis.readUTF();
        String color = dis.readUTF();
        int quantity  = dis.readInt();

        Stamp2 stamp = new Stamp2(title, description, issuedOn, color, quantity);

        file.close();
        return stamp;
    }

    private void readDisplayStateValues(){
        Intent intent = getIntent();
        int position = intent.getIntExtra(STAMP_POSITION, POSITION_NOT_SET);
        mIsNewStamp = position == POSITION_NOT_SET;
        if(mIsNewStamp){
            createNewStamp();
        }else{
            Log.i(TAG, "mStampPosition: " + mStampPosition);
            mStamp = mUserRepository.getStamps(currentUser).get(position);
        }

    }

    private void createNewStamp() {
        Stamp2 nou = new Stamp2();
        mStamp = nou;
    }

    private void deleteStamp(){
        mUserRepository.deleteStamp(mStamp);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_stamp, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.stamp_menu_cancel){
            mIsCancelling = true;
            finish();
        }else if(id == R.id.stamp_menu_next){
            moveNext();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.stamp_menu_next);
        int lastStampIndex = mUserRepository.getStamps(currentUser).size() - 1;
        item.setEnabled(mStampPosition < lastStampIndex);
        return super.onPrepareOptionsMenu(menu);
    }

    private void moveNext(){
        updateStamp();
        ++mStampPosition;
        mStamp = mUserRepository.getStamps(currentUser).get(mStampPosition);

        displayStamp(mTextStampTitle, mTextStampDescription, mTextStampIssuedOn,
                mTextStampColour, mTextStampQuantity);
        invalidateOptionsMenu();
    }

    private void saveStamp(){

        mStamp.setTitle(mTextStampTitle.getText().toString());
        mStamp.setDescription(mTextStampDescription.getText().toString());
        mStamp.setIssuedOn(mTextStampIssuedOn.getText().toString());
        mStamp.setColor(mTextStampColour.getText().toString());
        mStamp.setQuantity(Integer.parseInt(mTextStampQuantity.getText().toString()));
        mUserRepository.addStamp(currentUser, mStamp);
    }

    private void updateStamp() {
        mStamp.setTitle(mTextStampTitle.getText().toString());
        mStamp.setDescription(mTextStampDescription.getText().toString());
        mStamp.setIssuedOn(mTextStampIssuedOn.getText().toString());
        mStamp.setColor(mTextStampColour.getText().toString());
        mStamp.setQuantity(Integer.parseInt(mTextStampQuantity.getText().toString()));
        mUserRepository.updateStamp(mStamp);
    }




    @Override
    protected void onPause() {
        super.onPause();

        if(mIsCancelling){

            if(mIsNewStamp){
                mStamp = null;
            }
        }
        else{
            if(mIsNewStamp){
                saveStamp();
                setResult(RESULT_OK);

            }
            else
            {
                updateStamp();
                setResult(RESULT_OK);

            }

        }
        Log.d(TAG, "onPause");

    }


}
