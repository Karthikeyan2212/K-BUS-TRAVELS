package com.skbusapp.kbus;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.app.AlertDialog;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Book extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button book, b3;
    AlertDialog.Builder builder;
    EditText name,age,busno,date,time,arrival,departure,phone,address;
    long maxid=0;
    String[] busno1 = { "KB1", "KB2", "KB3", "KB4"};
    String[] Departure1 = { "Madurai", "Chennai"};
    String[] arrival1 = { "Madurai", "Chennai", "Salem" , "Coimbatore", "Thanjavoor", "Kodaikanal"};
    String busno2,dep,arr;
    DatabaseReference mydbref;
    int pos;
    Passenger pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book);
        final Spinner spin = (Spinner) findViewById(R.id.spinner);
         final Spinner spin2 = (Spinner) findViewById(R.id.spinner2);
        final Spinner spin3 = (Spinner) findViewById(R.id.spinner3);
        ArrayAdapter aa4 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,busno1);
        aa4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin3.setAdapter(aa4);
        ArrayAdapter aa1 = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Departure1);
        aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa1);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrival1);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin2.setAdapter(aa);
        spin2.setOnItemSelectedListener(this);
        spin3.setOnItemSelectedListener(this);
        spin.setOnItemSelectedListener(this);
        name = (EditText) findViewById(R.id.editText3);
        age = (EditText) findViewById(R.id.editText4);
        date = (EditText) findViewById(R.id.editText8);
        phone = (EditText) findViewById(R.id.editText9);
        address = (EditText) findViewById(R.id.editText10);
        time = (EditText) findViewById(R.id.editText11);
        book = (Button) findViewById(R.id.button3);
        b3 = (Button) findViewById(R.id.button4);
        pass=new Passenger();
        mydbref=FirebaseDatabase.getInstance().getReference().child("Passenger");
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


        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String nam=name.getText().toString();
                String address1=address.getText().toString();
                String date1=date.getText().toString();
                String time1=time.getText().toString();
                String phone1=phone.getText().toString();
                busno2=spin3.getSelectedItem().toString();
                dep=spin.getSelectedItem().toString();
                arr=spin2.getSelectedItem().toString();
                String age1=age.getText().toString();
                pass.setName(nam);
                pass.setAge(age1);
                pass.setBusno(busno2);
                pass.setDeparture(dep);
                pass.setArrival(arr);
                pass.setDate(date1);
                pass.setTime(time1);
                pass.setPhone(phone1);
                pass.setAddr(address1);
                long c=maxid+1;
//                mydbref.child(String.valueOf("User"+c)).setValue(pass);
                mydbref.child(String.valueOf("Passenger "+(maxid+1))).setValue(pass);

//                DatabaseReference dbref=db.getReference("Users");
//                dbref.setValue("hello world");
                //                  String id=mydbref.push().getKey();

                  //                  mydbref.child(id).child("Name").setValue(nam);
//                mydbref.child(id).child("Address").setValue(address1);
//                mydbref.child(id).child("Departure").setValue(dep);
//                mydbref.child(id).child("Arrival").setValue(arr);
//               mydbref.child(id).child("Age").setValue(age1);
//                mydbref.child(id).child("Time of Travel").setValue(time1);
//                mydbref.child(id).child("Date of Travel").setValue(date1);
//                mydbref.child(id).child("Phone Nummber").setValue(phone1);
//                mydbref.child(id).child("Bus Number").setValue(busno2);
//                name.setText("");
                  Toast.makeText(getApplicationContext(), "Booked Successfully",
                                        Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(Book.this, MainActivity.class);
                                startActivity(i);

            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Book.this, MainActivity.class);
                startActivity(i);
            }
        });

    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id1) {
      pos=position;
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Book.this);
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
