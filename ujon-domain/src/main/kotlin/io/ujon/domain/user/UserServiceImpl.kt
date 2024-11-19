package io.ujon.domain.user

import io.ujon.common.exception.CommonException
import io.ujon.common.exception.ResponseType
import io.ujon.domain.user.dto.info.UserInfo
import io.ujon.domain.user.dto.operation.RegisterUserOperation
import io.ujon.domain.user.mapper.UserServiceMapper
import io.ujon.infra.role.repository.RoleRepository
import io.ujon.infra.user.entity.User
import io.ujon.infra.user.entity.UserEmail
import io.ujon.infra.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
    // mapper
    private val userMapper: UserServiceMapper,
    // util
    private val passwordEncoder: PasswordEncoder,
) : UserService {
    override fun registerUser(operation: RegisterUserOperation.Email): UserInfo {
        if (userRepository.existsByEmail(operation.email)) throw CommonException(ResponseType.EMAIL_ALREADY_EXIST)
        if (userRepository.existsByUsername(operation.username)) throw CommonException(ResponseType.USERNAME_ALREADY_EXIST)
        val userRole = roleRepository.default()
        val user = User(
            username = operation.username,
            password = operation.password,
            name = operation.name,
            role = userRole
        )
        val email = UserEmail(
            email = operation.email,
            primary = true
        )
        user.addEmail(email)
        val savedUser = userRepository.save(user)
        return userMapper.toUserInfo(savedUser)
    }

    override fun retrieve(username: String): UserInfo {
        val user = userRepository.findByUsername(username)
            ?: throw CommonException(ResponseType.USER_NOT_EXIST)
        return userMapper.toUserInfo(user)
    }

    override fun verify(passcode: String): UserInfo {
        val user = userRepository.findByPasscode(passcode)
            ?: throw CommonException(ResponseType.INVALID_PASSCODE)
        return userMapper.toUserInfo(user)
    }

    override fun verify(email: String, password: String): UserInfo {
        val user = userRepository.findByEmail(email)
            ?: throw CommonException(ResponseType.USER_NOT_EXIST)
        if (!passwordEncoder.matches(password, user.password)) {
            throw CommonException(ResponseType.INVALID_PASSWORD)
        }
        return userMapper.toUserInfo(user)
    }

}