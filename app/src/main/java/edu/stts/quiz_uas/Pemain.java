package edu.stts.quiz_uas;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName="tbpemain")
public class Pemain implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int idpemain;
    @ColumnInfo(name = "mode_pemain")
    private String modepemain;
    @ColumnInfo(name = "nama_pemain")
    private String namapemain;
    @ColumnInfo(name = "nilai_pemain")
    private String nilaipemain;

    public Pemain(String modepemain, String namapemain, String nilaipemain) {
        this.modepemain = modepemain;
        this.namapemain = namapemain;
        this.nilaipemain = nilaipemain;
    }

    public int getIdpemain() {
        return idpemain;
    }

    public void setIdpemain(int idpemain) {
        this.idpemain = idpemain;
    }

    public String getModepemain() {
        return modepemain;
    }

    public void setModepemain(String modepemain) {
        this.modepemain = modepemain;
    }

    public String getNamapemain() {
        return namapemain;
    }

    public void setNamapemain(String namapemain) {
        this.namapemain = namapemain;
    }

    public String getNilaipemain() {
        return nilaipemain;
    }

    public void setNilaipemain(String nilaipemain) {
        this.nilaipemain = nilaipemain;
    }

    @Override
    public String toString() {
        return  ""+ modepemain+" - "+namapemain+" - "+ nilaipemain+"";
    }


}
