package com.skbusapp.kbus;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Signup extends AppCompatActivity {
    private Button submit, b3;
    EditText name,email,phone,password;
    DatabaseReference mydbref;
    Users user;
    long maxid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        name = (EditText) findViewById(R.id.editText3);
        email = (EditText) findViewById(R.id.editText13);
        phone = (EditText) findViewById(R.id.editText14);
        password = (EditText) findViewById(R.id.editText12);
        submit = (Button) findViewById(R.id.button3);
        b3 = (Button) findViewById(R.id.button4);
        user=new Users();
        mydbref=FirebaseDatabase.getInstance().getReference().child("Users");
        mydbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    maxid=(dataSnapshot.getChildrenCount());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase db=FirebaseDatabase.getInstance();
                String nam=name.getText().toString();
                String mail=email.getText().toString();
                String phone1=phone.getText().toString();
                String pwd=password.getText().toString();
                String id=mydbref.push().getKey();
//                Users pass=new Users(nam,mail,phone1,pwd);
//                mydbref.child(id).child("Name").setValue(nam);
//                mydbref.child(id).child("Mail Id").setValue(mail);
//                mydbref.child(id).child("Phone Number").setValue(phone1);
//                mydbref.child(id).child("Password").setValue(pwd);
//                name.setText("");
                user.setName(nam);
                user.setEmail(mail);
                user.setPhone(phone1);
                user.setPassword(pwd);
                mydbref.child(String.valueOf("User "+(maxid+1))).setValue(user);
                Toast.makeText(getApplicationContext(), "Account Created Successfully",
                        Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Signup.this, Login.class);
                startActivity(i);

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Signup.this, Login.class);
                startActivity(i);
            }
        });
    }



    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Signup.this);
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
                    public void onClick(DialogInterface dialog, int id) { dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }


}
