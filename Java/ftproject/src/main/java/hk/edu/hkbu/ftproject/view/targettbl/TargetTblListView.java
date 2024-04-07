package hk.edu.hkbu.ftproject.view.targettbl;

import hk.edu.hkbu.ftproject.entity.TargetTbl;

import hk.edu.hkbu.ftproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "targetTbls", layout = MainView.class)
@ViewController("ftp_TargetTbl.list")
@ViewDescriptor("target-tbl-list-view.xml")
@LookupComponent("targetTblsDataGrid")
@DialogMode(width = "64em")
public class TargetTblListView extends StandardListView<TargetTbl> {
}