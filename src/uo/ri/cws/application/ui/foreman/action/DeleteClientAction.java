package uo.ri.cws.application.ui.foreman.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.client.ClientCrudService;

public class DeleteClientAction implements Action {

	@Override
	public void execute() throws Exception {
		String id = Console.readString("Client id");;
		
		ClientCrudService ccs = BusinessFactory.forClientCrudService();
		ccs.deleteClient( id );
		
		Console.print("The client has been removed");
	}

}
