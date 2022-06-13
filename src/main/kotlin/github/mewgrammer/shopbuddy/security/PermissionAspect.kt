package github.mewgrammer.shopbuddy.security

import github.mewgrammer.shopbuddy.security.annotations.HasAccess
import github.mewgrammer.shopbuddy.security.annotations.HasPrivilege
import github.mewgrammer.shopbuddy.security.annotations.HasRole
import github.mewgrammer.shopbuddy.security.annotations.PermissionHelper
import github.mewgrammer.shopbuddy.security.model.Privilege
import github.mewgrammer.shopbuddy.security.model.Role
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Component
import org.springframework.security.access.AccessDeniedException;

@Aspect
@Component
class PermissionAspect {

    @Before("@annotation(github.mewgrammer.shopbuddy.security.annotations.HasAccess)")
    fun hasAccess(jp: JoinPoint) {
        val annotation = getAnnotation<HasAccess>(jp)
        val user = getCurrentUser()
        if(!hasRole(user, annotation.role) || !hasPrivilege(user, annotation.privilege))
            throw throw AccessDeniedException(ACCESS_DENIED_TEXT)
    }

    @Before("@annotation(github.mewgrammer.shopbuddy.security.annotations.HasRole)")
    fun hasRole(jp: JoinPoint) {
        val annotation = getAnnotation<HasRole>(jp)
        val user = getCurrentUser()
        if(!hasRole(user, annotation.role))
            throw throw AccessDeniedException(ACCESS_DENIED_TEXT)
    }

    @Before("@annotation(github.mewgrammer.shopbuddy.security.annotations.HasPrivilege)")
    fun hasPrivilege(jp: JoinPoint) {
        val annotation = getAnnotation<HasPrivilege>(jp)
        val user = getCurrentUser()
        if(!hasPrivilege(user, annotation.privilege))
            throw throw AccessDeniedException(ACCESS_DENIED_TEXT)
    }

    private inline fun <reified T: Annotation> getAnnotation(jp: JoinPoint): T {
        val signature = jp.signature as MethodSignature
        return signature.method.getAnnotation(T::class.java)
    }

    private fun hasRole(user: User, role: Role): Boolean {
        return user.authorities.any {
            PermissionHelper.hasRole(
                user,
                role
            )
        }
    }

    private fun getCurrentUser(): User {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication.principal !is User) throw AccessDeniedException(ACCESS_DENIED_TEXT)
        return authentication.principal as User
    }

    private fun hasPrivilege(user: User, privilege: Privilege): Boolean {
        return user.authorities.any {
            PermissionHelper.hasPrivilege(
                user,
                privilege
            )
        }
    }

    companion object {
        const val ACCESS_DENIED_TEXT = "You do not have the permissions to access this resource"
    }
}