package ro.ase.stampcollector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private EditText username;
    private EditText password;
    private Button loginButton;
    private TextView goToRegister;
    private UserRepository mUserRepository;
    public static final String PREFERENCES_FILE_NAME = "CVPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = (EditText) findViewById(R.id.loginNameEditText);
        password = (EditText) findViewById(R.id.loginPasswordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        goToRegister = (TextView) findViewById(R.id.registerText);

        mUserRepository = UserRepository
                .getInstance(getApplicationContext());

        goToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(intent);

                LoginActivity.this.finish();
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {


                    if (mUserRepository.login(username.getText().toString(),
                            password.getText().toString()) != null) {


                        Intent intent = new Intent(LoginActivity.this,
                                MainActivity.class);

                        startActivity(intent);

                        LoginActivity.this.finish();

                    } else {
                        Toast toast = Toast.makeText(LoginActivity.this,
                                "Wrong username", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            }
        });

    }

    private boolean isEmpty(EditText text) {
        String string = text.getText().toString();
        return TextUtils.isEmpty(string);
    }

    private boolean isValid() {

        if (isEmpty(username)) {
            Toast toast = Toast.makeText(this,
                    Resources.getSystem().getString(R.string.usernameError),
                    Toast.LENGTH_LONG);
            toast.show();

            return false;
        }

        if (isEmpty(password)) {
            Toast toast = Toast.makeText(this,
                    Resources.getSystem().getString(R.string.passwordError),
                    Toast.LENGTH_LONG);
            toast.show();

            return false;
        }

        return true;
    }


    @Override
    protected void onResume() {
        SharedPreferences mySettings = getSharedPreferences(PREFERENCES_FILE_NAME, 0);
        String username2 = mySettings.getString("username", null);
        String password2 = mySettings.getString("password", null);
        username.setText(username2);
        password.setText(password2);
        super.onResume();
    }


}
