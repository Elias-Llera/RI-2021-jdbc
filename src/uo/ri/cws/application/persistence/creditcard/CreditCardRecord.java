package uo.ri.cws.application.persistence.creditcard;

import java.time.LocalDate;

import uo.ri.cws.application.persistence.paymentmean.PaymentmeanRecord;

public class CreditCardRecord extends PaymentmeanRecord{

	public String id;
	public String number;
	public String type;
	public LocalDate validthru;

}
