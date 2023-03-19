package uo.ri.cws.application.persistence.mechanic;

import java.util.Optional;

import uo.ri.cws.application.persistence.Gateway;

public interface MechanicGateway extends Gateway<MechanicRecord> {

	/*
	 * Finds a row in the table
	 * @param record's field to retrieve (dni)
	 * @return dto from that record, probably null
	 */
	Optional<MechanicRecord> findByDni(String dni);
}
