package uo.ri.cws.application.business.mechanic.crud.commands;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class DeleteMechanic implements Command<Void> {

	private String id;
	MechanicGateway mg = PersistenceFactory.forMechanic();

	public DeleteMechanic(String id) {
		if (id == null || id.isBlank()) {
			throw new IllegalArgumentException("The id cannot be null or empty");
		}
		this.id = id;
	}

	public Void execute() throws BusinessException {
		if (mg.findById(id).isEmpty()) {
			throw new BusinessException("The mechanic does not exist.");
		}

		if (!PersistenceFactory.forWorkOrder().findByMechanicId(id).isEmpty()) {
			throw new BusinessException("The mechanic has work orders assigned");
		}

		mg.remove(id);

		return null;
	}

}
