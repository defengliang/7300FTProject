package hk.edu.hkbu.ftproject.view.evaltbl;

import hk.edu.hkbu.ftproject.entity.EvalTbl;

import hk.edu.hkbu.ftproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "evalTbls", layout = MainView.class)
@ViewController("ftp_EvalTbl.list")
@ViewDescriptor("eval-tbl-list-view.xml")
@LookupComponent("evalTblsDataGrid")
@DialogMode(width = "64em")
public class EvalTblListView extends StandardListView<EvalTbl> {
}