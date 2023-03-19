package uo.ri.cws.application.business.mechanic.crud;

import java.util.List;
import java.util.Optional;

import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.mechanic.MechanicCrudService;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.business.mechanic.crud.commands.AddMechanic;
import uo.ri.cws.application.business.mechanic.crud.commands.DeleteMechanic;
import uo.ri.cws.application.business.mechanic.crud.commands.FindAllMechanics;
import uo.ri.cws.application.business.mechanic.crud.commands.FindMechanicByDni;
import uo.ri.cws.application.business.mechanic.crud.commands.FindMechanicById;
import uo.ri.cws.application.business.mechanic.crud.commands.UpdateMechanic;
import uo.ri.cws.application.business.util.command.CommandExecutor;

public class MechanicCrudServiceImpl implements MechanicCrudService {
	
	CommandExecutor executor = new CommandExecutor();

	@Override
	public MechanicDto addMechanic(MechanicDto mechanic) throws BusinessException {
		return executor.execute(new AddMechanic(mechanic));
	}

	@Override
	public void deleteMechanic(String idMechanic) throws BusinessException {
		executor.execute(new DeleteMechanic(idMechanic));
	}

	@Override
	public void updateMechanic(MechanicDto mechanic) throws BusinessException {
		executor.execute(new UpdateMechanic(mechanic));
	}

	@Override
	public Optional<MechanicDto> findMechanicById(String idMechanic) throws BusinessException {
		return executor.execute(new FindMechanicById(idMechanic));
	}

	@Override
	public Optional<MechanicDto> findMechanicByDni(String dniMechanic) throws BusinessException {
		return executor.execute(new FindMechanicByDni(dniMechanic));
	}

	@Override
	public List<MechanicDto> findAllMechanics() throws BusinessException {
		return executor.execute(new FindAllMechanics());
	}

}
