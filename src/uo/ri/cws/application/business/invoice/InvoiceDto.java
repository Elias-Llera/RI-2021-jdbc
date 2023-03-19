package uo.ri.cws.application.business.invoice;

import java.time.LocalDate;

public class InvoiceDto {

	public String id;		// the surrogate id (UUID)
	public Long version;
	
	public double total;	// total amount (money) vat included
	public double vat;		// amount of vat (money)
	public long number;		// the invoice identity, a sequential number
	public LocalDate date;		// of the invoice
	public String status = InvoiceStatus.NOT_YET_PAID.toString();	// the status PAID, NOT_YET_PAID
	public boolean usedForVoucher;
	
	public enum InvoiceStatus { NOT_YET_PAID, PAID }
}
