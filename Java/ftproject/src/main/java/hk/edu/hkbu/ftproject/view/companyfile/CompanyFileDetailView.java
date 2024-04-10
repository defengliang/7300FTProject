package hk.edu.hkbu.ftproject.view.companyfile;

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
import io.jmix.flowui.kit.component.upload.event.FileUploadSucceededEvent;
import io.jmix.flowui.upload.TemporaryStorage;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.UUID;

@Route(value = "companyFiles/:id", layout = MainView.class)
@ViewController("ftp_CompanyFile.detail")
@ViewDescriptor("company-file-detail-view.xml")
@EditedEntityContainer("companyFileDc")
public class CompanyFileDetailView extends StandardDetailView<CompanyFile> {
    @ViewComponent
    private FileStorageUploadField fileField;

    @Autowired
    private TemporaryStorage temporaryStorage;
    @ViewComponent
    private TypedTextField<String> filePathField;
    @ViewComponent
    private TypedTextField<String> fileNameField;
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
    @Autowired
    private Notifications notifications;

    protected void setupEntityToEdit() {
        super.setupEntityToEdit();
    }

    @Subscribe
    public void onBeforeShow(final BeforeShowEvent event) {

        this.fileNameField.setVisible(false);
        this.filePathField.setVisible(false);

        if (this.getEditedEntity().getSymbol() != null) {
            if (this.statusField != null && ProcessStatus.PROCESSING.equals(this.statusField.getValue())) {
                this.fileField.setEnabled(false);
                this.saveAction.setEnabled(false);
            } else {
                this.fileField.setEnabled(true);
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

    @Subscribe("fileField")
    public void onFileFieldFileUploadSucceeded(final FileUploadSucceededEvent<FileStorageUploadField> event) {

        if (symbolField.getValue() != null
                && !symbolField.getValue().equals("")
                && event.getReceiver() instanceof FileTemporaryStorageBuffer buffer) {
            File file = buffer.getFileData().getFileInfo().getFile();
            fileField.setValue(null);
            filePathField.setValue("filePath");
            fileNameField.setValue("fileName");

            if (file != null) {

                statusField.setValue(ProcessStatus.PROCESSING);
                BackgroundTaskHandler taskHandler = backgroundWorker.handle(new BackgroundTask<Integer, Void>(7200) {
                    @Override
                    public Void run(TaskLifeCycle<Integer> taskLifeCycle) {
                        stockDataService.proecess(symbolField.getValue(), file);
                        return null;
                    }
                });
                taskHandler.execute();
            }
        }
    }


}