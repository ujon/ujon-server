package io.ujon.application.auth.helper

import com.ninjasquad.springmockk.MockkBean
import io.kotest.core.spec.style.FunSpec
import io.kotest.extensions.spring.SpringExtension
import io.kotest.matchers.shouldBe
import io.ujon.application.TestApplication
import io.ujon.application.auth.type.AuthTokenType
import io.ujon.domain.user.UserService
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [TestApplication::class])
@EnableAutoConfiguration(exclude = [DataSourceAutoConfiguration::class])
class JwtHelperTest(
    jwtHelper: JwtHelper,
) : FunSpec({
    extensions(listOf(SpringExtension))

    test("jwt generate and validate") {
        val username = "jack"
        val authority = jwtHelper.authority(username)
        val accessToken = authority.accessToken
        val refreshToken = authority.refreshToken
        jwtHelper.validate(accessToken, AuthTokenType.ACCESS_TOKEN).username shouldBe username
        jwtHelper.validate(refreshToken, AuthTokenType.REFRESH_TOKEN).username shouldBe username
    }
}) {
    @MockkBean
    lateinit var userService: UserService
}
