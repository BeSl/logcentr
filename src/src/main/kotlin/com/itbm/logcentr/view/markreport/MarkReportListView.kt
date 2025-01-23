package com.itbm.logcentr.view.markreport

import com.itbm.logcentr.entity.ExtServers
import com.itbm.logcentr.entity.MarkReport
import com.itbm.logcentr.view.main.MainView
import com.vaadin.flow.component.AbstractField
import com.vaadin.flow.component.ClickEvent
import com.vaadin.flow.component.combobox.ComboBox
import com.vaadin.flow.component.html.Span
import com.vaadin.flow.router.Route
import io.jmix.core.DataManager
import io.jmix.core.LoadContext
import io.jmix.core.Metadata
import io.jmix.core.querycondition.PropertyCondition
import io.jmix.flowui.Notifications
import io.jmix.flowui.component.combobox.JmixComboBox
import io.jmix.flowui.kit.component.button.JmixButton
import io.jmix.flowui.model.InstanceContainer
import io.jmix.flowui.view.*
import io.jmix.flowui.view.Target
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.beans.factory.annotation.Autowired
import java.awt.Label


@Route(value = "markReports", layout = MainView::class)
@ViewController(id = "MarkReport.list")
@ViewDescriptor(path = "mark-report-list-view.xml")
@LookupComponent("markReportsDataGrid")
@DialogMode(width = "50em")
class MarkReportListView : StandardListView<MarkReport>() {
    @Autowired
    private lateinit var dataManager: DataManager

    @Autowired
    protected var notifications: Notifications? = null

    @ViewComponent
    private lateinit var serverList: ComboBox<ExtServers>

    @ViewComponent
    private lateinit var CHBage: Span

    @Subscribe
    fun onInit(event: View.InitEvent) {

    }
    @Install(to = "markReportsDl", target = Target.DATA_LOADER)
    fun markReportsDlLoadDelegate(loadContext: LoadContext<MarkReport>): MutableList<MarkReport> {
        val url = "https://localhost:8123"

        val markReports = mutableListOf<MarkReport>()
        val repo = dataManager.create(MarkReport::class.java)
        repo.id = 1
        markReports.add(repo)
        // Here you can load entities from an external store.
        // Set the loaded entities to the not-new state using EntityStates.setNew(entity, false).
        return mutableListOf()
    }

    @Install(to = "markReportsDataGrid.remove", subject = "delegate")
    fun markReportsDataGridRemoveDelegate(entities: Collection<MarkReport>) {
        // Here you can remove entities from an external storage
    }

    @Subscribe(id = "CheckButtonClick", subject = "clickListener")
    private fun onCheckButtonClickClick(event: ClickEvent<JmixButton>) {
        val client = OkHttpClient()
        if (CHBage.text == "") {
            return
        }

        val request = Request.Builder()
            .url(CHBage.text)
            .build()

        val response = client.newCall(request).execute()
        notifications?.show("Result ${response.code} - ${response.body?.string()}");
        response.close()
    }

    @Subscribe("serverList")
    private fun onServerListComponentValueChange(event: AbstractField.ComponentValueChangeEvent<JmixComboBox<String>, String>) {

        if (event.value == event.oldValue) {
            return
        }

        val data = dataManager.load(ExtServers::class.java).condition(
            PropertyCondition.contains("name", event.value))
        .one()

        CHBage.text = "http://${data?.host}:${data?.port}"
    }

}