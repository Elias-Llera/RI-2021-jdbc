package uo.ri.cws.application.persistence.invoice;

import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;

public interface InvoiceGateway extends Gateway<InvoiceRecord>{

	/**
	 * @param invoice's number 
	 * @return invoice dto or null if it does not exist
	 */
	Optional<InvoiceRecord> findByNumber(Long number);
	
	/**
	 * @return next invoice number to assign; that is, one greater than the 
	 * 			greatest number already assigned to an invoice + 1 
	 * 
	 * Notice that, in a real deployment, this way to get a new invoice number 
	 * may produce incorrect values in a concurrent environment because two
	 * concurrent threads could get the same number.
	 * @ 
	 *  
	 */
	Long getNextInvoiceNumber() ;

}