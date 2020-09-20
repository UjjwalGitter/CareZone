package com.ujjwalsingh.carezone.DataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ujjwalsingh.carezone.DataBase.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(Note note);
    @Delete
    void delete(Note note);
    @Update
    void update(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("Select * from note_table")
    LiveData<List<Note>> getAllNotes();

    @Query("DELETE FROM note_table WHERE id = :userId")
    abstract void deleteByUserId(int userId);

}
