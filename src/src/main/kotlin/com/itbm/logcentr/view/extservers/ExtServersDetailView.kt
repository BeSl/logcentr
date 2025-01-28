package com.itbm.logcentr.view.extservers

import com.itbm.logcentr.entity.ExtServers
import com.itbm.logcentr.view.main.MainView
import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.combobox.ComboBox
import com.vaadin.flow.component.html.Span
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route
import io.jmix.flowui.Notifications
import io.jmix.flowui.kit.component.button.JmixButton
import io.jmix.flowui.view.*
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
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

    @ViewComponent
    private lateinit var dbNameField: ComboBox<String>

    @Subscribe(id = "pingButton", subject = "clickListener")
    private fun onPingButtonClick(event: ClickEvent<JmixButton>) {
        val client = OkHttpClient()
        if (hostField.value.isEmpty()
            || portField.value.isEmpty())
        {
            return
        }
        val urlServer = "http://${hostField.value}:${portField.value}"
        val request = Request.Builder()
            .url(urlServer)
            .build()

        val response = client.newCall(request).execute()
        notifications?.show("Result ${response.code} - ${response.body?.string()}");
        if (response.code == 200){
            dbNameField.setItems(ListDBServer(urlServer))
        }
        response.close()
    }

    fun ListDBServer(url: String):List<String> {
        
        val Body = RequestBody.create(okhttp3.MediaType.parse("application/json; charset=utf-8"), url)
        val r = Request.Builder()
            .url(url)
            .build()

    }
}