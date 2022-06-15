package ru.gb.gitapp.data.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import ru.gb.gitapp.domain.entities.UserEntity;

@Dao
public interface UsersDao {
    @Query("SELECT * FROM users")
    List<UserEntity> getAll();

    @Insert
    void insertAll(List<UserEntity> users);

}
