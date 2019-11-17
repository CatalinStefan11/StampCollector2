package ro.ase.stampcollector;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import androidx.appcompat.widget.Toolbar;

public class StampActivity extends AppCompatActivity {

    private EditText mTextStampTitle;
    private EditText mTextStampDescription;
    private EditText mTextStampIssuedOn;
    private EditText mTextStampColour;
    private EditText mTextStampQuantity;
    private  StampInfo mStamp;
    private int mStampPosition;
    private boolean mIsNewStamp;
    private static final int POSITION_NOT_SET = -1;
    public static final String STAMP_POSITION = "ro.ase.stampcollector.STAMP_POSITION";
    private final String TAG = getClass().getSimpleName();
    private boolean mIsCancelling;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stamp);
        Toolbar toolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(toolbar);



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




        Log.d(TAG, "onCreate");
    }


    private void displayStamp(EditText title, EditText description, EditText issuedOn,
                              EditText color, EditText quantity){

        title.setText(mStamp.getTitle());
        description.setText(mStamp.getDescription());
        issuedOn.setText(mStamp.getIssuedOn());
        color.setText(mStamp.getColor());
        quantity.setText(String.valueOf(mStamp.getQuantity()));

    }

    private void readDisplayStateValues(){
        Intent intent = getIntent();
        int position = intent.getIntExtra(STAMP_POSITION, POSITION_NOT_SET);
        mIsNewStamp = position == POSITION_NOT_SET;
        if(mIsNewStamp){
            createNewStamp();
        }else{
            Log.i(TAG, "mStampPosition: " + mStampPosition);
            mStamp = DataManager.getInstance().getStamps().get(position);
        }

    }

    private void createNewStamp() {
        DataManager dm = DataManager.getInstance();
        mStampPosition = dm.createNewStamp();
        mStamp = dm.getStamps().get(mStampPosition);
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
        int lastStampIndex = DataManager.getInstance().getStamps().size() - 1;
        item.setEnabled(mStampPosition < lastStampIndex);
        return super.onPrepareOptionsMenu(menu);
    }

    private void moveNext(){
        saveStamp();
        ++mStampPosition;
        mStamp = DataManager.getInstance().getStamps().get(mStampPosition);

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
    }


    @Override
    protected void onPause() {
        super.onPause();

        if(mIsCancelling){

            if(mIsNewStamp){
                DataManager.getInstance().removeStamp(mStampPosition);
            }
        }
        else{
            saveStamp();
        }
        Log.d(TAG, "onPause");

    }
}
