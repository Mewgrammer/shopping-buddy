package github.mewgrammer.shopbuddy.persistence.audit

import org.springframework.data.domain.AuditorAware
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import java.util.*

@Component
class UserAuditAware : AuditorAware<String> {
    override fun getCurrentAuditor(): Optional<String> {
        val currentUser = SecurityContextHolder.getContext().authentication.principal as User
        return Optional.ofNullable(currentUser.username)
    }
}
