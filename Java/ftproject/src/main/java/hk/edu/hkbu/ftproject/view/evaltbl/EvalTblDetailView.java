package hk.edu.hkbu.ftproject.view.evaltbl;

import hk.edu.hkbu.ftproject.entity.EvalTbl;

import hk.edu.hkbu.ftproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "evalTbls/:id", layout = MainView.class)
@ViewController("ftp_EvalTbl.detail")
@ViewDescriptor("eval-tbl-detail-view.xml")
@EditedEntityContainer("evalTblDc")
public class EvalTblDetailView extends StandardDetailView<EvalTbl> {
}