package uo.ri.cws.application.ui.foreman;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.cws.application.ui.foreman.action.AddWorkOrderAction;
import uo.ri.cws.application.ui.foreman.action.UpdateWorkOrderAction;

public class ClientReceptionMenu extends BaseMenu {

	public ClientReceptionMenu() {
		menuOptions = new Object[][] { 
			{"Foreman > Check in menu", null},
			
			{"Add workorder", 			AddWorkOrderAction.class }, 
			{"Update workorder", 		UpdateWorkOrderAction.class },
			{"Delete workorder", 		NotYetImplementedAction.class },
			{"", null},
			{"List workorder", 			NotYetImplementedAction.class }, 
			{"Check a workorder", 		NotYetImplementedAction.class },
			{"", null},
			{"List mechanics", 			NotYetImplementedAction.class }, 
			{"Assign a workorder",  	NotYetImplementedAction.class },
		};
	}

}
