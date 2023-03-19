package uo.ri.cws.application.ui.cashier.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.paymentmean.PaymentMeanDto;
import uo.ri.cws.application.business.paymentmean.PaymentmeanCrudService;
import uo.ri.cws.application.ui.printer.Printer;

public class ListPaymentMeansByClientAction implements Action {

	@Override
	public void execute() throws Exception {
		String id = Console.readString("Client id");
		
		PaymentmeanCrudService service = BusinessFactory.forPaymentMeanCrudService();
		List<PaymentMeanDto> means = service.findPaymentMeansByClientId( id );
		
		Printer.printPaymentMeans(means);
	}

}
