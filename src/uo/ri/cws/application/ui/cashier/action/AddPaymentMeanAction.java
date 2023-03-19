package uo.ri.cws.application.ui.cashier.action;

import java.time.LocalDate;

import alb.util.assertion.Assert;
import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.paymentmean.CardDto;
import uo.ri.cws.application.business.paymentmean.PaymentmeanCrudService;
import uo.ri.cws.application.business.paymentmean.voucher.VoucherDto;

public class AddPaymentMeanAction implements Action {

	@Override
	public void execute() throws Exception {
		PaymentmeanCrudService service = BusinessFactory.forPaymentMeanCrudService();
		
		String clientId = Console.readString("Client Id");
		int type = Console.readInt("Paymentmean (1 - Credit Card, 2 - Voucher)");
		if (type == 1) {
			CardDto card = askForCard( clientId );
			service.addCardPaymentMean(card);
		}
		else if ( type == 2 ) {
			VoucherDto voucher = askForVoucher( clientId );
			service.addVoucherPaymentMean(voucher);
		}
		else {
			Assert.fail("Invalid paymentmean.");
		}
		
		Console.println("Paymentmean correctly added.");		
	}

	private VoucherDto askForVoucher(String clientId) {
		VoucherDto res = new VoucherDto();
		res.clientId = clientId;
		res.accumulated = 0.0;
		res.description = Console.readString("Voucher description");
		res.balance = Console.readDouble("Voucher value");
		res.code = Console.readString("Voucher code");
		res.version = 1L;

		return res;
	}

	private CardDto askForCard(String clientId) {
		CardDto res = new CardDto();
		res.clientId = clientId;
		res.cardType = Console.readString("Credit card type");
		res.cardNumber = Console.readString("Credit card number");
		int month = Console.readInt("Expiration date (month)");
		int year = Console.readInt("Expiration date (year)");
		res.cardExpiration = LocalDate.of(year, month, 1);
		res.version = 1L;

		return res;
	}

}