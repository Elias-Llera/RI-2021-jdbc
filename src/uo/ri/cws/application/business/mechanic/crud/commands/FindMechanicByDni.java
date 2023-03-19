package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.Optional;

import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class FindMechanicByDni implements Command<Optional<MechanicDto>> {

	private String dniMechanic;

	public FindMechanicByDni(String dniMechanic) {
		if (dniMechanic == null || dniMechanic.isBlank()) {
			throw new IllegalArgumentException("The dni cannot be null nor empty.");
		}
		this.dniMechanic = dniMechanic;
	}

	public Optional<MechanicDto> execute() {
		MechanicGateway mg = PersistenceFactory.forMechanic();
		return DtoAssembler.toDto(mg.findByDni(dniMechanic));
	}

}
