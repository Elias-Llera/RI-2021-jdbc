package uo.ri.cws.application.persistence.vehicle;

import java.util.List;

import uo.ri.cws.application.persistence.Gateway;

public interface VehicleGateway extends Gateway<VehicleRecord>{

	List<VehicleRecord> findByClientId(String id);
	
}
