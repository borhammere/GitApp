package ru.gb.gitapp.domain.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class UserEntity(
    val login: String,
    @PrimaryKey val id: Long,
    @SerializedName("avatar_url") @ColumnInfo(name = "avatar_url") val avatarUrl: String
)