package io.ujon.api.common.middleware

import io.ujon.api.common.dto.AccountPrincipal
import io.ujon.application.auth.AuthFacade
import io.ujon.application.user.UserFacade
import io.ujon.application.user.dto.input.RetrieveUserSecret
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
class AuthenticationFilter(
    private val authFacade: AuthFacade,
    private val userFacade: UserFacade,
) : OncePerRequestFilter() {
    private val prefixBearer = "bearer "

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = token(request)
        if (token != null) {
            val claims = authFacade.verifyToken(token)
            val userSecret = userFacade.retrieveUserSecret(
                RetrieveUserSecret(claims.username)
            )
            val principal = AccountPrincipal(
                userId = userSecret.userId,
            )

            val authorities = listOf(
                userSecret.role?.let { SimpleGrantedAuthority(formatRoleName(it)) },
            )

            val authentication = UsernamePasswordAuthenticationToken(principal, token, authorities)
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }

    private fun token(request: HttpServletRequest): String? {
        val authorization = request.getHeader(HttpHeaders.AUTHORIZATION)

        return authorization
            ?.takeIf { it.startsWith(prefixBearer, ignoreCase = true) }
            ?.drop(prefixBearer.length)
            ?.trim()
    }

    /**
     * Formats a role name by prefixing it with "ROLE_" and converting it to uppercase.
     *
     * @param role the input role name to be formatted
     * @return the formatted role name
     */
    private fun formatRoleName(role: String): String {
        return "ROLE_${role}"
    }
}