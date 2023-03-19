package uo.ri.cws.application.business.paymentmean.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.paymentmean.CardDto;
import uo.ri.cws.application.business.paymentmean.PaymentMeanDto;
import uo.ri.cws.application.business.paymentmean.PaymentmeanCrudService;
import uo.ri.cws.application.business.paymentmean.voucher.VoucherDto;

public class PaymentmeanCrudServiceImpl implements PaymentmeanCrudService{

	@Override
	public void addCardPaymentMean(CardDto card) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addVoucherPaymentMean(VoucherDto voucher) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePaymentMean(String id) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<PaymentMeanDto> findById(String id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentMeanDto> findPaymentMeansByClientId(String id) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

}
