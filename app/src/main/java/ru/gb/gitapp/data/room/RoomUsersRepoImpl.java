package ru.gb.gitapp.data.room;

import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import ru.gb.gitapp.domain.entities.UserEntity;
import ru.gb.gitapp.domain.repos.UsersRepo;

public class RoomUsersRepoImpl implements UsersRepo {
    private final UsersDao dao;
    private final Handler ioHandler;
    private final Handler uiHandler;

    public RoomUsersRepoImpl(
            UsersDao dao,
            Handler ioHandler,
            Handler uiHandler
    ) {
        this.dao = dao;
        this.ioHandler = ioHandler;
        this.uiHandler = uiHandler;
    }

    @Override
    public void getUsers(
            @NonNull Function1<? super List<UserEntity>, Unit> onSuccess,
            @Nullable Function1<? super Throwable, Unit> onError
    ) {
        ioHandler.post(() -> {
            final List<UserEntity> data = dao.getAll();
            uiHandler.post(() -> onSuccess.invoke(data));
        });
    }

    @Override
    public void saveUsers(@NonNull List<UserEntity> users) {
        ioHandler.post(() -> dao.insertAll(users));
    }
}
