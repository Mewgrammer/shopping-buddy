package github.mewgrammer.shopbuddy.security.annotations

import github.mewgrammer.shopbuddy.security.model.Privilege
import github.mewgrammer.shopbuddy.security.model.Role

@Target(AnnotationTarget.FUNCTION)
@Retention(value = AnnotationRetention.RUNTIME)
annotation class HasAccess(val role: Role, val privilege: Privilege)
