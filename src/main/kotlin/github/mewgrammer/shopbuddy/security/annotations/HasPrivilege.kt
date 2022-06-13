package github.mewgrammer.shopbuddy.security.annotations

import github.mewgrammer.shopbuddy.security.model.Privilege

@Target(AnnotationTarget.FUNCTION)
@Retention(value = AnnotationRetention.RUNTIME)
annotation class HasPrivilege(val privilege: Privilege)
