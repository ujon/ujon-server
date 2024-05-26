package io.ujon.domain.user.mapper

import io.ujon.domain.user.dto.info.UserInfo
import io.ujon.domain.user.dto.info.UserSecretInfo
import io.ujon.infra.user.entity.User
import io.ujon.infra.user.projection.UserSecret
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Named

@Mapper(componentModel = "spring")
interface UserServiceMapper {
    companion object {
        @JvmStatic
        @Named("primaryEmail")
        fun primaryEmail(user: User): String = user.primaryEmail().email
    }

    // entity -> info
    @Mapping(target = "email", source = "user", qualifiedByName = ["primaryEmail"])
    fun toUserInfo(user: User): UserInfo
    fun toUserSecretInfo(user: UserSecret): UserSecretInfo
}