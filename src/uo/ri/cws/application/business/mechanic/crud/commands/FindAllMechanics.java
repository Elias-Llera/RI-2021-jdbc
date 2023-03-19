package uo.ri.cws.application.business.mechanic.crud.commands;

import java.util.List;

import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.mechanic.MechanicGateway;

public class FindAllMechanics implements Command<List<MechanicDto>> {

	public List<MechanicDto> execute() {
		MechanicGateway mg = PersistenceFactory.forMechanic();
		return DtoAssembler.toDtoList(mg.findAll());
	}

}
