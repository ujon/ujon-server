package io.ujon.api.common.config

import io.ujon.api.common.middleware.AuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
class SecurityConfig(
    private val authenticationFilter: AuthenticationFilter
) {
    // todo: Change roleHierarchy dynamically with database.
    @Bean
    fun roleHierarchy(): RoleHierarchyImpl {
        val roleHierarchy = RoleHierarchyImpl()
        roleHierarchy.setHierarchy("ROLE_MASTER > ROLE_ADMIN > ROLE_USER");
        return roleHierarchy
    }

    @Bean
    fun expressionHandler(): DefaultMethodSecurityExpressionHandler {
        val expressionHandler = DefaultMethodSecurityExpressionHandler()
        expressionHandler.setRoleHierarchy(roleHierarchy())
        return expressionHandler
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain? {
        return http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .authorizeHttpRequests {
                it
                    .requestMatchers("/auth/**").permitAll()
                    .anyRequest().authenticated()
            }
            .sessionManagement {
                it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilterBefore(authenticationFilter, BasicAuthenticationFilter::class.java)
            .build()
    }

}