package uo.ri.cws.application.persistence.client.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import alb.util.jdbc.Jdbc;
import uo.ri.cws.application.persistence.PersistenceException;
import uo.ri.cws.application.persistence.client.ClientGateway;
import uo.ri.cws.application.persistence.client.ClientRecord;
import uo.ri.cws.application.persistence.util.Conf;
import uo.ri.cws.application.persistence.util.RecordAssembler;

public class ClientGatewayImpl implements ClientGateway{

	@Override
	public void add(ClientRecord client) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();

			pst = c.prepareStatement(Conf.getInstance().getProperty("TCLIENTS_ADD"));
			pst.setString(1, client.id);
			pst.setString(2, client.dni);
			pst.setString(3, client.email);
			pst.setString(4, client.name);
			pst.setString(5, client.phone);
			pst.setString(6, client.surname);
			pst.setString(7, client.city);
			pst.setString(8, client.street);
			pst.setString(9, client.zipcode);

			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void remove(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCLIENTS_DELETE"));
			pst.setString(1, id);
			
			pst.executeUpdate();
			
			
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void update(ClientRecord client) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCLIENTS_UPDATE"));
			pst.setString(1, client.email);
			pst.setString(2, client.name);
			pst.setString(3, client.phone);
			pst.setString(4, client.surname);
			pst.setString(5, client.city);
			pst.setString(6, client.street);
			pst.setString(7, client.zipcode);
			pst.setString(8, client.id);
			
			pst.executeUpdate();
			
		} catch (SQLException e) {
			throw new PersistenceException(e);
		}
		finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public Optional<ClientRecord> findById(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCLIENTS_FIND_BY_ID"));
			pst.setString(1, id);

			rs = pst.executeQuery();

			return RecordAssembler.toClientRecord(rs);
			
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<ClientRecord> findAll() {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCLIENTS_FIND_ALL"));

			rs = pst.executeQuery();

			return RecordAssembler.toClientRecordList(rs);
			
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public Optional<ClientRecord> findByDni(String dni) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TCLIENTS_FIND_BY_DNI"));
			pst.setString(1, dni);

			rs = pst.executeQuery();

			return RecordAssembler.toClientRecord(rs);
			
		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
