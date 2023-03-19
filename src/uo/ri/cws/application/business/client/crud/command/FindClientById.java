package uo.ri.cws.application.business.client.crud.command;

import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.ClientDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class FindClientById implements Command<Optional<ClientDto>> {

	private String id;

	public FindClientById(String id) {
		if (id == null || id.isBlank()) {
			throw new IllegalArgumentException("The id cannot be null nor empty");
		}
		this.id = id;
	}

	@Override
	public Optional<ClientDto> execute() throws BusinessException {
		return DtoAssembler.toClientDto(PersistenceFactory.forClient().findById(id));
	}

}
