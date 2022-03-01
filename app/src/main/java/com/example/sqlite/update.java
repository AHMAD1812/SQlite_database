package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update extends AppCompatActivity {
    EditText name_edit,section_edit,address_edit;
    Button update;
    int id=0;
    DatabaseHelper db=new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        name_edit=findViewById(R.id.name);
        section_edit=findViewById(R.id.section);
        address_edit=findViewById(R.id.address);
        update=findViewById(R.id.update);
        Bundle b=getIntent().getExtras();
        boolean updateValue=b.getBoolean("update",false);
        if(updateValue==true) {
            name_edit.setText(b.getString("name", ""));
            section_edit.setText(b.getString("section", ""));
            address_edit.setText(b.getString("address", ""));
            id=b.getInt("id");
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int updatedata=db.updateContact(new userModel(name_edit.getText().toString(),section_edit.getText().toString(),address_edit.getText().toString()),id);
                if(updatedata==1){
                    Toast.makeText(update.this, "Data Updated", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(update.this,view.class));
                }
            }
        });
    }
}