package hw.appdev.example.android.roomwordsample;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ConcurrentModificationException;

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    private static volatile WordRoomDatabase sInstance;

    public abstract WordDao wordDao();

    static WordRoomDatabase getDatabase(final Context context) {
        if (sInstance == null) {
            synchronized (WordRoomDatabase.class) {
                if (sInstance == null) {
                    //create database here
                    sInstance = Room.databaseBuilder(context.getApplicationContext(), WordRoomDatabase.class, "word_database").addCallback(sRoomDatabaseCallback).build();
                }
            }
        }
        return sInstance;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(sInstance).execute();
                }
    };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        PopulateDbAsync(WordRoomDatabase db) {
            mDao = db.wordDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mDao.deleteAll();
            Word word = new Word("Hello");
            mDao.insert(word);
            word = new Word("World");
            mDao.insert(word);
            return null;
        }
    }
}
