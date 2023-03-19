package uo.ri.cws.application.ui.cashier.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.paymentmean.PaymentmeanCrudService;

public class DeletePaymentMeanAction implements Action {

	@Override
	public void execute() throws Exception {
		String id = Console.readString("Paymentmean id");
		
		PaymentmeanCrudService service = BusinessFactory.forPaymentMeanCrudService();
		
		service.deletePaymentMean( id );
		Console.println("The paymentmean has been correctly deleted.");
	}

}
