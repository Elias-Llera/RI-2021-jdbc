package uo.ri.cws.application.ui.foreman;

import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;

public class VehicleMenu extends BaseMenu {

	public VehicleMenu() {
		menuOptions = new Object[][] { 
			{ "Foreman > Vehicles management ", null },

			{ "Add vehicle", NotYetImplementedAction.class }, 
			{ "Update vehicle", NotYetImplementedAction.class }, 
			{ "Delete vehicle", NotYetImplementedAction.class }, 
			{ "List vehicles", NotYetImplementedAction.class }, 
		};
	}

}
