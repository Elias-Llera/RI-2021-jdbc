package uo.ri.cws.application.ui.cashier;

import alb.util.menu.BaseMenu;
import uo.ri.cws.application.ui.cashier.action.AddPaymentMeanAction;
import uo.ri.cws.application.ui.cashier.action.DeletePaymentMeanAction;
import uo.ri.cws.application.ui.cashier.action.ListPaymentMeansByClientAction;
import uo.ri.cws.application.ui.cashier.action.FindPaymentMeansByIdAction;

public class PaymentMeanMenu extends BaseMenu {

	public PaymentMeanMenu() {
		menuOptions = new Object[][] { 
			{ "Cashier > Payment mean management", null },

			{ "Add payment mean", 				AddPaymentMeanAction.class }, 
			{ "Delete payment mean", 			DeletePaymentMeanAction.class }, 
			{ "Find payment mean by client", 	ListPaymentMeansByClientAction.class },
			{ "Find payment mean by id", 	FindPaymentMeansByIdAction.class },
		};
	}

}
