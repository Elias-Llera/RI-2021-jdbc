package uo.ri.cws.application.ui.manager;

import alb.util.menu.BaseMenu;
import uo.ri.cws.application.ui.manager.action.AggregateVoucherListAction;
import uo.ri.cws.application.ui.manager.action.ClientVoucherListAction;
import uo.ri.cws.application.ui.manager.action.GenerateVoucherAction;

public class VoucherMenu extends BaseMenu {

	public VoucherMenu() {
		menuOptions = new Object[][] { 
			{"Manager > Voucher management", null},
			
			{ "Voucher generation", 				GenerateVoucherAction.class }, 
			{ "List vouchers by client", 	ClientVoucherListAction.class }, 
			{ "Vouchers summary",	AggregateVoucherListAction.class }, 
		};
	}

}
