package com.camera.notes.ui.database;

import android.content.Context;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.camera.notes.ui.dao.NoteDao;
import com.camera.notes.ui.enteties.Note;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;



@Database(entities = Note.class , version = 1, exportSchema = false)
public abstract class NoteDatabase extends RoomDatabase {
    public static NoteDatabase noteDatabase;
    public abstract NoteDao noteDOA();

    private static final int NUMBER_OF_THREADS = 4;

    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static synchronized NoteDatabase getNoteDatabase(Context context) {
        if(noteDatabase==null){
            noteDatabase = Room.databaseBuilder(
                    context, NoteDatabase.class
                    , "note_db"
            ).build();
        }
        return noteDatabase;
    }

}


