package com.example.new_sqlite;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Main2Activity extends AppCompatActivity {


    EditText editText1, editText2, editText3;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );
        editText1 = findViewById( R.id.editOne );
        editText2 = findViewById( R.id.editTwo );
        editText3 = findViewById( R.id.editThree );
        button = findViewById( R.id.log );
        final Database database = new Database( this );
        
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();
                String s3 = editText3.getText().toString();
                
                boolean ifChecked = database.check_data( s1, s2, s3);
                if (ifChecked){
                    Toast.makeText( Main2Activity.this, "Logged in", Toast.LENGTH_SHORT ).show();
                }else {
                    Toast.makeText( Main2Activity.this, " Not Logged in", Toast.LENGTH_SHORT ).show();

                }
                
            }
        } );
    }
}
