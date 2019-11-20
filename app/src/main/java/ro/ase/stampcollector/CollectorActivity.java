package ro.ase.stampcollector;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CollectorActivity extends AppCompatActivity {


    public static final String PARCELABLE_USER = "User";
    private TextView mTextCollectorName;
    private TextView mTextCollectorCity;
    private TextView mTextCollectorCompany;
    private TextView mTextCollectorPhone;
    private TextView mTextCollectorEmail;
    private Button SendEmailButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collector);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        mTextCollectorName = findViewById(R.id.collector_name);
        mTextCollectorCity= findViewById(R.id.collector_city);
        mTextCollectorCompany = findViewById(R.id.collector_company);
        mTextCollectorPhone = findViewById(R.id.collector_phone);
        mTextCollectorEmail = findViewById(R.id.collector_email);
        SendEmailButton = findViewById(R.id.button_email);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        readDisplayStateValues();
        sendEmailButtonSet();

    }

    private void readDisplayStateValues(){
        Intent intent = getIntent();
        User user = intent.getParcelableExtra(PARCELABLE_USER);
        mTextCollectorName.setText(user.getName());
        mTextCollectorCity.setText(user.getCity());
        mTextCollectorCompany.setText(user.getCompanyName());
        mTextCollectorEmail.setText(user.getEmail());
        mTextCollectorPhone.setText(user.getPhone());
    }

    private void sendEmail() {

        String email = mTextCollectorEmail.getText().toString();
        String name = mTextCollectorName.getText().toString();
        String subject = "An deal with stamps";


        String text = "Dear, " + name + "\n" + "I want to make you an offer";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc2822");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.putExtra(Intent.EXTRA_EMAIL, email);
        startActivity(intent);

    }

    private void sendEmailButtonSet(){

        final Button sendEmail = findViewById(R.id.button_email);
        sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });
    }




}
