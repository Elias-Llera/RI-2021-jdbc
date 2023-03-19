package uo.ri.cws.application.business.invoice.create.commands;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import alb.util.math.Round;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoiceDto;
import uo.ri.cws.application.business.invoice.InvoiceDto.InvoiceStatus;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderRecord;

public class CreateInvoice implements Command<InvoiceDto> {

	private List<String> workOrderIds;

	InvoiceGateway ig = PersistenceFactory.forInvoice();
	WorkOrderGateway wg = PersistenceFactory.forWorkOrder();

	public CreateInvoice(List<String> workOrderIds) {
		if(workOrderIds == null || workOrderIds.size() == 0) {
			throw new IllegalArgumentException("The work orders list cannot be null nor empty.");
		}
		for (String id : workOrderIds)
			if (id == null || id.isBlank()) {
				throw new IllegalArgumentException("Los ids no pueden ser nulos o vacios");
			}
		this.workOrderIds = workOrderIds;
	}

	public InvoiceDto execute() throws BusinessException {
		validate();
		
		InvoiceDto invoice = calculateInvoice();
		ig.add(DtoAssembler.toRecord(invoice));
		linkWorkOrdersToInvoice(invoice.id);
		markWorkOrdersAsInvoiced();

		return invoice;
	}

	private InvoiceDto calculateInvoice() {
		InvoiceDto invoice = new InvoiceDto();

		invoice.id = UUID.randomUUID().toString();
		invoice.number = calulateNextInvoiceNumber();
		invoice.date = LocalDate.now();
		invoice.vat = calculateVat(invoice.date);
		invoice.total = calculateInvoiceTotal(invoice.vat);
		invoice.status = InvoiceStatus.NOT_YET_PAID.toString();

		return invoice;
	}
	
	private double calculateInvoiceTotal(double vat) {
		double total = calculateInvoiceAmount() * (1 + vat / 100);
		total = Round.twoCents(total);
		return total;
	}
	
	private double calculateInvoiceAmount() {
		double totalInvoice = 0.0;
		for (String id : workOrderIds) {
			totalInvoice += wg.findById(id).get().amount;
		}
		return totalInvoice;
	}

	private double calculateVat(LocalDate invoiceDate) {
		return LocalDate.parse("2012-07-01").isBefore(invoiceDate) ? 21.0 : 18.0;
	}
	
	private long calulateNextInvoiceNumber() {
		return ig.getNextInvoiceNumber();
	}


	private void linkWorkOrdersToInvoice(String invoiceId) {
		for(String id : workOrderIds) {
			wg.linkToInvoice(invoiceId, id);
		}
	}
	
	private void markWorkOrdersAsInvoiced() {
		for (String id : workOrderIds) {
			wg.markAsInvoiced(id);
		}
	}

	private void validate() throws BusinessException {
		for (String id : workOrderIds) {
			Optional<WorkOrderRecord> workOrder = wg.findById(id);
			if (workOrder.isPresent()) {
				if (!workOrder.get().status.equals("FINISHED")) {
					throw new BusinessException("Todas las workOrders deben tener el estado 'FINISHED'");
				}
			} else {
				throw new BusinessException("Todos los ids deben existir");
			}
		}
	}

}
