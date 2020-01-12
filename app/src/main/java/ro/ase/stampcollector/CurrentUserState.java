package ro.ase.stampcollector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class CurrentUserState extends AppCompatActivity {

    private EditText currentUserText;
    private Button updateUser;
    private Button deleteUser;
    private UserRepository mUserRepository;
    private User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_state);


        mUserRepository = UserRepository
                .getInstance(getApplicationContext());
        currentUser = mUserRepository.getCurrentUser();


        currentUserText = findViewById(R.id.current_user_text);
        updateUser = findViewById(R.id.button_update_user);
        deleteUser = findViewById(R.id.button_delete_user);

        currentUserText.setText(currentUser.getUsername());


        updateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentUser.setUsername(currentUserText.getText().toString());
                mUserRepository.updateUser(currentUser);
                mUserRepository.getUserById(currentUser.id);
                currentUserText.setText(currentUser.getUsername());
            }
        });

        deleteUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUserRepository.deleteUser(currentUser);
                finish();
                startActivity(new Intent(CurrentUserState.this,LoginActivity.class));
            }
        });
    }
}
