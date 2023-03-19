package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.UUID;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class AddMechanic implements Command<MechanicDto> {

	private MechanicDto mechanic;
	MechanicGateway mg = PersistenceFactory.forMechanic();

	public AddMechanic(MechanicDto mechanic) {
		if (mechanic == null || mechanic.dni == null || mechanic.dni.isBlank()) {
			throw new IllegalArgumentException("The mechanic cannot be null and the dni cannot be empty or null.");
		}
		this.mechanic = mechanic;
	}

	public MechanicDto execute() throws BusinessException {
		if (mg.findByDni(mechanic.dni).isPresent()) {
			throw new BusinessException("Repetead DNI");
		}
		
		mechanic.id = UUID.randomUUID().toString();

		mg.add(DtoAssembler.toRecord(mechanic));

		return mechanic;
	}

}
