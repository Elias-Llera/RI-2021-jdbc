package uo.ri.cws.application.business.paymentmean.voucher.create.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.client.ClientRecord;
import uo.ri.cws.application.persistence.invoice.InvoiceGateway;
import uo.ri.cws.application.persistence.invoice.InvoiceRecord;
import uo.ri.cws.application.persistence.paymentmean.PaymentMeanGateway;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.vehicle.VehicleRecord;
import uo.ri.cws.application.persistence.voucher.VoucherGateway;
import uo.ri.cws.application.persistence.voucher.VoucherRecord;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderRecord;

public class GenerateVouchers implements Command<Integer> {

	ClientGateway clientg = PersistenceFactory.forClient();
	VehicleGateway vehicleg = PersistenceFactory.forVehicle();
	WorkOrderGateway workorderg = PersistenceFactory.forWorkOrder();
	InvoiceGateway invoiceg = PersistenceFactory.forInvoice();
	VoucherGateway voucherg = PersistenceFactory.forVoucher();
	PaymentMeanGateway paymentmeang = PersistenceFactory.forPaymentmean();

	@Override
	public Integer execute() throws BusinessException {

		List<WorkOrderRecord> usedForVoucher = new ArrayList<>();
		int vouchers = 0;
		WorkOrderRecord first = null, second = null;
		String currentClient = null;

		for (ClientRecord client : clientg.findAll()) {
			for (VehicleRecord vehicle : vehicleg.findByClientId(client.id)) {
				for (WorkOrderRecord workOrder : workorderg.findForVoucherByVehicleId(vehicle.id)) {
					Optional<InvoiceRecord> invoice = invoiceg.findById(workOrder.invoiceId);
					if (invoice.isPresent() && invoice.get().status.equals("PAID")) {
						if (currentClient == null || !currentClient.equals(client.id)) {
							currentClient = client.id;
							first = workOrder;
							second = null;
						} else {
							if (first == null) {
								first = workOrder;
							} else if (second == null) {
								second = workOrder;
							} else {
								usedForVoucher.add(first);
								usedForVoucher.add(second);
								usedForVoucher.add(workOrder);
								first = null;
								second = null;
								VoucherRecord voucher = generateVoucher(currentClient);
								vouchers++;
								paymentmeang.add(voucher);
								voucherg.add(voucher);
							}
						}
					}
				}
			}
		}
		for (WorkOrderRecord workOrder : usedForVoucher) {
			workorderg.markAsUsedForVoucher(workOrder.id);
		}
		return vouchers;
	}

	private VoucherRecord generateVoucher(String clientId) {
		VoucherRecord voucherRecord = new VoucherRecord();
		voucherRecord.id = UUID.randomUUID().toString();
		voucherRecord.code = UUID.randomUUID().toString();
		voucherRecord.description = "By three workorders";
		voucherRecord.available = 20.00;
		voucherRecord.client_id = clientId;
		voucherRecord.accumulated = 0;
		voucherRecord.dtype = "VOUCHER";
		return voucherRecord;
	}

}
