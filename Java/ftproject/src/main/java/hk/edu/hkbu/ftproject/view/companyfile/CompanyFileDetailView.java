package hk.edu.hkbu.ftproject.view.companyfile;

import com.vaadin.flow.component.ClickEvent;
import hk.edu.hkbu.ftproject.entity.CompanyFile;

import hk.edu.hkbu.ftproject.entity.ProcessStatus;
import hk.edu.hkbu.ftproject.services.StockDataService;
import hk.edu.hkbu.ftproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.core.FileRef;
import io.jmix.flowui.Notifications;
import io.jmix.flowui.action.view.DetailSaveCloseAction;
import io.jmix.flowui.backgroundtask.BackgroundTask;
import io.jmix.flowui.backgroundtask.BackgroundTaskHandler;
import io.jmix.flowui.backgroundtask.BackgroundWorker;
import io.jmix.flowui.backgroundtask.TaskLifeCycle;
import io.jmix.flowui.component.layout.ViewLayout;
import io.jmix.flowui.component.select.JmixSelect;
import io.jmix.flowui.component.textfield.TypedTextField;
import io.jmix.flowui.component.upload.FileStorageUploadField;
import io.jmix.flowui.component.upload.FileUploadField;
import io.jmix.flowui.component.upload.receiver.FileTemporaryStorageBuffer;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.kit.component.upload.event.FileUploadSucceededEvent;
import io.jmix.flowui.upload.TemporaryStorage;
import io.jmix.flowui.view.*;
import org.jfree.util.Log;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.UUID;

@Route(value = "companyFiles/:id", layout = MainView.class)
@ViewController("ftp_CompanyFile.detail")
@ViewDescriptor("company-file-detail-view.xml")
@EditedEntityContainer("companyFileDc")
public class CompanyFileDetailView extends StandardDetailView<CompanyFile> {
    @ViewComponent
    private JmixButton saveAndCloseBtn;
    @ViewComponent
    private TypedTextField<String> symbolField;
    @ViewComponent
    private JmixSelect<Object> statusField;
    @ViewComponent
    private DetailSaveCloseAction<Object> saveAction;
    @Autowired
    private StockDataService stockDataService;
    @Autowired
    private BackgroundWorker backgroundWorker;


    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {

        if (this.getEditedEntity().getSymbol() != null) {
            if (this.statusField != null && ProcessStatus.PROCESSING.equals(this.statusField.getValue())) {

                this.saveAction.setEnabled(false);
            } else {

                this.saveAction.setEnabled(true);
            }

            this.statusField.setEnabled(false);
            this.symbolField.setEnabled(false);
        } else {

            this.statusField.setVisible(false);
            this.statusField.setValue(ProcessStatus.DRAFT);
            this.symbolField.setEnabled(true);
        }

    }

    protected ViewLayout initContent() {
        return super.initContent();
    }


    @Subscribe
    public void onAfterSave(final AfterSaveEvent event) {

        if (event.getSource().equals(this)) {
            if (symbolField.getValue() != null
                    && !symbolField.getValue().equals("")
                    && !this.statusField.getValue().equals(ProcessStatus.PROCESSING)) {

                statusField.setValue(ProcessStatus.PROCESSING);
                String symbol = symbolField.getValue();
                stockDataService.processSymbol(symbol);

            }
        }
    }

}