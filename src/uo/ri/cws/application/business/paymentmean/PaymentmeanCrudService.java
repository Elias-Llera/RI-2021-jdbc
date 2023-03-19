package uo.ri.cws.application.business.paymentmean;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.paymentmean.voucher.VoucherDto;

public interface PaymentmeanCrudService {
    
    
    /**
     * Registers a new Card in the system with the information received
     * 
     * @param card
     * @throws BusinessException in case of:
     * 		- Client does not exist.
     * 		- There is another card registered with the same card number
     *		- The card to be inserted is outdated (expiration date before today)
     * @throws IllegalArgumentException if 
     * 		- The dto is null.
     * 		- Any field of the following fields is null: id, cardExpiration, cardNumber, cardType, clientId
     * 		- Any field of the following fields is empty: id, cardNumber, cardType, clientId
     * 
     */
    void addCardPaymentMean(CardDto card) throws BusinessException;
 
    /**
     * Registers a new Voucher in the system with the information received
     * 
     * @param voucher
     * @throws BusinessException in case of:
     * 		- Client does not exist. 
     * 		- There is another voucher registered with the same code
     * @throws IllegalArgumentException if 
     * 		- The dto is null.
     * 		- Any field of the following fields is null: code, description or client id
     * 		- Any field of the following fields is empty: code, description or client id
     * 		- Field balance is negative
     */
    void addVoucherPaymentMean(VoucherDto voucher) throws BusinessException;
    
    /**
     * Removes the payment mean from the system 
     * 
     * @param id
     * @throws BusinessException in case of:
     * 		- There is no payment mean with such id.
     * 		- Cash can not be removed.
     * 		- The payment mean has charges.
     * @throws IllegalArgumentException if id is null or blank
     */
    void deletePaymentMean(String id) throws BusinessException;
    
//    /**
//     * Find credit card registered by id
//     * 
//     * @return A credit card 
//     * @throws BusinessException DOES NOT
//     * @throws IllegalArgumentException if the argument is null or blank
//     */
//    Optional<CardDto> findCreditCardById( String id ) throws BusinessException;

    /**
     * Find a payment mean registered by id
     * 
     * @return A payment mean
     * @throws BusinessException DOES NOT
     * @throws IllegalArgumentException if the argument is null or blank
     */
    Optional<PaymentMeanDto> findById( String id ) throws BusinessException;
    
    /**
     * Returns all the payment means for the client identified by its id
     * 
     * @param id
     * @return A list with payment means or empty if there is no payment mean for the client
     * @throws BusinessException DOES NOT
     * @throws IllegalArgumentException if the argument is null or blank
     */
    List<PaymentMeanDto> findPaymentMeansByClientId(String id) throws BusinessException;
}
