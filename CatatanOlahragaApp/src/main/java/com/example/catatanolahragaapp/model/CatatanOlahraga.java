
package com.example.catatanolahragaapp.model;


import java.time.LocalDate;

/**
 * Model untuk Catatan Latihan Olahraga Harian.
 * Atribut: tanggal (LocalDate), jenisOlahraga (String), durasi (int).
 */
public class CatatanOlahraga {
    private LocalDate tanggal;
    private String jenisOlahraga;
    private int durasi;  // dalam menit

    public CatatanOlahraga(LocalDate tanggal, String jenisOlahraga, int durasi) {
        this.tanggal = tanggal;
        this.jenisOlahraga = jenisOlahraga;
        this.durasi = durasi;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }

    public String getJenisOlahraga() {
        return jenisOlahraga;
    }

    public void setJenisOlahraga(String jenisOlahraga) {
        this.jenisOlahraga = jenisOlahraga;
    }

    public int getDurasi() {
        return durasi;
    }

    public void setDurasi(int durasi) {
        this.durasi = durasi;
    }
}