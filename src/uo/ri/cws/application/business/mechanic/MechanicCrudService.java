package uo.ri.cws.application.business.mechanic;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;

/**
 * This service is intended to be used by the Manager
 * It follows the ISP principle (@see SOLID principles from RC Martin)
 */
public interface MechanicCrudService {

	
	/**
	 * Add a new mechanic to the system with the data specified in the dto.
	 * 		The id value will be ignored as it is generated here.
	 * @param mecanich A dto containing info to be added
	 * @return dto with the id value set to the UUID generated 
	 * @throws IllegalArgumentException when argument is null or dni is null or empty string
	 * @throws BusinessException if there already exists another mechanic with the same dni
	 */
	MechanicDto addMechanic(MechanicDto mechanic) throws BusinessException;

	/**
	 * @param idMechanic the id of the mechanid to be deleted
	 * @throws 		BusinessException if the mechanic does not exist or if there are work orders
	 * registered for this mechanic
	 * @throws		IllegalArgumentException when argument is null or empty string
	 */
	void deleteMechanic(String idMechanic) throws BusinessException;

	/**
	 * Updates values for the mechanic specified by the id field,
	 * 		just name and surname will be updated
	 * @param mechanic A dto identifying the mechanic to update by the field id,
	 * 					and the data to update in name and surname
	 * @throws BusinessException if the mechanic does not exist 
	 * @throws IllegalArgumentException when the argument is null or any of the fields (id, dni, name, surname) are null or empty 
	 */
	void updateMechanic(MechanicDto mechanic) throws BusinessException;

	/**
	 * @param idMechanic The id of the mechanic to find
	 * @return the dto for the mechanic or null if there is none with the id
	 * @throws IllegalArgumentException when argument is null or empty string
	 *        DOES NOT throw BusinessException
	 */
	Optional<MechanicDto> findMechanicById(String idMechanic) throws BusinessException;

	/**
	 * @param dniMechanic The dni of the mechanic to find
	 * @return the dto for the mechanic or null if there is none with this dni
	 * @throws IllegalArgumentException when argument is null or empty string
	 *        DOES NOT throw BusinessException
	 */
	Optional<MechanicDto> findMechanicByDni(String dniMechanic) throws BusinessException;
	
	
	/**
	 * @return the list of all mechanics registered in the system
	 * 	without regarding their contract status or if they have
	 * 	contract or not. It might be an empty list if there is no mechanic
	 *
	 * DOES NOT @throws BusinessException
	 */
	List<MechanicDto> findAllMechanics() throws BusinessException;

	
	
}
