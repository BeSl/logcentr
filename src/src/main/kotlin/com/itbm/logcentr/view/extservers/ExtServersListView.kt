package com.itbm.logcentr.view.extservers

import com.itbm.logcentr.entity.ExtServers
import com.itbm.logcentr.view.main.MainView
import com.vaadin.flow.router.Route
import io.jmix.flowui.view.*


@Route(value = "extServerses", layout = MainView::class)
@ViewController(id = "ExtServers.list")
@ViewDescriptor(path = "ext-servers-list-view.xml")
@LookupComponent("extServersesDataGrid")
@DialogMode(width = "64em")
class ExtServersListView : StandardListView<ExtServers>() {
}