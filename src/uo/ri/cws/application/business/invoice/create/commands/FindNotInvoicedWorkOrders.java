package uo.ri.cws.application.business.invoice.create.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.invoice.InvoicingWorkOrderDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.client.ClientRecord;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.vehicle.VehicleRecord;
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;

public class FindNotInvoicedWorkOrders implements Command<List<InvoicingWorkOrderDto>> {

	private String dni;

	public FindNotInvoicedWorkOrders(String dni) {
		if (dni == null || dni.isBlank()) {
			throw new IllegalArgumentException("El DNI no puede ser nulo o estar vacio.");
		}
		this.dni = dni;
	}

//	@Override
//	public List<InvoicingWorkOrderDto> execute() throws BusinessException {
//		if (PersistenceFactory.forClient().findByDni(dni).isPresent()) {
//			return DtoAssembler
//					.toInvoicingWorkOrderList(PersistenceFactory.forWorkOrder().findNotInvoicedForClientDni(dni));
//		} else {
//			throw new BusinessException("The client does not exist.");
//		}
//	}

	@Override
	public List<InvoicingWorkOrderDto> execute() throws BusinessException {
		List<InvoicingWorkOrderDto> res = new ArrayList<>();

		ClientGateway cg = PersistenceFactory.forClient();
		WorkOrderGateway wg = PersistenceFactory.forWorkOrder();
		VehicleGateway vg = PersistenceFactory.forVehicle();

		Optional<ClientRecord> client = cg.findByDni(dni);

		if (client.isEmpty()) {
			throw new BusinessException("El cliente no existe");
		}

		List<VehicleRecord> vehiclesList = vg.findByClientId(client.get().id);

		for (VehicleRecord vehicleRecord : vehiclesList) {
			res.addAll(DtoAssembler.toInvoicingWorkOrderList(wg.findNotInvoicedForVehicleId(vehicleRecord.id)));
		}

		return res;
	}
}
