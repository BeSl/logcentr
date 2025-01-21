package com.itbm.logcentr.entity

import io.jmix.core.entity.annotation.JmixGeneratedValue
import io.jmix.core.entity.annotation.JmixId
import io.jmix.core.metamodel.annotation.JmixEntity

@JmixEntity
open class MarkReport {
    @JmixGeneratedValue
    @JmixId
    var id: Long? = null

    var username: String? = null

    var operation: String? = null

    var duration: Long? = null
}