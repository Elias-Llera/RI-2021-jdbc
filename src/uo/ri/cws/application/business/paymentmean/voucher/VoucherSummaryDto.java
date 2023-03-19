package uo.ri.cws.application.business.paymentmean.voucher;

/**
 * An aggregated result of all vouchers of a client
 */
public class VoucherSummaryDto {
	public String id;
	public Long version;
	public String dni;			// of the client 
	public String name;			// of the client 
	public String surname;		// of the client 
	public int issued;			// how many vouchers has been issued
	public double totalAmount;	// the total amount "voucherized" (money)
	public double availableBalance;	// how much remains available for the client
	public double consumed;		// how much has been 
	
}
