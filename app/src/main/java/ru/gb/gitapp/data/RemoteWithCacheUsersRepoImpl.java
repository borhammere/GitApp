package ru.gb.gitapp.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import ru.gb.gitapp.domain.entities.UserEntity;
import ru.gb.gitapp.domain.repos.UsersRepo;

public class RemoteWithCacheUsersRepoImpl implements UsersRepo {
    private UsersRepo remoteRepo;
    private UsersRepo cacheRepo;

    public RemoteWithCacheUsersRepoImpl(UsersRepo remoteRepo, UsersRepo cacheRepo) {
        this.remoteRepo = remoteRepo;
        this.cacheRepo = cacheRepo;
    }

    @Override
    public void saveUsers(@NonNull List<UserEntity> users) {
        // pass
    }

    @Override
    public void getUsers(@NonNull Function1<? super List<UserEntity>, Unit> onSuccess, @Nullable Function1<? super Throwable, Unit> onError) {
        remoteRepo.getUsers(
                (List<UserEntity> userEntities) -> {
                    cacheRepo.saveUsers(userEntities);
                    onSuccess.invoke(userEntities);
                    return null;
                },
                (Throwable throwable) -> {
                    cacheRepo.getUsers(
                            (List<UserEntity> userEntities) -> {
                                onSuccess.invoke(userEntities);
                                return null;
                            },
                            (Throwable throwable2) -> {
                                onError.invoke(throwable2);
                                return null;
                            }
                    );
                    return null;
                }
        );
    }
}
