package com.example.new_sqlite;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    EditText editText1, editText2, editText3, editText4;
    Button button1, button2, button3, button4;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        editText1 = findViewById( R.id.edit1 );
        editText2 = findViewById( R.id.edit2 );
        editText3 = findViewById( R.id.edit3 );
        editText4 = findViewById( R.id.edit4 );
        button1 = findViewById( R.id.bt1 );
        button2 = findViewById( R.id.bt2 );
        button3 = findViewById( R.id.bt3 );
        button4 = findViewById( R.id.bt4 );
        database = new Database( this );
        button1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s2 = editText2.getText().toString();
                String s3 = editText3.getText().toString();
                String s4 = editText4.getText().toString();
                boolean ifAdded = database.add_data( s2, s3, s4 );
                if (ifAdded) {
                    Toast.makeText( MainActivity.this, "Data added", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( MainActivity.this, "Data not added", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
        
        button1.setOnLongClickListener( new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Intent intent = new Intent( MainActivity.this, Main2Activity.class );
                startActivity( intent    );
                return false;
            }
        } );
        button4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = editText1.getText().toString();
                Integer ifDeleted = database.delete_data( s1 );
                if (ifDeleted > 0) {
                    Toast.makeText( MainActivity.this, "Data deleted", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( MainActivity.this, "Data not deleted", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
        button3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();
                String s3 = editText3.getText().toString();
                String s4 = editText4.getText().toString();
                boolean ifUpdated = database.update_data( s1, s2, s3, s4 );
                if (ifUpdated) {
                    Toast.makeText( MainActivity.this, "Data updated", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( MainActivity.this, "Data not updated", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
        button2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor all_list_count_no = database.view_data();
                if (all_list_count_no.getCount() == 0) {
                    showMessage( "ERROR", "Data not found" );
                }else {
                    StringBuilder stringBuilder = new StringBuilder();
                    while (all_list_count_no.moveToNext()) {
                        stringBuilder.append( "ID: " ).append( all_list_count_no.getString( 0 ) ).append( "\n" );
                        stringBuilder.append( "NAME: " ).append( all_list_count_no.getString( 1 ) ).append( "\n" );
                        stringBuilder.append( "EMAIL: " ).append( all_list_count_no.getString( 2 ) ).append( "\n" );
                        stringBuilder.append( "Age: " ).append( all_list_count_no.getString( 3 ) ).append( "\n\n" );
                    }
                    showMessage( "SQl Data", stringBuilder.toString() );
                }
            }
        } );
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( title );
        builder.setMessage( message );
        builder.show();
    }
}
