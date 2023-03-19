package uo.ri.cws.application.persistence.workorder;

import java.util.List;

import uo.ri.cws.application.persistence.Gateway;

public interface WorkOrderGateway extends Gateway<WorkOrderRecord>{
	
	List<WorkOrderRecord> findByMechanicId(String mechanicId);
	
	void markAsInvoiced(String id);
	
	void linkToInvoice(String invoiceId, String id);
	
	List<WorkOrderRecord> findNotInvoicedForClientDni(String dni);

	List<WorkOrderRecord> findNotInvoicedForVehicleId(String id);
	
	List<WorkOrderRecord> findForVoucherByVehicleId(String id);

	void markAsUsedForVoucher(String id);

}
