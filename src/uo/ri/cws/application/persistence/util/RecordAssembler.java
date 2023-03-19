package uo.ri.cws.application.persistence.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.persistence.client.ClientRecord;
import uo.ri.cws.application.persistence.invoice.InvoiceRecord;
import uo.ri.cws.application.persistence.mechanic.MechanicRecord;
import uo.ri.cws.application.persistence.paymentmean.PaymentmeanRecord;
import uo.ri.cws.application.persistence.recommendation.RecommendationRecord;
import uo.ri.cws.application.persistence.vehicle.VehicleRecord;
import uo.ri.cws.application.persistence.workorder.WorkOrderRecord;

public class RecordAssembler {

	public static Optional<MechanicRecord> toMechanicRecord(ResultSet m) throws SQLException {
		if (m.next()) {
			return Optional.of(resultSetToMechanicRecord(m));
		}
		else 	
			return Optional.ofNullable(null);
	}
	

	public static List<MechanicRecord> toMechanicRecordList(ResultSet rs) throws SQLException {
		List<MechanicRecord> res = new ArrayList<>();
		while(rs.next()) {
			res.add( resultSetToMechanicRecord(rs));
		}

		return res;
	}

	private static MechanicRecord resultSetToMechanicRecord(ResultSet rs) throws SQLException {
		MechanicRecord value = new MechanicRecord();
		value.id = rs.getString("id");

		value.dni = rs.getString("dni");
		value.name = rs.getString("name");
		value.surname = rs.getString("surname");
		return value;
	}

	public static Optional<WorkOrderRecord> toWorkOrderRecord ( ResultSet rs ) throws SQLException {
		WorkOrderRecord record = null;
		
		if (rs.next()) {
			record = resultSetToWorkOrderRecord(rs);
			}
		return Optional.ofNullable(record);
	}

	public static List<WorkOrderRecord> toWorkOrderRecordList(ResultSet rs) throws SQLException {
		List<WorkOrderRecord> res = new ArrayList<>();
		while(rs.next()) {
			res.add( resultSetToWorkOrderRecord(rs)	);
		}
		
		return res;
	}
	
	
	private static WorkOrderRecord resultSetToWorkOrderRecord ( ResultSet rs ) throws SQLException {
		WorkOrderRecord record = new WorkOrderRecord();
		
		record.id = rs.getString("id");
		record.version = rs.getLong("version");

		record.vehicleId = rs.getString( "vehicle_Id");
		record.description = rs.getString( "description");
		record.date = rs.getTimestamp("date").toLocalDateTime();
		record.amount = rs.getDouble("amount");
		record.status = rs.getString( "status");
		record.mechanicId = rs.getString( "mechanic_Id");
		record.invoiceId = rs.getString( "invoice_Id");
				
		return record;		
	}
	
	public static Optional<ClientRecord> toClientRecord(ResultSet rs) throws SQLException{
		if (rs.next()) {
			ClientRecord client = new ClientRecord();

			client.id = rs.getString("id");
			client.dni = rs.getString("dni");
			client.email = rs.getString("email");
			client.name = rs.getString("name");
			client.phone = rs.getString("phone");
			client.surname = rs.getString("surname");
			client.city = rs.getString("city");
			client.street = rs.getString("street");
			client.zipcode = rs.getString("zipcode");
			
			return Optional.of(client);
		}
		else 	
			return Optional.ofNullable(null);
	}


	public static List<VehicleRecord> toVehicleRecordList(ResultSet rs) throws SQLException {
		List<VehicleRecord> res = new ArrayList<>();
		while(rs.next()) {
			res.add(resultsetToVehicleRecord(rs));
		}
		return res;
	}
	
	private static VehicleRecord resultsetToVehicleRecord(ResultSet rs) throws SQLException{
		VehicleRecord vehicle = new VehicleRecord();
		
		vehicle.id = rs.getString("id");
		vehicle.client_id = rs.getString("client_id");
		vehicle.platenumber = rs.getString("platenumber");
		vehicle.make = rs.getString("make");
		vehicle.model = rs.getString("model");
		vehicle.vehicletype_id = rs.getString("vehicleType_id");
		
		return vehicle;
	}


	public static List<ClientRecord> toClientRecordList(ResultSet rs) throws SQLException {
		List<ClientRecord> res = new ArrayList<>();
		while (rs.next()) {
			ClientRecord client = new ClientRecord();

			client.id = rs.getString("id");
			client.dni = rs.getString("dni");
			client.email = rs.getString("email");
			client.name = rs.getString("name");
			client.phone = rs.getString("phone");
			client.surname = rs.getString("surname");
			client.city = rs.getString("city");
			client.street = rs.getString("street");
			client.zipcode = rs.getString("zipcode");
			
			res.add(client);
		}
		return res;
	}


	public static List<RecommendationRecord> toRecommendationRecordList(ResultSet rs) throws SQLException {
		List<RecommendationRecord> res = new ArrayList<>();
		while (rs.next()) {
			RecommendationRecord recommendation = new RecommendationRecord();

			recommendation.id = rs.getString("id");
			recommendation.sponsor_id = rs.getString("sponsor_id");
			recommendation.recommended_id = rs.getString("recommended_id");
			recommendation.usedForVoucher = rs.getBoolean("usedForVoucher");
			
			res.add(recommendation);
		}
		return res;
	}


	public static List<PaymentmeanRecord> toPaymentmeanRecordList(ResultSet rs) throws SQLException {
		List<PaymentmeanRecord> res = new ArrayList<>();
		while (rs.next()) {
			PaymentmeanRecord recommendation = new PaymentmeanRecord();

			recommendation.id = rs.getString("id");
			recommendation.dtype = rs.getString("dtype");
			recommendation.accumulated = rs.getDouble("accumulated");
			recommendation.client_id = rs.getString("client_id");
			
			res.add(recommendation);
		}
		return res;
	}


	public static Optional<InvoiceRecord> toInvoiceRecord(ResultSet rs) throws SQLException {
		if(rs.next()) {
			InvoiceRecord invoice = new InvoiceRecord();
			invoice.amount = rs.getDouble("amount");
			invoice.date = rs.getDate("date").toLocalDate();
			invoice.id = rs.getString("id");
			invoice.number = rs.getLong("number");
			invoice.status = rs.getString("status");
			invoice.usedforvoucher = rs.getBoolean("usedForVoucher");
			invoice.vat = rs.getDouble("vat");
			return Optional.of(invoice);
		}
		return Optional.empty();
	}

}
