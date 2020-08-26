package com.example.signup.RoomDataBase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;
@Dao
public interface RegisterDao {



        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void insert(Register register);

        @Query("Delete from registration")
        void deleteAll();

        @Query("Select * from registration")
        LiveData<List<Register>> getAllUser();

        @Query("Select * from registration where user_email = ':user_email' ")
        LiveData<Register> getUserByEmail();
    }

