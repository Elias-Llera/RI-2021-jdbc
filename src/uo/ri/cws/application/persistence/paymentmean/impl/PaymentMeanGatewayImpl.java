package uo.ri.cws.application.persistence.paymentmean.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.paymentmean.PaymentMeanGateway;
import uo.ri.cws.application.persistence.paymentmean.PaymentmeanRecord;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;

public class PaymentMeanGatewayImpl implements PaymentMeanGateway {

	@Override
	public void add(PaymentmeanRecord paymentMean) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYMENTMEAN_ADD"));
			pst.setString(1, paymentMean.id);
			pst.setString(2, paymentMean.dtype);
			pst.setDouble(3, paymentMean.accumulated);
			pst.setString(4, paymentMean.client_id);

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
	public void update(PaymentmeanRecord t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<PaymentmeanRecord> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentmeanRecord> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PaymentmeanRecord> findByClientId(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TPAYMENTMEAN_FIND_BY_CLIENT_ID"));
			pst.setString(1, id);

			rs = pst.executeQuery();

			return RecordAssembler.toPaymentmeanRecordList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
