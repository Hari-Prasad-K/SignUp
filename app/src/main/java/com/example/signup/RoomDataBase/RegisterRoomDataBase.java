package com.example.signup.RoomDataBase;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(version = 1, exportSchema = false, entities = Register.class)
public abstract class RegisterRoomDataBase extends RoomDatabase {
    abstract RegisterDao registerDao();
    private static final String Db_name = "register_db";
    private static final int NUMBER_OF_THREADS = 6;
    private static volatile RegisterRoomDataBase instance;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static RegisterRoomDataBase getDatabase(final Context context){
        if(instance==null) {
            synchronized (RegisterRoomDataBase.class){
                if(instance==null){
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                            RegisterRoomDataBase.class,Db_name).fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }
    public static RoomDatabase.Callback RegisterRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase registerRoomDatabase){
            super.onOpen(registerRoomDatabase);
            databaseWriteExecutor.execute(()->{
                RegisterDao dao = instance.registerDao();
                dao.deleteAll();
            });
        }
    };
}
