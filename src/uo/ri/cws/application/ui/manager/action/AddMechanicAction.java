package uo.ri.cws.application.ui.manager.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicDto;

public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		// Get info
		String dni = Console.readString("Dni"); 
		String name = Console.readString("Name"); 
		String surname = Console.readString("Surname");
		
		// Process
		MechanicDto m = new MechanicDto();
		m.dni = dni;
		m.name = name;
		m.surname = surname;
		
		BusinessFactory.forMechanicCrudService().addMechanic(m);
	
		
		// Print result
		Console.println("Mechanic added");
	}

}
