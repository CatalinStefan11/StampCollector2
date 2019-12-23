package ro.ase.stampcollector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private EditText username;
    private EditText password;
    private Button loginButton;
    private TextView goToRegister;
    private StampsDatabase mStampsDatabase;
    private UserViewModel userViewModel;
    private UserRepository mUserRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        username = (EditText) findViewById(R.id.loginNameEditText);
        password = (EditText) findViewById(R.id.loginPasswordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        goToRegister = (TextView) findViewById(R.id.registerText);
//        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        mUserRepository = UserRepository
                .getInstance(StampsDatabase.getInstance(getApplicationContext()).userDao());

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
                if(checkDataEntered()) {

//                    if(userViewModel.checkValidLogin(username.getText().toString(),
//                    password.getText().toString()))
                    if(mUserRepository.isValidAccount(username.getText().toString(),
                   password.getText().toString()))
                    {


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

    private boolean checkDataEntered() {

        if(isEmpty(username)) {
            Toast toast =  Toast.makeText(this, "You must provide a username",
                    Toast.LENGTH_SHORT);
            toast.show();
            username.setError("Username is required");

            return false;
        }

        if(isEmpty(password)) {
            Toast toast =  Toast.makeText(this, "You must provide a password",
                    Toast.LENGTH_SHORT);
            toast.show();
            password.setError("Password is required");

            return false;
        }

        return true;
    }

//    private void readData(){
//        Completable.fromAction(new Action() {
//            @Override
//            public void run() throws Exception {
//                mStampsDatabase.userDao().getUserRelation();
//            }
//        }).observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe(new CompletableObserver() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        mUserWithStamps = mStampsDatabase.userDao().getUserRelation();
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Toast.makeText(LoginActivity.this, "Empty data",Toast.LENGTH_LONG).show();
//                    }
//                });
//    }

}
