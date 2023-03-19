package uo.ri.cws.application.business.client.crud.command;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.ClientDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientGateway;

public class UpdateClient implements Command<Void>{
	
	private ClientDto client;
	
	public UpdateClient(ClientDto client) {
		if( client == null
				|| client.id == null || client.id.isBlank()
				|| client.name == null || client.name.isBlank()
				|| client.surname == null || client.surname.isBlank()
				|| client.email == null || client.email.isBlank()
				|| client.phone == null || client.phone.isBlank()
				|| client.addressCity == null || client.addressCity.isBlank()
				|| client.addressStreet == null || client.addressStreet.isBlank()
				|| client.addressZipcode == null ||  client.addressZipcode.isBlank()) {
				throw new IllegalArgumentException("All values of the client must be provided");
			}
			this.client = client;
	}

	@Override
	public Void execute() throws BusinessException {
		ClientGateway cg = PersistenceFactory.forClient();
		if(cg.findById(client.id).isEmpty()) {
			throw new BusinessException("No existe un cliente con ese ID");
		}
		cg.update(DtoAssembler.toRecord(client));
		return null;
	}

}
