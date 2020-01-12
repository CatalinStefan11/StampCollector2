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
    private EditText mUsernameText;
    private EditText mPasswordText;
    private Button mButtonLogin;
    private TextView mGoRegister;
    private UserRepository mUserRepository;
    public static final String PREFERENCES_FILE_NAME = "CVPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mUsernameText = (EditText) findViewById(R.id.name_login_text);
        mPasswordText = (EditText) findViewById(R.id.pass_login_text);
        mButtonLogin = (Button) findViewById(R.id.button_login);
        mGoRegister = (TextView) findViewById(R.id.thenRegisterText);

        mUserRepository = UserRepository
                .getInstance(getApplicationContext());

        mGoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(intent);

                LoginActivity.this.finish();
            }
        });


        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValid()) {


                    if (mUserRepository.login(mUsernameText.getText().toString(),
                            mPasswordText.getText().toString()) != null) {


                        Intent intent = new Intent(LoginActivity.this,
                                MainActivity.class);

                        startActivity(intent);

                        LoginActivity.this.finish();

                    } else {
                        Toast toast = Toast.makeText(LoginActivity.this,
                                "Wrong mUsernameText or mPasswordText", Toast.LENGTH_LONG);
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

        if (isEmpty(mUsernameText)) {
            Toast toast = Toast.makeText(this,
                    Resources.getSystem().getString(R.string.usernameError),
                    Toast.LENGTH_LONG);
            toast.show();

            return false;
        }

        if (isEmpty(mPasswordText)) {
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
        String username2 = mySettings.getString("mUsernameText", null);
        String password2 = mySettings.getString("mPasswordText", null);
        mUsernameText.setText(username2);
        mPasswordText.setText(password2);
        super.onResume();
    }


}
