package edu.stts.quiz_uas;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = {Pemain.class},version = 1)
public abstract class AppDatabase  extends RoomDatabase {
    public  abstract PemainDAO pemainDAO();
}
