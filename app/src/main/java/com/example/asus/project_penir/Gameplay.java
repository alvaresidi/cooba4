package com.example.asus.project_penir;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;



public class Gameplay extends AppCompatActivity {

    public final static String EXTRA_NAMA = "x";
    public final static String EXTRA_SCORE = "y";
    public final static String EXTRA_STATUS = "z";
    //array button jawaban
    ArrayList<Soal> kum_soal = new ArrayList<>();
    private static final int[] idArrayButton =
            {R.id.bt1, R.id.bt2, R.id.bt3, R.id.bt4, R.id.bt5, R.id.bt6
            , R.id.bt7, R.id.bt8, R.id.bt9, R.id.bt10, R.id.bt11, R.id.bt12};
    //aray buton hasil jawaban
    private static final int[] idButtonhasil =
            {R.id.btj1, R.id.btj2, R.id.btj3, R.id.btj4, R.id.btj5, R.id.btj6
                    , R.id.btj7, R.id.btj8, R.id.btj9, R.id.btj10};

    private Button[] btJawab = new Button[idArrayButton.length];
    private Button[] btHasil= new Button[idButtonhasil.length];
    private int[] posisiBtnJawab;
    private String nama;
    private String soal="";
    private String jawaban="";
    private String jawabanUser="";
    private int lengthJawaban;
    private int urutanSoal = 0;
    private int urutanJawaban = 0;


    private int numberfix;
    private String textBtn ;
    //
    TextView txtscore;
    TextView txtkesempatan ;
    TextView txtronde ;
    ImageView imgSoal;
    //atribut pemain
    private int kesempatan = 3;
    private int skor =0;
    private int ronde =1;
    //akhir deklarasi
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();
        final String jenislevel = intent.getStringExtra(Home.EXTRA_LEVEL);
        nama = intent.getStringExtra(Home.EXTRA_NAMA);

        //deklarasi
        TextView txtusername = (TextView) findViewById(R.id.textNamagame);
         txtscore = (TextView) findViewById(R.id.textScoreResult);
        txtkesempatan = (TextView) findViewById(R.id.textKesempatan);
         txtronde = (TextView) findViewById(R.id.textRonde);
        imgSoal = (ImageView) findViewById(R.id.imageSoal);
        TextView txtlevel = (TextView) findViewById(R.id.textLevelGame);

        //set default value
        if(jenislevel.equals("easy")){
            setSoaleasy();
        } else if (jenislevel.equals("normal")){
            setSoalnormal();
        } else if (jenislevel.equals("hard")){
            setSoalhard();
        }

        txtlevel.setText("LEVEL : " +jenislevel);
        txtusername.setText(nama);
        //
        SoalAwal();
        SetIDButton();

        //looping jumlah btn jwban
        for (int i=0; i<idButtonhasil.length; i++) {
            final int b = i;

            btHasil [b].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(btHasil[b].getText().equals("")){
                        Toast.makeText(getApplicationContext(),  "pilihan habis" , Toast.LENGTH_SHORT).show();
                    }
                    else {
                        btJawab[posisiBtnJawab[b]].setText(btHasil[b].getText().toString());
                        btHasil[b].setText("");
                        urutanJawaban --;
                    }
                }
            });
        }
        //Looping untuk set btn jawaban
            for (int i=0; i<idArrayButton.length; i++) {
                final int b = i;
                textBtn = String.valueOf(soal.charAt(b));
                btJawab[b].setText(textBtn);
                btJawab [b].setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    if(btJawab[b].getText().toString().equals("")){
                        Toast.makeText(getApplicationContext(),  "pilihan habis" , Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if(numberfix<urutanJawaban){
                            Toast.makeText(getApplicationContext(),  "kolom jawaban penuh" , Toast.LENGTH_SHORT).show();
                        }
                        else {
                            btHasil[urutanJawaban].setText(btJawab[b].getText().toString());
                            btJawab[b].setText("");

                            posisiBtnJawab[urutanJawaban] = b;

                            urutanJawaban ++;

                            if(lengthJawaban==urutanJawaban){
                                String xx = "";
                                for (int i=0; i<lengthJawaban; i++) {
                                    final int b = i;

                                    xx = xx + btHasil[b].getText().toString();
                                }
                                jawabanUser = xx;

                                if(jawabanUser.equals(jawaban)&&jenislevel.equals("easy")){
                                    Toast.makeText(getApplicationContext(),  "jawaban benar" , Toast.LENGTH_SHORT).show();
                                    skor += 50;
                                    urutanSoal ++;
                                    ronde++;
                                    if(ronde>20){
                                        ResultForm();
                                    }
                                    else {
                                        NextSoal();
                                    }
                                }
                                else if (jawabanUser.equals(jawaban)&&jenislevel.equals("normal")){
                                    Toast.makeText(getApplicationContext(),  "jawaban benar" , Toast.LENGTH_SHORT).show();
                                    skor += 75;
                                    urutanSoal ++;
                                    ronde++;
                                    if(ronde>20){
                                        ResultForm();
                                    }
                                    else {
                                        NextSoal();
                                    }
                                }
                                else if (jawabanUser.equals(jawaban)&&jenislevel.equals("hard")){
                                    Toast.makeText(getApplicationContext(),  "jawaban benar" , Toast.LENGTH_SHORT).show();
                                    skor += 100;
                                    urutanSoal ++;
                                    ronde++;
                                    if(ronde>20){
                                        ResultForm();
                                    }
                                    else {
                                        NextSoal();
                                    }
                                }
                                else if(jawabanUser != jawaban && jenislevel.equals("easy")){
                                    kesempatan--;

                                    txtscore.setText(String.valueOf(skor));
                                    txtkesempatan.setText(String.valueOf(kesempatan));
                                    Toast.makeText(getApplicationContext(),  "jawaban salah"  , Toast.LENGTH_SHORT).show();
                                    if(kesempatan<0){
                                        Toast.makeText(getApplicationContext(),  "game over"  , Toast.LENGTH_SHORT).show();
                                        ResultFormFail();
                                    }

                                    else {
                                        if(kesempatan<0){
                                            Toast.makeText(getApplicationContext(),  "game over"  , Toast.LENGTH_SHORT).show();
                                            ResultFormFail();
                                        }
                                        else{
                                            txtkesempatan.setText(String.valueOf(kesempatan));
                                            Toast.makeText(getApplicationContext(),  "jawaban salah"  , Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                else if(jawabanUser != jawaban && jenislevel.equals("medium")){
                                    kesempatan--;
                                    skor-=20;
                                    txtscore.setText(String.valueOf(skor));
                                    txtkesempatan.setText(String.valueOf(kesempatan));
                                    Toast.makeText(getApplicationContext(),  "jawaban salah"  , Toast.LENGTH_SHORT).show();
                                    if(kesempatan<0){
                                        Toast.makeText(getApplicationContext(),  "game over"  , Toast.LENGTH_SHORT).show();
                                        ResultFormFail();
                                    }

                                    else {
                                        if(kesempatan<0){
                                            Toast.makeText(getApplicationContext(),  "game over"  , Toast.LENGTH_SHORT).show();
                                            ResultFormFail();
                                        }
                                        else{
                                            txtkesempatan.setText(String.valueOf(kesempatan));
                                            Toast.makeText(getApplicationContext(),  "jawaban salah"  , Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                else if(jawabanUser != jawaban && jenislevel.equals("hard")){
                                    kesempatan--;
                                    skor-=50;
                                    txtscore.setText(String.valueOf(skor));
                                    txtkesempatan.setText(String.valueOf(kesempatan));
                                    Toast.makeText(getApplicationContext(),  "jawaban salah"  , Toast.LENGTH_SHORT).show();
                                    if(kesempatan<0){
                                        Toast.makeText(getApplicationContext(),  "game over"  , Toast.LENGTH_SHORT).show();
                                        ResultFormFail();
                                    }

                                    else {
                                        if(kesempatan<0){
                                            Toast.makeText(getApplicationContext(),  "game over"  , Toast.LENGTH_SHORT).show();
                                            ResultFormFail();
                                        }
                                        else{
                                            txtkesempatan.setText(String.valueOf(kesempatan));
                                            Toast.makeText(getApplicationContext(),  "jawaban salah"  , Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            }
                         }
                    }
                }
            });
        }
    }

    public void SetIDButton(){
        //set jmlh btn jawaban
        for (int i=0; i<idArrayButton.length; i++) {
            final int b = i;
            btJawab[b] = (Button) findViewById(idArrayButton[b]);
            textBtn = String.valueOf(soal.charAt(b));
            btJawab[b].setText(textBtn);
        }

        for (int i=0; i<idButtonhasil.length; i++) {
            final int b = i;
            btHasil [b] = (Button)findViewById(idButtonhasil[b]);

        }
        for (int i=0; i<lengthJawaban; i++) {
            final int b = i;
            btHasil[b].setVisibility(View.VISIBLE);
        }

    }
    public void RefreshJawaban(){
        for (int i=0; i<lengthJawaban; i++) {
            final int b = i;
            btHasil[b].setText("");
        }

        for (int i=0; i<idArrayButton.length; i++) {
            final int b = i;
            textBtn = String.valueOf(soal.charAt(b));
            btJawab[b].setText(textBtn);
        }
        urutanJawaban=0;

    }
    public void SoalAwal(){
        txtkesempatan.setText(String.valueOf(kesempatan));
        txtscore.setText(String.valueOf(skor));
        txtronde.setText(String.valueOf(ronde));
        imgSoal.setImageResource(kum_soal.get(urutanSoal).getGambar());
        soal = kum_soal.get(urutanSoal).getSoal();
        jawaban = kum_soal.get(urutanSoal).getJawaban();
        lengthJawaban = jawaban.length();
        posisiBtnJawab = new int[lengthJawaban];
        numberfix = lengthJawaban -1;

    }
    public void NextSoal(){
        txtscore.setText(String.valueOf(skor));
        txtronde.setText(String.valueOf(ronde));
        imgSoal.setImageResource(kum_soal.get(urutanSoal).getGambar());
        soal = kum_soal.get(urutanSoal).getSoal();
        jawaban = kum_soal.get(urutanSoal).getJawaban();
        lengthJawaban = jawaban.length();
        posisiBtnJawab = new int[lengthJawaban];
        numberfix = lengthJawaban -1;

        for (int i=0; i<idArrayButton.length; i++) {
            final int b = i;
            textBtn = String.valueOf(soal.charAt(b));
            btJawab[b].setText(textBtn);
        }
        for (int i=0; i<idButtonhasil.length; i++) {
            final int b = i;
            btHasil[b].setVisibility(View.INVISIBLE);
        }
        for (int i=0; i<lengthJawaban; i++) {
            final int b = i;
            btHasil[b].setVisibility(View.VISIBLE);
        }

        RefreshJawaban();
    }
    public void exit(View v){
        Intent intent = new Intent(getApplicationContext(), Home.class);
        startActivity(intent);

    }
    public void setSoaleasy(){
        kum_soal.add(new Soal("VISANSATNTNB","TNT", R.drawable.q1));
        kum_soal.add(new Soal("XADWEVISAFRE","VISA", R.drawable.q2));
        kum_soal.add(new Soal("JSKXEROXSDFG","XEROX", R.drawable.q3));
        kum_soal.add(new Soal("BAHJYERCABSJ","BAYER", R.drawable.q4));
        kum_soal.add(new Soal("AMAZONJSKDNA","AMAZON", R.drawable.q5));
        kum_soal.add(new Soal("ASDNIKRAFTDA","KRAFT", R.drawable.q6));
        kum_soal.add(new Soal("HYATTSASDDSG","HYATT", R.drawable.q7));
        kum_soal.add(new Soal("DISNEYSADASN","DISNEY", R.drawable.q8));
        kum_soal.add(new Soal("LSAJROLEXSAD","ROLEX", R.drawable.q9));
        kum_soal.add(new Soal("DASATARIASDD","ATARI", R.drawable.q10));
        kum_soal.add(new Soal("DANONEASDDFF","DANONE", R.drawable.q11));
        kum_soal.add(new Soal("SDKNDAKODAKA","KODAK", R.drawable.q12));
        kum_soal.add(new Soal("UNILEVERASDS","UNILEVER", R.drawable.q13));
        kum_soal.add(new Soal("AOMHNZASDFGF","ANZ", R.drawable.q14));
        kum_soal.add(new Soal("ADOBEASDQWEE","ADOBE", R.drawable.q15));
        kum_soal.add(new Soal("ASDCANONQWEA","CANON", R.drawable.q16));
        kum_soal.add(new Soal("POISAPBISDQW","SAP", R.drawable.q17));
        kum_soal.add(new Soal("ASDNESTLEASD","NESTLE", R.drawable.q18));
        kum_soal.add(new Soal("PHNIKONASDSD","NIKON", R.drawable.q19));
        kum_soal.add(new Soal("SADAD213MDFA","3M", R.drawable.q20));
    }
    public void setSoalnormal(){
        kum_soal.add(new Soal("RENAULTQWEEE","RENAULT", R.drawable.m1));
        kum_soal.add(new Soal("ACURAASDQWED","ACURA", R.drawable.m2));
        kum_soal.add(new Soal("AUDIASDASDAS","AUDI", R.drawable.m3));
        kum_soal.add(new Soal("CHEVROLETQWE","CHEVROLET", R.drawable.m4));
        kum_soal.add(new Soal("DAIHATSUQWEE","DAIHATSU", R.drawable.m5));
        kum_soal.add(new Soal("DODGEQWEASDD","DODGE", R.drawable.m6));
        kum_soal.add(new Soal("FERRARIASDDE","FERRARI", R.drawable.m7));
        kum_soal.add(new Soal("DAEWOOASDDAE","DAEWOO", R.drawable.m8));
        kum_soal.add(new Soal("HONDAASDWQEE","HONDA", R.drawable.m9));
        kum_soal.add(new Soal("HYUNDAIASDDA","HYUNDAI", R.drawable.m10));
        kum_soal.add(new Soal("JAGUARASDQWE","JAGUAR", R.drawable.m11));
        kum_soal.add(new Soal("PORSCHEASDQW","PORSCHE", R.drawable.m12));
        kum_soal.add(new Soal("MAZDAASDQWED","MAZDA", R.drawable.m13));
        kum_soal.add(new Soal("NISSANASDQWE","NISSAN", R.drawable.m14));
        kum_soal.add(new Soal("OPELASDQWEAS","OPEL", R.drawable.m15));
        kum_soal.add(new Soal("PONTIACASDQW","PONTIAC", R.drawable.m16));
        kum_soal.add(new Soal("SUZUKIASDQWE","SUZUKI", R.drawable.m17));
        kum_soal.add(new Soal("TOYOTAASDQWE","TOYOTA", R.drawable.m18));
        kum_soal.add(new Soal("SUBARUASDQWE","SUBARU", R.drawable.m19));
        kum_soal.add(new Soal("LEXUXASDQWED","LEXUX", R.drawable.m20));
    }

    public void setSoalhard(){
        kum_soal.add(new Soal("AMAZONJSKDNA","AMAZON", R.drawable.q5));
        kum_soal.add(new Soal("ASDNIKRAFTDA","KRAFT", R.drawable.q6));
        kum_soal.add(new Soal("HYATTSASDDSG","HYATT", R.drawable.q7));
        kum_soal.add(new Soal("DISNEYSADASN","DISNEY", R.drawable.q8));
        kum_soal.add(new Soal("SUZUKIASDQWE","SUZUKI", R.drawable.m17));
        kum_soal.add(new Soal("TOYOTAASDQWE","TOYOTA", R.drawable.m18));
        kum_soal.add(new Soal("SUBARUASDQWE","SUBARU", R.drawable.m19));
        kum_soal.add(new Soal("LEXUXASDQWED","LEXUX", R.drawable.m20));
        kum_soal.add(new Soal("AOMHNZASDFGF","ANZ", R.drawable.q14));
        kum_soal.add(new Soal("ADOBEASDQWEE","ADOBE", R.drawable.q15));
        kum_soal.add(new Soal("ASDCANONQWEA","CANON", R.drawable.q16));
        kum_soal.add(new Soal("POISAPBISDQW","SAP", R.drawable.q17));
        kum_soal.add(new Soal("ASDNESTLEASD","NESTLE", R.drawable.q18));
        kum_soal.add(new Soal("PHNIKONASDSD","NIKON", R.drawable.q19));
        kum_soal.add(new Soal("SADAD213MDFA","3M", R.drawable.q20));
        kum_soal.add(new Soal("DAEWOOASDDAE","DAEWOO", R.drawable.m8));
        kum_soal.add(new Soal("HONDAASDWQEE","HONDA", R.drawable.m9));
        kum_soal.add(new Soal("HYUNDAIASDDA","HYUNDAI", R.drawable.m10));
        kum_soal.add(new Soal("JAGUARASDQWE","JAGUAR", R.drawable.m11));
        kum_soal.add(new Soal("PORSCHEASDQW","PORSCHE", R.drawable.m12));
    }

    public void ResultForm(){
        Intent intentResult = new Intent(getApplicationContext(), Result.class);
        String _nama = nama;
        String _score = String.valueOf(skor);
        String stats = "Permainan Selesai";
        intentResult.putExtra(EXTRA_NAMA , _nama);
        intentResult.putExtra(EXTRA_SCORE , _score);
        intentResult.putExtra(EXTRA_STATUS , stats);
        startActivity(intentResult);
    }
    public void ResultFormFail(){
        Intent intentResult = new Intent(getApplicationContext(), Result.class);
        String _nama = nama;
        String _score = String.valueOf(skor);
        String stats = "Anda Kurang Beruntung";
        intentResult.putExtra(EXTRA_NAMA , _nama);
        intentResult.putExtra(EXTRA_SCORE , _score);
        intentResult.putExtra(EXTRA_STATUS , stats);
        startActivity(intentResult);
    }



}

