package hk.edu.hkbu.ftproject.view.companyfile;

import hk.edu.hkbu.ftproject.entity.CompanyFile;

import hk.edu.hkbu.ftproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "companyFiles", layout = MainView.class)
@ViewController("ftp_CompanyFile.list")
@ViewDescriptor("company-file-list-view.xml")
@LookupComponent("companyFilesDataGrid")
@DialogMode(width = "64em")
public class CompanyFileListView extends StandardListView<CompanyFile> {
}