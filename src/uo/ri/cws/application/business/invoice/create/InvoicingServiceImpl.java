package uo.ri.cws.application.business.invoice.create;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.ChargeDto;
import uo.ri.cws.application.business.invoice.InvoiceDto;
import uo.ri.cws.application.business.invoice.InvoicingService;
import uo.ri.cws.application.business.invoice.InvoicingWorkOrderDto;
import uo.ri.cws.application.business.invoice.PaymentMeanForInvoicingDto;
import uo.ri.cws.application.business.invoice.create.commands.CreateInvoice;
import uo.ri.cws.application.business.invoice.create.commands.FindNotInvoicedWorkOrders;
import uo.ri.cws.application.business.util.command.CommandExecutor;

public class InvoicingServiceImpl implements InvoicingService{

	CommandExecutor executor = new CommandExecutor();
	
	@Override
	public InvoiceDto createInvoiceFor(List<String> workOrderIds) throws BusinessException {
		return executor.execute(new CreateInvoice(workOrderIds));
	}

	@Override
	public List<InvoicingWorkOrderDto> findWorkOrdersByClientDni(String dni) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<InvoicingWorkOrderDto> findNotInvoicedWorkOrdersByClientDni(String dni) throws BusinessException {
		return executor.execute(new FindNotInvoicedWorkOrders(dni));
	}

	@Override
	public List<InvoicingWorkOrderDto> findWorkOrdersByPlateNumber(String plate) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<InvoiceDto> findInvoiceByNumber(Long number) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentMeanForInvoicingDto> findPayMeansByClientDni(String dni) throws BusinessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void settleInvoice(String invoiceId, List<ChargeDto> charges) throws BusinessException {
		// TODO Auto-generated method stub
		
	}

}
