package uo.ri.cws.application.ui.foreman;

import alb.util.menu.BaseMenu;
import uo.ri.cws.application.ui.foreman.action.AddClientAction;
import uo.ri.cws.application.ui.foreman.action.DeleteClientAction;
import uo.ri.cws.application.ui.foreman.action.FindAllClientsAction;
import uo.ri.cws.application.ui.foreman.action.ListRecommendedClientsAction;
import uo.ri.cws.application.ui.foreman.action.UpdateClientAction;

public class ClientMenu extends BaseMenu {

	public ClientMenu() {
		menuOptions = new Object[][] { 
			{ "Foreman > Client management", null },

			{ "Add client", AddClientAction.class }, 
			{ "Update client", UpdateClientAction.class }, 
			{ "Delete client", DeleteClientAction.class }, 
			{ "List clients", FindAllClientsAction.class }, 
			{ "List sponsors", ListRecommendedClientsAction.class }, 

		};
	}

}
