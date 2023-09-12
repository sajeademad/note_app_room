package com.camera.notes.ui.dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.camera.notes.ui.enteties.Note;

import java.util.List;

@androidx.room.Dao
public interface NoteDao {

    @Insert
    void insertNote(Note note);

    @Delete
    void deleteNote(Note note);

    @Update
    void updateNote(Note note);

    @Query("SELECT * FROM note_table ORDER BY id desc")
    List<Note> getAllNotes();

    @Query("SELECT * FROM note_table WHERE id =:id LIMIT 1")
    Note getNote(int id);



    @Query("SELECT * FROM note_table WHERE title LIKE :title")
    List<Note> searchNote(String title);


}
