package uo.ri.cws.application.ui.cashier.action;

import java.util.Optional;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.paymentmean.PaymentMeanDto;
import uo.ri.cws.application.business.paymentmean.PaymentmeanCrudService;
//import uo.ri.cws.application.business.util.BusinessCheck;
import uo.ri.cws.application.ui.printer.Printer;

public class FindPaymentMeansByIdAction implements Action {

	@Override
	public void execute() throws Exception {
		String id = Console.readString("Payment mean id");
		
		PaymentmeanCrudService service = BusinessFactory.forPaymentMeanCrudService();
		Optional<PaymentMeanDto> opm = service.findById( id );
		if (opm.isPresent()) {
		    PaymentMeanDto pm = opm.get();
		    Printer.printPaymentMean(pm);
		}
	}

}

