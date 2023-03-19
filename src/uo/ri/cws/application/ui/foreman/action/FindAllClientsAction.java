package uo.ri.cws.application.ui.foreman.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.cws.application.business.BusinessFactory;
import uo.ri.cws.application.business.client.ClientCrudService;
import uo.ri.cws.application.business.client.ClientDto;
import uo.ri.cws.application.ui.printer.Printer;

public class FindAllClientsAction implements Action {

    @Override
    public void execute() throws Exception {
	ClientCrudService ccs = BusinessFactory.forClientCrudService();
	List<ClientDto> clients = ccs.findAllClients();
	Console.println("\nList of clients \n");
	Printer.printClients(clients);
    }

}
