package hk.edu.hkbu.ftproject.view.targettbl;

import hk.edu.hkbu.ftproject.entity.TargetTbl;

import hk.edu.hkbu.ftproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "targetTbls/:id", layout = MainView.class)
@ViewController("ftp_TargetTbl.detail")
@ViewDescriptor("target-tbl-detail-view.xml")
@EditedEntityContainer("targetTblDc")
public class TargetTblDetailView extends StandardDetailView<TargetTbl> {
}