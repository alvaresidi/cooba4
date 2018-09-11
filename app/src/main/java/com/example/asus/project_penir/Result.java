package com.example.asus.project_penir;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    TextView txtnama;
    TextView txtscore;

    Button btplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent intent = getIntent();
        String skor = intent.getStringExtra(Gameplay.EXTRA_SCORE);
        String nama = intent.getStringExtra(Gameplay.EXTRA_NAMA);
        String stats = intent.getStringExtra(Gameplay.EXTRA_STATUS);
        setIdattribut();
        int _skor = Integer.parseInt(skor);

        if(stats.equals("Permainan Selesai")){

            txtnama.setText("Selamat " +nama);
            txtscore.setText("Skor anda " + skor);

        }
        else if(stats.equals("Anda Kurang Beruntung")){

            txtnama.setText("jangan menyerah " +nama);
            txtscore.setText("Skor anda " + skor);

        }


        btplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);

            }
        });

    }
    public void quit(View v){
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);

    }
    public void setIdattribut(){
        btplay = (Button) findViewById(R.id.btnPlay);
        txtnama = (TextView) findViewById(R.id.textNamaResult);
        txtscore = (TextView) findViewById(R.id.textScoreResult);

    }
}
