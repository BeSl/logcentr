package com.itbm.logcentr.view.markreport

import com.itbm.logcentr.entity.MarkReport
import com.itbm.logcentr.view.main.MainView
import com.vaadin.flow.router.Route
import io.jmix.core.DataManager
import io.jmix.core.LoadContext
import io.jmix.flowui.view.*
import io.jmix.flowui.view.Target
import org.springframework.beans.factory.annotation.Autowired

@Route(value = "markReports", layout = MainView::class)
@ViewController(id = "MarkReport.list")
@ViewDescriptor(path = "mark-report-list-view.xml")
@LookupComponent("markReportsDataGrid")
@DialogMode(width = "50em")
class MarkReportListView : StandardListView<MarkReport>() {
    @Autowired
    private lateinit var dataManager: DataManager

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
}