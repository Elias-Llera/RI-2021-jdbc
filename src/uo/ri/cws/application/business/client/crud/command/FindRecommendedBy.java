package uo.ri.cws.application.business.client.crud.command;

import java.util.ArrayList;
import java.util.List;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.client.ClientDto;
import uo.ri.cws.application.business.util.DtoAssembler;
import uo.ri.cws.application.business.util.command.Command;
import uo.ri.cws.application.persistence.PersistenceFactory;
import uo.ri.cws.application.persistence.recommendation.RecommendationRecord;

public class FindRecommendedBy implements Command<List<ClientDto>>{

	private String sponsorId;
	
	public FindRecommendedBy(String sponsorId) {
		if(sponsorId == null || sponsorId.isEmpty()) {
			throw new IllegalArgumentException("The sponsor id cannot be null nor empty.");
		}
		this.sponsorId = sponsorId;
	}
	
	@Override
	public List<ClientDto> execute() throws BusinessException {
		List<RecommendationRecord> recommendations = PersistenceFactory.forRecommendation().findBySponsorId(sponsorId);
		List<ClientDto> res = new ArrayList<>();
		for (RecommendationRecord recommendation : recommendations) {
			res.add(DtoAssembler.toDto(PersistenceFactory.forClient().findById(recommendation.recommended_id).get()));
		}
		return res;
	}

}
