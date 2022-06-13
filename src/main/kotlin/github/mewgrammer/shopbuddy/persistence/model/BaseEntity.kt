package github.mewgrammer.shopbuddy.persistence.model

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.Instant
import java.util.UUID
import javax.persistence.*


@MappedSuperclass
abstract class BaseEntity(
    @Id
    @Column(name = "id")
    open val id: UUID = UUID.randomUUID(),

    @CreationTimestamp
    @Column(name = "createdAt")
    open val createdAt: Instant? = null,

    @UpdateTimestamp
    @Column(name = "updatedAt")
    open val updatedAt: Instant? = null,
)