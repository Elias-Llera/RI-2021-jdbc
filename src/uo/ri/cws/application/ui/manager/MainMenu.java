package uo.ri.cws.application.ui.manager;

import alb.util.menu.BaseMenu;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Administrator", null },
			{ "Mechanics management", 			MechanicMenu.class }, 
			{ "Spare parts management", 			SparePartMenu.class },
			{ "Vehicle types management", 	VehicleTypeMenu.class },
			{ "Vouchers management", 				VoucherMenu.class },
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}
}