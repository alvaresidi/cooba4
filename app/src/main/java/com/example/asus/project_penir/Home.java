package com.example.asus.project_penir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {
    public final static String EXTRA_LEVEL = "x";
    public final static String EXTRA_NAMA = "y";
    Button btnBegin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //membuat spinner deklarasi dll
        final Spinner spinnerLevel= (Spinner)findViewById(R.id.spinnerLevel);
        btnBegin = (Button) findViewById(R.id.btnBegin);
        final TextView txtnama = (TextView) findViewById(R.id.textNamahome);
        //set spiner
        ArrayAdapter adapterLevel= ArrayAdapter.createFromResource(this, R.array.arraylevel, android.R.layout.simple_spinner_dropdown_item);
        adapterLevel.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLevel.setAdapter(adapterLevel);
        //set button event
        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intentgame = new Intent(getApplicationContext(), Gameplay.class);

                String level = spinnerLevel.getSelectedItem().toString();
                String nama = txtnama.getText().toString();
                if(nama.equals("")){
                    Toast.makeText(getApplicationContext(),  "Masukan Nama (-_-')"  , Toast.LENGTH_SHORT).show();
                }
                else {
                    intentgame.putExtra(EXTRA_LEVEL,level);
                    intentgame.putExtra(EXTRA_NAMA,nama);
                    startActivity(intentgame);
                }

            }
        });
    }
}
