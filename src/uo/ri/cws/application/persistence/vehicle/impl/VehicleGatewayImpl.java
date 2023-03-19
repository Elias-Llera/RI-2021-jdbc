package uo.ri.cws.application.persistence.vehicle.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;
import uo.ri.cws.application.persistence.vehicle.VehicleGateway;
import uo.ri.cws.application.persistence.vehicle.VehicleRecord;

public class VehicleGatewayImpl implements VehicleGateway {

	@Override
	public void add(VehicleRecord t) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(VehicleRecord t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<VehicleRecord> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehicleRecord> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<VehicleRecord> findByClientId(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TVEHICLES_FIND_BY_CLIENT_ID"));
			pst.setString(1, id);

			rs = pst.executeQuery();

			return RecordAssembler.toVehicleRecordList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
