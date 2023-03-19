package uo.ri.cws.application.business.mechanic.crud.commands;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class UpdateMechanic implements Command<Void> {

	private MechanicDto mechanic;
	private MechanicGateway mg = PersistenceFactory.forMechanic();

	public UpdateMechanic(MechanicDto mechanic) {
		if (mechanic == null) {
			throw new IllegalArgumentException("The mechanic cannot be null");
		}

		if (mechanic.dni == null || mechanic.dni.isBlank() || mechanic.name == null || mechanic.name.isBlank()
				|| mechanic.surname == null || mechanic.surname.isBlank()) {
			throw new IllegalArgumentException("None of the mechanic fields can be null nor empty.");
		}
		this.mechanic = mechanic;
	}

	public Void execute() throws BusinessException {
		if (mg.findById(mechanic.id).isEmpty()) {
			throw new BusinessException("The mechanic does not exist.");
		}
		PersistenceFactory.forMechanic().update(DtoAssembler.toRecord(mechanic));
		return null;
	}
}
