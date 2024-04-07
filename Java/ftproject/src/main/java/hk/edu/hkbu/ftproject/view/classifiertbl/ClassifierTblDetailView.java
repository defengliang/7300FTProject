package hk.edu.hkbu.ftproject.view.classifiertbl;

import hk.edu.hkbu.ftproject.entity.ClassifierTbl;

import hk.edu.hkbu.ftproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "classifierTbls/:id", layout = MainView.class)
@ViewController("ftp_ClassifierTbl.detail")
@ViewDescriptor("classifier-tbl-detail-view.xml")
@EditedEntityContainer("classifierTblDc")
public class ClassifierTblDetailView extends StandardDetailView<ClassifierTbl> {
}