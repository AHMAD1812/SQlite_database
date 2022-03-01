package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText name_edit,section_edit,address_edit;
    Button insert,view;
    DatabaseHelper db=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name_edit=findViewById(R.id.name);
        section_edit=findViewById(R.id.section);
        address_edit=findViewById(R.id.address);
        insert=findViewById(R.id.insert);
        view=findViewById(R.id.view);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Boolean add=db.addData(new userModel(name_edit.getText().toString(),section_edit.getText().toString(),address_edit.getText().toString()));
                if(add){
                    Toast.makeText(MainActivity.this, "Data Saved", Toast.LENGTH_SHORT).show();
                    name_edit.setText("");
                    section_edit.setText("");
                    address_edit.setText("");
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,view.class));
            }
        });



    }
}