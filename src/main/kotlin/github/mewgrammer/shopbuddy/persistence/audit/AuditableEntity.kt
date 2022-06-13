package github.mewgrammer.shopbuddy.persistence.audit

import github.mewgrammer.shopbuddy.persistence.model.BaseEntity
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.LastModifiedBy
import javax.persistence.Column

abstract class AuditableEntity(
    @CreatedBy
    @Column(name = "createdBy")
    val createdBy: String? = null,

    @LastModifiedBy
    @Column(name = "updatedBy")
    val updatedBy: String? = null,
) : BaseEntity()