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

    EditText mUsernameText;
    EditText mPasswordText;
    Button mButton;
    TextView mGoLogin;

    private UserRepository mUserRepository;
    public static final String PREFERENCES_FILE_NAME = "CVPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.mUsernameText =  findViewById(R.id.name_register_text);
        this.mPasswordText =  findViewById(R.id.pass_register_text);
        this.mButton =  findViewById(R.id.button_register);
        this.mGoLogin =  findViewById(R.id.loginText);


        mGoLogin.setOnClickListener(new View.OnClickListener() {
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
                    mUserRepository.addUser(mUsernameText.getText().toString(),
                            mPasswordText.getText().toString());
                    SharedPreferences settingsFile = getSharedPreferences(PREFERENCES_FILE_NAME, 0);
                    SharedPreferences.Editor myEditor = settingsFile.edit();
                    myEditor.putString("mUsernameText", mUsernameText.getText().toString());
                    myEditor.putString("mPasswordText", mPasswordText.getText().toString());
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
}