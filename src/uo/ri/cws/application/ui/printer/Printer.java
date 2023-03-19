package uo.ri.cws.application.ui.printer;

import java.util.List;

import alb.util.console.Console;
import uo.ri.cws.application.business.client.ClientDto;
import uo.ri.cws.application.business.invoice.InvoiceDto;
import uo.ri.cws.application.business.invoice.InvoicingWorkOrderDto;
import uo.ri.cws.application.business.invoice.PaymentMeanForInvoicingDto;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.paymentmean.CardDto;
import uo.ri.cws.application.business.paymentmean.PaymentMeanDto;
import uo.ri.cws.application.business.paymentmean.voucher.VoucherDto;
import uo.ri.cws.application.business.paymentmean.voucher.VoucherSummaryDto;

public class Printer {

	public static void printInvoice(InvoiceDto invoice) {

		double toPayWithVAT = invoice.total;
		double vat = invoice.vat;
		double totalWithoutVAT = toPayWithVAT / (1 + vat / 100);

		Console.printf("Invoice nº: %d\n", invoice.number);
		Console.printf("\tDate: %1$td/%1$tm/%1$tY\n", invoice.date);
		Console.printf("\tTotal: %.2f €\n", totalWithoutVAT);
		Console.printf("\tVAT: %.1f %% \n", invoice.vat);
		Console.printf("\tTotal plus VAT: %.2f €\n", invoice.total);
		Console.printf("\tStatus: %s\n", invoice.status);
	}

	public static void printPaymentMeans(List<PaymentMeanDto> medios) {
		Console.println();
		Console.println("Available payment means ");

		Console.printf("\t%-36s \t%-8.8s \t%s \n", "ID", "Type", "Acummulated");
		for (PaymentMeanDto medio : medios) {
			printPaymentMean(medio);
		}
	}

	public static void printPaymentMean(PaymentMeanDto pm) {
		String name = pm.getClass().getName().substring(43);
		String extra = formatExtra(pm);
		Console.printf("\t%-36s \t%-15.15s \t%.2f \t%s\n", pm.id, name, // not the best...
				pm.accumulated, extra);
	}

	private static String formatExtra(PaymentMeanDto mean) {
		if (mean instanceof VoucherDto) {
			return "balance " + ((VoucherDto) mean).balance;
		} else if (mean instanceof CardDto) {
			return "exp " + ((CardDto) mean).cardExpiration;
		}
		return "";
	}

	public static void printMechanic(MechanicDto m) {

		Console.printf("\t%s %-10.10s %-25.25s %-25.25s%n", m.id, m.dni, m.name, m.surname);
	}

	public static void printMechanics(List<MechanicDto> list) {

		Console.printf("\t%-36s %-10.10s %-25.25s %-25.25s%n", "Mechanic identifier", "DNI", "Name", "Surname");
		for (MechanicDto m : list)
			printMechanic(m);
	}

	public static void printVoucher(VoucherDto v) {
		Console.printf("%s \t%s \t%-20.20s \t%.2f \t%.2f\n", v.id, v.code, v.description, v.accumulated, v.balance);
	}

	public static void printVoucherSummary(VoucherSummaryDto vs) {

		Console.printf("%10.10s \t%20.20s \t%20.20s \t%3d \t%.2f \t%.2f \t%.2f\n", vs.dni, vs.name, vs.surname, vs.issued,
				vs.totalAmount, vs.availableBalance, vs.consumed);
	}

	public static void printClient(ClientDto c) {
		Console.printf("%-36s \t%11.11s \t%20.20s \t%20.20s \t%11.11s \t%20.20s \t%s\n", c.id, c.dni, c.name, c.surname, c.phone,
				c.email, c.addressCity);
	}

	public static void printClients(List<ClientDto> clients) {
		Console.printf("%-36s \t%11.11s \t%20.20s \t%20.20s \t%11.11s \t%20.20s \t%s\n", "ID", "DNI", "NAME", "SURNAME", "PHONE",
				"EMAIL", "ADDRESS CITY");
		for (ClientDto m : clients)
			printClient(m);
	}

	public static void printPaymentMeansForInvoicing(List<PaymentMeanForInvoicingDto> means) {
		printPaymentMeanForInvoicingHeader();
		for (PaymentMeanForInvoicingDto p : means) {
			printPaymentMeanForInvoicing(p);
		}

	}

	private static void printPaymentMeanForInvoicingHeader() {
		Console.printf("\t%-36s \t%s \t%-36s \t%s\n", "Paymentmean id", "version", "client id", "accumulated");

	}

	private static void printPaymentMeanForInvoicing(PaymentMeanForInvoicingDto p) {
		Console.printf("\t%s \t%d \t%s \t%.2f\n", p.id, p.version, p.clientId, p.accumulated);

	}

	public static void printInvoicingWorkOrder(InvoicingWorkOrderDto arg) {

		Console.printf("\t%-36s \t%-35s \t%-10s \t%-8s \t%.2f\n", arg.id, arg.description, arg.date, arg.status,
				arg.total);
	}

	public static void printInvoicingWorkOrders(List<InvoicingWorkOrderDto> arg) {
		Console.printf("\t%-36s \t%-35s \t%-10s \t%-8s \t%-8s\n", "Identifier", "description", "date", "status", "total");
		for (InvoicingWorkOrderDto inv : arg)
			printInvoicingWorkOrder(inv);
	}

}
