package hk.edu.hkbu.ftproject.view.classifiertbl;

import hk.edu.hkbu.ftproject.entity.ClassifierTbl;

import hk.edu.hkbu.ftproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "classifierTbls", layout = MainView.class)
@ViewController("ftp_ClassifierTbl.list")
@ViewDescriptor("classifier-tbl-list-view.xml")
@LookupComponent("classifierTblsDataGrid")
@DialogMode(width = "64em")
public class ClassifierTblListView extends StandardListView<ClassifierTbl> {
}