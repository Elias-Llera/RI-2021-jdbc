package uo.ri.cws.application.persistence.recommendation.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.recommendation.RecommendationGateway;
import uo.ri.cws.application.persistence.recommendation.RecommendationRecord;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;

public class RecommendationGatewayImpl implements RecommendationGateway {

	@Override
	public void add(RecommendationRecord recommendation) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TRECOMMENDATIONS_ADD"));
			pst.setString(1, recommendation.id);
			pst.setString(2, recommendation.sponsor_id);
			pst.setString(3, recommendation.recommended_id);
			pst.setBoolean(4, recommendation.usedForVoucher);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(RecommendationRecord t) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<RecommendationRecord> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecommendationRecord> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RecommendationRecord> findBySponsorId(String sponsorId) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TRECOMMENDATIONS_FIND_BY_SPONSOR_ID"));
			pst.setString(1, sponsorId);

			rs = pst.executeQuery();

			return RecordAssembler.toRecommendationRecordList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<RecommendationRecord> findForVoucher() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TRECOMMENDATIONS_FIND_FOR_VOUCHERS"));

			rs = pst.executeQuery();

			return RecordAssembler.toRecommendationRecordList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void markAsUsedForVoucher(String id) {
		// TODO Auto-generated method stub
		
	}

}
