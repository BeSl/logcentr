package com.itbm.logcentr.view.extservers

import com.itbm.logcentr.entity.ExtServers
import com.itbm.logcentr.view.main.MainView
import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.html.Span
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route
import io.jmix.flowui.Notifications
import io.jmix.flowui.kit.component.button.JmixButton
import io.jmix.flowui.view.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Autowired

@Route(value = "extServerses/:id", layout = MainView::class)
@ViewController(id = "ExtServers.detail")
@ViewDescriptor(path = "ext-servers-detail-view.xml")
@EditedEntityContainer("extServersDc")
class ExtServersDetailView : StandardDetailView<ExtServers>() {

    @Autowired
    protected var notifications: Notifications? = null

    @ViewComponent
    private lateinit var hostField: TextField
    @ViewComponent
    private lateinit var portField: TextField

    @Subscribe(id = "pingButton", subject = "clickListener")
    private fun onPingButtonClick(event: ClickEvent<JmixButton>) {
        val client = OkHttpClient()
        if (hostField.value.isEmpty()
            || portField.value.isEmpty())
        {
            return
        }

        val request = Request.Builder()
            .url("http://${hostField.value}:${portField.value}")
            .build()

        val response = client.newCall(request).execute()
        notifications?.show("Result ${response.code} - ${response.body?.string()}");
        response.close()
    }
}