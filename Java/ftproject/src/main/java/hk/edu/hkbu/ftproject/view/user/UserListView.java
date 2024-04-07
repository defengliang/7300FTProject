package hk.edu.hkbu.ftproject.view.user;

import hk.edu.hkbu.ftproject.entity.User;
import hk.edu.hkbu.ftproject.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.*;

@Route(value = "users", layout = MainView.class)
@ViewController("ftp_User.list")
@ViewDescriptor("user-list-view.xml")
@LookupComponent("usersDataGrid")
@DialogMode(width = "64em")
public class UserListView extends StandardListView<User> {
}