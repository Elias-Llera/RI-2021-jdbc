package uo.ri.cws.application.business.invoice;

import java.time.LocalDate;

public class CardForInvoicingDto extends PaymentMeanForInvoicingDto {
	public String cardNumber;
	public LocalDate cardExpiration;
	public String cardType;

}
