package io.ujon.domain.user

import io.ujon.common.exception.CommonException
import io.ujon.common.exception.ResponseType
import io.ujon.domain.user.dto.info.UserInfo
import io.ujon.domain.user.dto.info.UserSecretInfo
import io.ujon.domain.user.dto.operation.RegisterUserOperation
import io.ujon.domain.user.dto.operation.RetrieveUserOperation
import io.ujon.domain.user.dto.operation.RetrieveUserSecretOperation
import io.ujon.domain.user.mapper.UserServiceMapper
import io.ujon.infra.role.repository.RoleRepository
import io.ujon.infra.user.entity.User
import io.ujon.infra.user.entity.UserEmail
import io.ujon.infra.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val roleRepository: RoleRepository,
    private val userRepository: UserRepository,
    // mapper
    private val userMapper: UserServiceMapper,
) : UserService {
    override fun registerUser(operation: RegisterUserOperation.Email): UserInfo {
        if (userRepository.existsByEmail(operation.email)) throw CommonException(ResponseType.EMAIL_ALREADY_EXIST)
        if (userRepository.existsByUsername(operation.username)) throw CommonException(ResponseType.USERNAME_ALREADY_EXIST)
        val userRole = roleRepository.default()
        val user = User(
            username = operation.username,
            password = operation.password,
            name = operation.name,
            roleId = userRole.roleId
        )
        val email = UserEmail(
            email = operation.email,
            primary = true
        )
        user.addEmail(email)
        val savedUser = userRepository.save(user)
        return userMapper.toUserInfo(savedUser)
    }

    override fun retrieveUser(operation: RetrieveUserOperation): UserInfo {
        val user = userRepository.findByUsername(operation.username)
            ?: throw CommonException(ResponseType.USER_NOT_EXIST)
        return userMapper.toUserInfo(user)
    }

    override fun retrieveUserSecret(operation: RetrieveUserSecretOperation.Email): UserSecretInfo {
        val userSecret = userRepository.findUserSecretByEmail(operation.email)
            ?: throw CommonException(ResponseType.USER_NOT_EXIST)
        return userMapper.toUserSecretInfo(userSecret)
    }

    override fun retrieveUserSecret(operation: RetrieveUserSecretOperation.Username): UserSecretInfo {
        val userSecret = userRepository.findUserSecretByUsername(operation.username)
            ?: throw CommonException(ResponseType.USER_NOT_EXIST)
        return userMapper.toUserSecretInfo(userSecret)
    }

}