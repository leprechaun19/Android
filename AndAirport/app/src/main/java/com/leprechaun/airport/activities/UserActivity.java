package com.leprechaun.airport.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.leprechaun.airport.R;
import com.leprechaun.airport.data.entities.User;
import com.leprechaun.airport.tasks.User.AddChangeUser;
import com.leprechaun.airport.tasks.User.DeleteUser;

public class UserActivity extends AppCompatActivity {

    User user;
    TextView addOrChangeText;
    CardView cardView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        Bundle b = getIntent().getExtras();
        if (b != null){
            user = (User) b.getSerializable(User.class.getSimpleName());
            if (user != null){
                TextView name = findViewById(R.id.user_name_textview);
                TextView email = findViewById(R.id.user_email_textView);
                TextView phone = findViewById(R.id.user_phone_textView);
                TextView address = findViewById(R.id.user_address_textView);
                TextView createTime = findViewById(R.id.user_create);

                name.setText(user.getUserName());
                email.setText(user.getEmail());
                phone.setText(user.getPhone());
                address.setText(user.getAddress());
                createTime.setText(String.valueOf(user.getCreateAt()));
            }
        }
        else {
            TextView airlineText = findViewById(R.id.userText);
            CardView cardView_Name = findViewById(R.id.cardView_user);

            airlineText.setVisibility(View.GONE);
            cardView_Name.setVisibility(View.GONE);
            SetVisibleChange();
            addOrChangeText.setText("Add new user");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.nav_change:
                SetVisibleChange();
                SetDataToEditText(user);
                addOrChangeText.setText("New user");
                break;
            case R.id.nav_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Are you sure want to delete?");
                builder.setMessage("Confirm your decision.");

                builder.setPositiveButton("Yes, delete it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DeleteUser(user, UserActivity.this.getWindow().getDecorView().getRootView()).execute("0");
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(UserActivity.this, "Deletion canceled.", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                break;
            case 16908332:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void SetVisibleChange(){

        cardView = findViewById(R.id.layout_change_user);
        addOrChangeText = findViewById(R.id.new_or_change_user);

        addOrChangeText.setVisibility(View.VISIBLE);
        cardView.setVisibility(View.VISIBLE);
    }

    private void SetDataToEditText(User user){

        if(user != null) {
            EditText name = findViewById(R.id.new_user_name);
            EditText phone = findViewById(R.id.new_user_phone);
            EditText email = findViewById(R.id.new_user_email);
            EditText address = findViewById(R.id.new_user_address);

            name.setText(user.getUserName());
            phone.setText(user.getPhone());
            email.setText(user.getEmail());
            address.setText(user.getAddress());
        }
    }

    public void AddChangeUserClick(View view) {

        User u = new User();
        EditText name = findViewById(R.id.new_user_name);
        EditText phone = findViewById(R.id.new_user_phone);
        EditText email = findViewById(R.id.new_user_email);
        EditText address = findViewById(R.id.new_user_address);

        if (user != null){
            u.setUserId(user.getUserId());
            u.setCreateAt(user.getCreateAt());
        }

        u.setAddress(address.getText().toString());
        u.setEmail(email.getText().toString());
        u.setPhone(phone.getText().toString());
        u.setUserName(name.getText().toString());
        new AddChangeUser(this.getWindow().getDecorView().getRootView()).execute(u);
    }
}
