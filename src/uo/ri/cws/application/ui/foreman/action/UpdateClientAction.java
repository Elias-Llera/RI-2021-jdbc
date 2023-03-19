package uo.ri.cws.application.ui.foreman.action;

import java.util.Optional;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.client.ClientCrudService;
import uo.ri.cws.application.business.client.ClientDto;
import uo.ri.cws.application.ui.printer.Printer;

public class UpdateClientAction implements Action {

	@Override
	public void execute() throws Exception {
		ClientCrudService ccs = BusinessFactory.forClientCrudService();
		
		String id = Console.readString("Client id");
		Optional<ClientDto> oc = ccs.findClientById( id );
		ClientDto c = oc.get();
		Printer.printClient( c );

		c.name = Console.readString("Name");
		c.surname = Console.readString("Surname");
		c.phone = Console.readString("Phone");
		c.email = Console.readString("Email");
		c.addressStreet = Console.readString("Street");
		c.addressCity = Console.readString("City");
		c.addressZipcode = Console.readString("Zipcode");

		
		ccs.updateClient( c );
		Console.println("The client has been succesfully updated");
		
	}
	
}
