package hk.edu.hkbu.ftproject.view.pricetbl;

import hk.edu.hkbu.ftproject.entity.PriceTbl;
import hk.edu.hkbu.ftproject.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "priceTbls", layout = MainView.class)
@ViewController("ftp_PriceTbl.list")
@ViewDescriptor("price-tbl-list-view.xml")
@LookupComponent("priceTblsDataGrid")
@DialogMode(width = "64em")
public class PriceTblListView extends StandardListView<PriceTbl> {
}