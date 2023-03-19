package uo.ri.cws.application.persistence.workorder.impl;

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
import uo.ri.cws.application.persistence.workorder.WorkOrderGateway;
import uo.ri.cws.application.persistence.workorder.WorkOrderRecord;

public class WorkOrderGatewayImpl implements WorkOrderGateway {

	@Override
	public void add(WorkOrderRecord t) {
		// TODO Auto-generated method stub
	}

	@Override
	public void remove(String id) {
		// TODO Auto-generated method stub
	}

	@Override
	public void update(WorkOrderRecord workOrder) {
		// TODO Auto-generated method stub
	}

	@Override
	public Optional<WorkOrderRecord> findById(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FIND_BY_ID"));
			pst.setString(1, id);

			rs = pst.executeQuery();

			return RecordAssembler.toWorkOrderRecord(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<WorkOrderRecord> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void markAsInvoiced(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_MARK_AS_INVOICED"));
			pst.setString(1, id);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void linkToInvoice(String invoiceId, String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_LINK_TO_INVOICE"));
			pst.setString(1, invoiceId);
			pst.setString(2, id);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<WorkOrderRecord> findNotInvoicedForClientDni(String dni) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FIND_NOT_INVOICED_FOR_CLIENT_DNI"));
			pst.setString(1, dni);
			rs = pst.executeQuery();
			return RecordAssembler.toWorkOrderRecordList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<WorkOrderRecord> findNotInvoicedForVehicleId(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FIND_NOT_INVOICED_FOR_VEHICLE_ID"));
			pst.setString(1, id);
			rs = pst.executeQuery();
			return RecordAssembler.toWorkOrderRecordList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<WorkOrderRecord> findByMechanicId(String mechanicId) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;

		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FIND_BY_MECHANIC_ID"));
			pst.setString(1, mechanicId);

			rs = pst.executeQuery();

			return RecordAssembler.toWorkOrderRecordList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public List<WorkOrderRecord> findForVoucherByVehicleId(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_FIND_FOR_VOUCHER_BY_VEHICLE_ID"));
			pst.setString(1, id);
			rs = pst.executeQuery();
			return RecordAssembler.toWorkOrderRecordList(rs);

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

	@Override
	public void markAsUsedForVoucher(String id) {
		Connection c = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			c = Jdbc.getCurrentConnection();
			pst = c.prepareStatement(Conf.getInstance().getProperty("TWORKORDERS_MARK_AS_USED_FOR_VOUCHER"));
			pst.setString(1, id);
			pst.executeUpdate();

		} catch (SQLException e) {
			throw new PersistenceException(e);
		} finally {
			Jdbc.close(rs, pst);
		}
	}

}
