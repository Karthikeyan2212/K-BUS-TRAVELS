package com.skbusapp.kbus;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.*;

import java.util.ArrayList;

public class Login extends Activity  {
    Button b1,b2,b3,b4,book;
    EditText ed1,ed2;
    AlertDialog.Builder builder;
    TextView tx1;
    int counter = 3;
    Button submit, fetch;
    DatabaseReference mydbref;
    String name,pwd;
    long maxid=0;
    private ArrayList<String> username1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        b1 = (Button)findViewById(R.id.button);

        ed1 = (EditText)findViewById(R.id.editText);
        ed2 = (EditText)findViewById(R.id.editText2);

        b2 = (Button)findViewById(R.id.button2);

        b3 = (Button)findViewById(R.id.button3);

         final ArrayAdapter aa1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,username1);


            b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mydbref=FirebaseDatabase.getInstance().getReference("Users");

                mydbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot ds : dataSnapshot.getChildren()) {
                             name = ds.child("name").getValue().toString();
                             pwd = ds.child("password").getValue().toString();
                            if(ed1.getText().toString().equals(name) &&
                                    ed2.getText().toString().equals(pwd)) {
                                Toast.makeText(getApplicationContext(),
                                        "Redirecting...",Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Login.this, MainActivity.class);
                                startActivity(i);
                            }
                            else{

                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Uncomment the below code to Set the message and title from the strings.xml file
                builder = new AlertDialog.Builder(Login.this);


                        //Uncomment the below code to Set the message and title from the strings.xml file
                        builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);

                        //Setting message manually and performing action on button click
                        builder.setMessage("Do you want to exit")
                                .setCancelable(false)
                                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        finish();
                                        Toast.makeText(getApplicationContext(),"",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //  Action for 'NO' Button
                                        dialog.cancel();
                                    }
                                });
                        //Creating dialog box
                        AlertDialog alert = builder.create();
                        //Setting the title manually
                        alert.setTitle("EXIT");
                        alert.show();
                    }
                });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent i = new Intent(Login.this, Signup.class);
                    startActivity(i);
            }
        });

    }
    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setTitle(R.string.app_name);
        builder.setIcon(R.mipmap.ib1);
        builder.setMessage("Do you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

}

