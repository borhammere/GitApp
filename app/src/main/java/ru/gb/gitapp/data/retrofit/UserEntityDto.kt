package ru.gb.gitapp.data.retrofit

import com.google.gson.annotations.SerializedName
import ru.gb.gitapp.domain.entities.UserEntity

data class UserEntityDto(
    val login: String,
    val id: Long,
    @SerializedName("avatar_url")
    val avatarUrl: String
) {
    fun toUserEntity() = UserEntity(login, id, avatarUrl)

    companion object {
        fun fromUserEntity(userEntity: UserEntity): UserEntityDto {
            return UserEntityDto(
                userEntity.login,
                userEntity.id,
                userEntity.avatarUrl
            )
        }
    }
}