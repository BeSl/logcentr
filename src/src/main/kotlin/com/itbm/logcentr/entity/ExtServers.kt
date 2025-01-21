package com.itbm.logcentr.entity

import io.jmix.core.entity.annotation.JmixGeneratedValue
import io.jmix.core.metamodel.annotation.InstanceName
import io.jmix.core.metamodel.annotation.JmixEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@JmixEntity
@Table(name = "EXT_SERVERS")
@Entity
open class ExtServers {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    var id: UUID? = null

    @InstanceName
    @Column(name = "NAME")
    var name: String? = null

    @Column(name = "HOST")
    var host: String? = null

    @Column(name = "PORT", length = 10)
    var port: String? = null

    @Column(name = "LOGIN", length = 40)
    var login: String? = null

    @Column(name = "PASSWORD")
    var password: String? = null
}