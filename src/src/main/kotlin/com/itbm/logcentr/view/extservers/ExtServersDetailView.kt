package com.itbm.logcentr.view.extservers

import com.itbm.logcentr.entity.ExtServers
import com.itbm.logcentr.view.main.MainView
import com.vaadin.flow.router.Route
import io.jmix.flowui.view.EditedEntityContainer
import io.jmix.flowui.view.StandardDetailView
import io.jmix.flowui.view.ViewController
import io.jmix.flowui.view.ViewDescriptor

@Route(value = "extServerses/:id", layout = MainView::class)
@ViewController(id = "ExtServers.detail")
@ViewDescriptor(path = "ext-servers-detail-view.xml")
@EditedEntityContainer("extServersDc")
class ExtServersDetailView : StandardDetailView<ExtServers>() {
}