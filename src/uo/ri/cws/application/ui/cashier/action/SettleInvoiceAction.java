package uo.ri.cws.application.ui.cashier.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.invoice.ChargeDto;
import uo.ri.cws.application.business.invoice.InvoiceDto;
import uo.ri.cws.application.business.invoice.InvoicingService;
import uo.ri.cws.application.business.invoice.PaymentMeanForInvoicingDto;
import uo.ri.cws.application.ui.printer.Printer;

public class SettleInvoiceAction implements Action {

    private InvoicingService cs = BusinessFactory.forInvoicingService();

    @Override
    public void execute() throws Exception {
	Long number = Console.readLong("Invoice number");
	Optional<InvoiceDto> oi = cs.findInvoiceByNumber(number);
	if (oi.isEmpty()) {
	    Console.println("Invoice does not exist");

	}else {
	    InvoiceDto invoice = oi.get();

	    Printer.printInvoice( invoice );

	    String dni = Console.readString("Client dni");
	    List<PaymentMeanForInvoicingDto> means = cs.findPayMeansByClientDni(dni);

	    List<ChargeDto> charges = askForCharges(means, invoice.total);

	    cs.settleInvoice(invoice.id, charges);

	    Console.println("The invoice has been settled");
	}
    }

    private List<ChargeDto> askForCharges(List<PaymentMeanForInvoicingDto> means,
	    double totalAmount) {
	List<ChargeDto> res = new ArrayList<>();
	Double sumAmounts = 0.0;
	do {
	    showPaymentMeans(means);
	    String id = askForPaymentMeanId();
	    Double amount = askForAmount();
	    ChargeDto charge = new ChargeDto();
	    charge.amount = amount;
	    charge.paymentMean_id = id;
	    res.add(charge);
	    sumAmounts += amount;

	} while ( sumAmounts < totalAmount );

	return res;
    }

    private Double askForAmount() {
	return Console.readDouble("Amount to charge");
    }

    private String askForPaymentMeanId() {
	return Console.readString("Payment mean id");
    }

    private void showPaymentMeans(List<PaymentMeanForInvoicingDto> means) {
	Printer.printPaymentMeansForInvoicing( means );
    }

}
