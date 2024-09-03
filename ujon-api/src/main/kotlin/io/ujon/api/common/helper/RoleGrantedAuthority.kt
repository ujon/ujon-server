package io.ujon.api.common.helper

import org.springframework.security.core.GrantedAuthority

class RoleGrantedAuthority(private val role: String) : GrantedAuthority {
    override fun getAuthority(): String {
        return "ROLE_${role.uppercase()}"
    }
}