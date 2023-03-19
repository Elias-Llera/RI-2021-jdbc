package uo.ri.cws.application.persistence.recommendation;

import java.util.List;

import uo.ri.cws.application.persistence.Gateway;

public interface RecommendationGateway extends Gateway<RecommendationRecord>{

	List<RecommendationRecord>findBySponsorId(String sponsorId);
	
	List<RecommendationRecord>findForVoucher();

	void markAsUsedForVoucher(String id);
	
}
