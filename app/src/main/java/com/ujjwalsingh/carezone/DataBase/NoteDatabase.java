package com.ujjwalsingh.carezone.DataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class},version = 2, exportSchema = true)
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;
    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"note_database.db").
                    fallbackToDestructiveMigration().
                    addCallback(roomCallback).
                    build();
        }
            return instance;
    }

    private static RoomDatabase.Callback roomCallback =new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;

        public PopulateDbAsyncTask(NoteDatabase noteDatabase) {
            noteDao = noteDatabase.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Paracetamol","1:00 AM","5 min",2,3));
            noteDao.insert(new Note("Diabel","2:00 PM","10 min",1,1));
            noteDao.insert(new Note("Inject","3:00 PM","14 min",3,3));
            noteDao.insert(new Note("Crocin","4:00 PM","16 min",1,2));
            noteDao.insert(new Note("Diabel","5:00 PM","10 min",2,1));
//            noteDao.insert(new Note(R.drawable.drug,"crocin","3:00 am","15 min",3,1,true));
            return null;
        }
    }

}
