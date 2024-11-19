package io.ujon.domain.user.mapper

import io.ujon.domain.user.dto.info.UserInfo
import io.ujon.infra.role.entity.Role
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

        @JvmStatic
        @Named("roleName")
        fun roleName(role: Role): String = role.name
    }

    // entity -> info
    @Mapping(target = "email", source = "user", qualifiedByName = ["primaryEmail"])
    @Mapping(target = "role", source = "role", qualifiedByName = ["roleName"])
    fun toUserInfo(user: User): UserInfo
}