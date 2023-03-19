package uo.ri.cws.application.business.client.crud.command;

import java.util.UUID;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.ClientDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.recommendation.RecommendationRecord;

public class AddClient implements Command<ClientDto> {

	private ClientDto client;
	private String recommenderId;

	public AddClient(ClientDto client, String recommenderId) {
		if( client == null
			|| client.dni == null || client.dni.isBlank()
			|| client.name == null || client.name.isBlank()
			|| client.surname == null || client.surname.isBlank()
			|| client.email == null || client.email.isBlank()
			|| client.phone == null || client.phone.isBlank()
			|| client.addressCity == null || client.addressCity.isBlank()
			|| client.addressStreet == null || client.addressStreet.isBlank()
			|| client.addressZipcode == null ||  client.addressZipcode.isBlank()) {
			throw new IllegalArgumentException("All values of the client must be provided");
		}
		this.recommenderId = recommenderId;
		this.client = client;
	}

	@Override
	public ClientDto execute() throws BusinessException {
		ClientGateway cg = PersistenceFactory.forClient();
		if(cg.findByDni(client.dni).isPresent()) {
			throw new BusinessException("Ya existe un cliente con ese DNI");
		}
		if(recommenderId != null && cg.findById(recommenderId).isEmpty()) {
			throw new BusinessException("The recommender does not exist.");
		}
		
		client.id = UUID.randomUUID().toString();
		cg.add(DtoAssembler.toRecord(client));
		
		RecommendationRecord recommendation = new RecommendationRecord();
		recommendation.id = UUID.randomUUID().toString();
		recommendation.sponsor_id = recommenderId;
		recommendation.recommended_id = client.id;
		recommendation.usedForVoucher = false;
		PersistenceFactory.forRecommendation().add(recommendation);
		
		return client;
	}

}
