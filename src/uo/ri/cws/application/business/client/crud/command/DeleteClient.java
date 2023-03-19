package uo.ri.cws.application.business.client.crud.command;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientGateway;

public class DeleteClient implements Command<Void> {

	private String id;

	public DeleteClient(String id) {
		if(id == null || id.isBlank()) {
			throw new IllegalArgumentException("El id no puede ser nulo o estar vacio.");
		}
		this.id = id;
	}

	@Override
	public Void execute() throws BusinessException {
		ClientGateway cg = PersistenceFactory.forClient();
		
		if(cg.findById(id).isEmpty()) {
			throw new BusinessException("El cliente no existe");
		}
		
		if(!PersistenceFactory.forVehicle().findByClientId(id).isEmpty()) {
			throw new BusinessException("The client has vehicles");
		}
		
		if(!PersistenceFactory.forPaymentmean().findByClientId(id).isEmpty()) {
			throw new BusinessException("The client has payment means");
		}
		
		cg.remove(id);
		
		return null;
	}

}
