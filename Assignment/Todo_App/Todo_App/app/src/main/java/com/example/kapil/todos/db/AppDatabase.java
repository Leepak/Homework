package com.example.kapil.todos.db;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.example.kapil.todos.model.Todo;

@Database(entities = {Todo.class}, version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appDatabase;

    public abstract TodoDao todoDao();

    public static synchronized AppDatabase getAppDatabase(Context context){
        if(appDatabase == null){
            appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class,"todo_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return appDatabase;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDb(appDatabase).execute();
        }
    };
    private static class PopulateDb extends AsyncTask<Void, Void, Void>{
        private TodoDao todoDao;

        private PopulateDb(AppDatabase database){
            todoDao = database.todoDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            todoDao.insert(new Todo("title 1","Description 1, created_at"));
            return null;
        }
    }

}
