package uo.ri.cws.application.ui.manager.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicDto;

public class UpdateMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		// Get info
		String id = Console.readString("Type mechahic id to update"); 
		String name = Console.readString("Name"); 
		String surname = Console.readString("Surname");
		
		MechanicDto m = new MechanicDto();
		m.id = id;
		m.name = name;
		m.surname = surname;
		
		// Process
		BusinessFactory.forMechanicCrudService().updateMechanic(m);
		
		// Print result
		Console.println("Mechanic updated");
	}

}
