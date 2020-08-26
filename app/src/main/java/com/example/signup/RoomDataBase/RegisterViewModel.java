package com.example.signup.RoomDataBase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RegisterViewModel extends AndroidViewModel {

    private RegisterRepository registerRepository;
    private LiveData<List<Register>> registerData;
    private LiveData<Register> register;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        registerRepository = new RegisterRepository(application);
        registerData = registerRepository.getAllUser();

    }

    LiveData<List<Register>> getAllUser() { return registerData; }

    public void insert(Register register){
        registerRepository.insertRegisterData(register);
    }
//    public LiveData<Register> getUserByRollNo() {
//        register = registerRepository.getUserByRollNo();
//        return register;
//    }
}