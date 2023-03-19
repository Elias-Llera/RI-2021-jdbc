package uo.ri.cws.application.ui.foreman.action;

import java.util.Optional;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.client.ClientCrudService;
import uo.ri.cws.application.business.client.ClientDto;
import uo.ri.cws.application.ui.printer.Printer;

public class ViewClientDetailAction implements Action {

	@Override
	public void execute() throws Exception {
		String id = Console.readString("Client id");
		
		ClientCrudService ccs = BusinessFactory.forClientCrudService();
		Optional<ClientDto> oc = ccs.findClientById( id );
		ClientDto c = oc.get();
		
		Printer.printClient(c);
	}

}
