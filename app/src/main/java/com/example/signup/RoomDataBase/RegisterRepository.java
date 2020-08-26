package com.example.signup.RoomDataBase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RegisterRepository {
    private RegisterDao registerDao;
    private RegisterRoomDataBase registerRoomDataBase;
    private LiveData<List<Register>> registerData;
    private LiveData<Register> register;

    public RegisterRepository(Application application){
        registerRoomDataBase = RegisterRoomDataBase.getDatabase(application);
        registerDao = registerRoomDataBase.registerDao();
        registerData = registerDao.getAllUser();
    }

    public LiveData<List<Register>> getAllUser() {
        return registerData;
    }

    public LiveData<Register> getUserByEmail(){
        RegisterRoomDataBase.databaseWriteExecutor.execute(()->{
            register = registerDao.getUserByEmail();
        });
        return register;
    }

    public void insertRegisterData(Register register){
        RegisterRoomDataBase.databaseWriteExecutor.execute(()->{
            registerDao.insert(register);
        });
    }
    public static class LoginInsertion extends AsyncTask<Register, Void, Void> {
        private RegisterDao registerDao;

        private LoginInsertion(RegisterDao registerDao){
            this.registerDao = registerDao;
        }
        @Override
        protected Void doInBackground(Register... data) {
            registerDao.insert(data[0]);
            return null;
        }
    }
}