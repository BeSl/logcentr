package com.itbm.logcentr.security

import io.jmix.security.role.annotation.ResourceRole
import io.jmix.security.role.annotation.SpecificPolicy
import io.jmix.securityflowui.role.UiMinimalPolicies
import io.jmix.securityflowui.role.annotation.MenuPolicy
import io.jmix.securityflowui.role.annotation.ViewPolicy

@ResourceRole(name = "UI: minimal access", code = UiMinimalRole.CODE, scope = ["UI"])
interface UiMinimalRole : UiMinimalPolicies {

    companion object {
        const val CODE = "ui-minimal"
    }

    @ViewPolicy(viewIds = ["MainView", "MarkReport.list"])
    fun main()

    @ViewPolicy(viewIds = ["LoginView"])
    @SpecificPolicy(resources = ["ui.loginToUi"])
    fun login()

    @MenuPolicy(menuIds = ["MarkReport.list"])
    fun screens()
}
