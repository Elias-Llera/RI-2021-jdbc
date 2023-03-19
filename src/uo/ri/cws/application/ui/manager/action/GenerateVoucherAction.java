package uo.ri.cws.application.ui.manager.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.paymentmean.voucher.VoucherService;

public class GenerateVoucherAction implements Action {

	@Override
	public void execute() throws Exception {
		
		VoucherService vs = BusinessFactory.forVoucherService();
		
		int qty = vs.generateVouchers();
		
		Console.printf("%d vouchers has been generated", qty);
	}
	

}
