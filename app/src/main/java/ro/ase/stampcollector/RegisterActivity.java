package ro.ase.stampcollector;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.SharedPreferences;
public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button mButton;
    TextView goToLogin;

    private UserRepository mUserRepository;
    public static final String PREFERENCES_FILE_NAME = "CVPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.username =  findViewById(R.id.registerNameEditText);
        this.password =  findViewById(R.id.registerPasswordEditText);
        this.mButton =  findViewById(R.id.registerButton);
        this.goToLogin =  findViewById(R.id.loginText);


        goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,
                        LoginActivity.class);
                startActivity(intent);

                RegisterActivity.this.finish();
            }
        });

        mUserRepository = UserRepository
                .getInstance(getApplicationContext());

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValid()) {
                    mUserRepository.addUser(username.getText().toString(),
                            password.getText().toString());
                    SharedPreferences settingsFile = getSharedPreferences(PREFERENCES_FILE_NAME, 0);
                    SharedPreferences.Editor myEditor = settingsFile.edit();
                    myEditor.putString("username", username.getText().toString());
                    myEditor.putString("password", password.getText().toString());
                    myEditor.apply();
                    Intent intent = new Intent(RegisterActivity.this,
                            LoginActivity.class);
                    startActivity(intent);

                    RegisterActivity.this.finish();
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
}