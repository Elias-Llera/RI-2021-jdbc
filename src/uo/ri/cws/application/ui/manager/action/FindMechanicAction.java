package uo.ri.cws.application.ui.manager.action;

import java.util.Optional;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessException;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.mechanic.MechanicDto;
import uo.ri.cws.application.ui.printer.Printer;

public class FindMechanicAction implements Action {

	
	@Override
	public void execute() throws BusinessException {

		// Get info
		String id = Console.readString("Mechanic ID "); 

		// Process
		Optional<MechanicDto> m;

		m = BusinessFactory.forMechanicCrudService().findMechanicById(id);		
			
		if (m.isEmpty())
			Console.println("\nMechanic does not exist\n");  
		else
			Printer.printMechanic( m.get() );
	}
}
