package com.example.asus.project_penir;

/**
 * Created by ASUS on 3/29/2017.
 */

public class Soal {
    private String soal;
    private String jawaban;
    private int gambar;


    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getJawaban() {
        return jawaban;
    }

    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }

    public int getGambar() {
        return gambar;
    }

    public void setGambar(int gambar) {
        this.gambar = gambar;
    }

    public Soal(String soal, String jawaban, int gambar) {
        this.soal = soal;
        this.jawaban = jawaban;
        this.gambar = gambar;
    }
}
