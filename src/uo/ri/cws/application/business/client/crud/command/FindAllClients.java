package uo.ri.cws.application.business.client.crud.command;

import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.ClientDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;

public class FindAllClients implements Command<List<ClientDto>>{

	@Override
	public List<ClientDto> execute() throws BusinessException {
		return DtoAssembler.toClientDtoList(PersistenceFactory.forClient().findAll());
	}

}
