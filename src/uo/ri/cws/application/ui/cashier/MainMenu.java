package uo.ri.cws.application.ui.cashier;


import alb.util.menu.BaseMenu;
import alb.util.menu.NotYetImplementedAction;
import uo.ri.cws.application.ui.cashier.action.FindNotInvoicedWorkOrdersAction;
import uo.ri.cws.application.ui.cashier.action.SettleInvoiceAction;
import uo.ri.cws.application.ui.cashier.action.WorkOrdersBillingAction;

public class MainMenu extends BaseMenu {

	public MainMenu() {
		menuOptions = new Object[][] { 
			{ "Cashier", null },
			
			{ "Payment mean management",			PaymentMeanMenu.class },
			{ "Search not invoiced workorders by client",	FindNotInvoicedWorkOrdersAction.class }, 
			{ "Search not invoiced workorders by plate", 	NotYetImplementedAction.class }, 
			{ "Work order billing", 					WorkOrdersBillingAction.class },
			{ "Settle invoice", 						SettleInvoiceAction.class },
		};
	}

	public static void main(String[] args) {
		new MainMenu().execute();
	}

}
