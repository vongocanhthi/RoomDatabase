package com.vnat.roomdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    User user;
    UserDatabase db;
    ArrayList<User> userArrayList;
    UserAdapter userAdapter;

    @BindView(R.id.edtUsername)
    EditText edtUsername;

    @BindView(R.id.edtPassword)
    EditText edtPassword;

    @BindView(R.id.btnSave)
    Button btnSave;

    @BindView(R.id.lvwAccount)
    ListView lvwAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        init();
        event();
    }

    private void event() {
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = user.username = edtUsername.getText().toString();
                String password = user.password = edtPassword.getText().toString();

                user = new User(username,password);

                db.iUserDao().insertUser(user);
                Toast.makeText(MainActivity.this, "Create Success", Toast.LENGTH_SHORT).show();
                Log.d("zzz", String.valueOf(db.iUserDao().getAllUser().size()));
                userAdapter.notifyDataSetChanged();

            }
        });

        lvwAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "" + userArrayList.get(position).getUsername(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        db = UserDatabase.getInstance(getApplicationContext());

        userArrayList = (ArrayList<User>) db.iUserDao().getAllUser();

        userAdapter = new UserAdapter(MainActivity.this, userArrayList);
        lvwAccount.setAdapter(userAdapter);

    }
}