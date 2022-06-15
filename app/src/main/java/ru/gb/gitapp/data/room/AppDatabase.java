package ru.gb.gitapp.data.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.gb.gitapp.domain.entities.UserEntity;

@Database(
        entities = {UserEntity.class},
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UsersDao userDao();
}