package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.Optional;

import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class FindMechanicById implements Command<Optional<MechanicDto>>{

	private String idMechanic;

	public FindMechanicById(String idMechanic) {
		if (idMechanic == null || idMechanic.isBlank()) {
			throw new IllegalArgumentException("The id cannot be null nor empty.");
		}
		this.idMechanic = idMechanic;
	}

	public Optional<MechanicDto> execute() {
		return DtoAssembler.toDto(PersistenceFactory.forMechanic().findById(idMechanic));
	}

}
