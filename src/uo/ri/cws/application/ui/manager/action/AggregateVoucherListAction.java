package uo.ri.cws.application.ui.manager.action;

import java.util.List;

import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.paymentmean.voucher.VoucherService;
import uo.ri.cws.application.business.paymentmean.voucher.VoucherSummaryDto;
import uo.ri.cws.application.ui.printer.Printer;

public class AggregateVoucherListAction implements Action {

	@Override
	public void execute() throws Exception {
		VoucherService vs = BusinessFactory.forVoucherService();
		
		List<VoucherSummaryDto> summaries = vs.getVoucherSummary();
	
		for(VoucherSummaryDto s: summaries) {
			Printer.printVoucherSummary( s );
		}
	}

}
