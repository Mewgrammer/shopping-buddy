package github.mewgrammer.shopbuddy.config

import github.mewgrammer.shopbuddy.security.model.Privilege
import github.mewgrammer.shopbuddy.security.model.Role
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.core.GrantedAuthorityDefaults
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.web.cors.CorsConfiguration


@EnableWebSecurity
class WebSecurityConfiguration {

    private val AUTH_WHITELIST = arrayOf( // -- Swagger UI v2
        "/actuator/health",
        "/swagger-resources",
        "/swagger-resources/**",
        "/configuration/ui",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        "/v3/api-docs/**",
        "/swagger-ui/**"
    )

    @Bean
    fun authenticationManager(auth: AuthenticationManagerBuilder): InMemoryUserDetailsManager {
        val users = arrayOf(
            User.withUsername("admin")
                .password(passwordEncoder().encode("admin"))
                .authorities(Role.ADMIN.name, Privilege.READ.name, Privilege.WRITE.name)
                .build(),
            User.withUsername("user")
                .password(passwordEncoder().encode("user"))
                .authorities(Role.USER.name, Privilege.READ.name, Privilege.WRITE.name)
                .build(),
            User.withUsername("guest")
                .password(passwordEncoder().encode("guest"))
                .authorities(Role.GUEST.name, Privilege.READ.name)
                .build()
        )
        return InMemoryUserDetailsManager(*users)
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .cors().configurationSource { corsConfig() }
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .csrf().disable()
            .authorizeRequests()
            .antMatchers(*AUTH_WHITELIST)
            .permitAll()
            .and()
            .httpBasic().realmName("taskbook")
            .and()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun grantedAuthorityDefaults(): GrantedAuthorityDefaults? {
        return GrantedAuthorityDefaults("") // Remove the ROLE_ prefix
    }

    private fun corsConfig(): CorsConfiguration {
        val corsConfiguration = CorsConfiguration()
        corsConfiguration.allowedHeaders = listOf("*")
        corsConfiguration.allowedOriginPatterns = listOf("*")
        corsConfiguration.allowedMethods = listOf("*")
        corsConfiguration.allowCredentials = true
        corsConfiguration.exposedHeaders = listOf("*")
        return corsConfiguration
    }
}