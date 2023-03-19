package uo.ri.cws.application.persistence.voucher.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.voucher.VoucherGateway;
import uo.ri.cws.application.persistence.voucher.VoucherRecord;

public class VoucherGatewayImpl implements VoucherGateway{
	
	@Override
	public void add(VoucherRecord voucher) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TVOUCHERS_ADD"));
			pst.setString(1, voucher.id);
			pst.setString(2, voucher.code);
			pst.setString(3, voucher.description);
			pst.setDouble(4, voucher.available);

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
	public void update(VoucherRecord t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<VoucherRecord> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VoucherRecord> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
