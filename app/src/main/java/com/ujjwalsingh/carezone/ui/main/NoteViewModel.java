package com.ujjwalsingh.carezone.ui.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.ujjwalsingh.carezone.DataBase.Note;
import com.ujjwalsingh.carezone.DataBase.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository=new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }

    public void insert(Note note){
        repository.insert(note);
    }
    public void delete(Note note){
        repository.delete(note);
    }
    public void update(Note note){
        repository.update(note);
    }
    public void deleteAllNotes(){
        repository.deleteAllNotes();
    }
    public void deleteByUserId(int id){
        repository.deleteByUserId(id);
    }

    public LiveData<List<Note>> getAllNotes(){
//        LiveData<List<Note>> m = allNotes;
//        List<Note> sd = m.getValue();
//        int s = sd.size();
//        Log.i("debugna", String.valueOf(s));
     return allNotes;
    }
}
