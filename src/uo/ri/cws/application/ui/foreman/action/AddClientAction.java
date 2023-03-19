package uo.ri.cws.application.ui.foreman.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.client.ClientCrudService;
import uo.ri.cws.application.business.client.ClientDto;

public class AddClientAction implements Action {

	@Override
	public void execute() throws Exception {
		ClientDto c = new ClientDto();
		c.dni = Console.readString("DNI");
		c.name = Console.readString("Name");
		c.surname = Console.readString("Surname");
		c.phone = Console.readString("Phone");
		c.email = Console.readString("Email");
		c.addressStreet = Console.readString("Street");
		c.addressCity = Console.readString("City");
		c.addressZipcode = Console.readString("Zipcode");
		c.version = 1L;

		String idSponsor = Console.readString("Sponsor id [type 0 if no sponsor]");
		idSponsor = (idSponsor.compareTo("0") == 0)? null : idSponsor;
		
		ClientCrudService ccs = BusinessFactory.forClientCrudService();
		ccs.addClient( c, idSponsor );
		Console.println("New client added");
		
	}

}
