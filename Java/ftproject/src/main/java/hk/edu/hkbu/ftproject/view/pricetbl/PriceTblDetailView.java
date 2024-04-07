package hk.edu.hkbu.ftproject.view.pricetbl;

import hk.edu.hkbu.ftproject.entity.PriceTbl;
import hk.edu.hkbu.ftproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "priceTbls/:id", layout = MainView.class)
@ViewController("ftp_PriceTbl.detail")
@ViewDescriptor("price-tbl-detail-view.xml")
@EditedEntityContainer("priceTblDc")
public class PriceTblDetailView extends StandardDetailView<PriceTbl> {
}