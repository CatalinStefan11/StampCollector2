package ro.ase.stampcollector;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button registerButton;
    TextView goToLogin;
    private UserViewModel userViewModel;
    private UserRepository mUserRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.registerNameEditText);
        password = (EditText) findViewById(R.id.registerPasswordEditText);
        registerButton = (Button) findViewById(R.id.registerButton);
        goToLogin = (TextView) findViewById(R.id.loginText);
//        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

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
                .getInstance(StampsDatabase.getInstance(getApplicationContext()).userDao());

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkDataEntered()) {
                    mUserRepository.insertUser(username.getText().toString(),
                            password.getText().toString());

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



    private boolean checkDataEntered() {
        if(isEmpty(username)) {
            Toast toast =  Toast.makeText(this, "You must provide a user name",
                    Toast.LENGTH_SHORT);
            toast.show();
            username.setError("Name is required");

            return false;
        }



        if(isEmpty(password)) {
            Toast toast =  Toast.makeText(this, "You must provide a user password",
                    Toast.LENGTH_SHORT);
            toast.show();
            password.setError("Password is required");

            return false;
        }


        return true;
    }
}