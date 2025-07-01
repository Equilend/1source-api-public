package com.os.console;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.os.console.api.ApplicationConfig;

@SpringBootApplication
@EnableScheduling
@ConfigurationPropertiesScan
public class SpringBootConsoleApp extends AbstractConsole implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(SpringBootConsoleApp.class);

	@Autowired
	ApplicationConfig authConfig;

	@Autowired
	LoginConsole loginConsole;

	@Autowired
	LoansConsole loansConsole;

	public static void main(String[] args) {
		logger.debug("STARTING THE APPLICATION");
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringBootConsoleApp.class, args);
		SpringApplication.exit(applicationContext);
		logger.debug("APPLICATION FINISHED");
	}

	@Override
	public void run(String... args) {

		logger.debug("EXECUTING : command line runner");

		for (int i = 0; i < args.length; ++i) {
			logger.info("args[{}]: {}", i, args[i]);
		}

//		Console console = System.console();
//		
//		if (console == null) {
//			logger.warn("No console available");
//			return;
//		}

		BufferedReader consoleIn = new BufferedReader(new InputStreamReader(System.in));

		loginConsole.login(authConfig, consoleIn);

		if (ApplicationConfig.TOKEN == null || ApplicationConfig.ACTING_PARTY == null || ApplicationConfig.ACTING_AS == null) {
			System.exit(-2);
		}
		
		System.out.println();
		System.out.println("\"?\" or \"help\" to see menu");
		System.out.println();

		execute(consoleIn);
	}

	@Override
	protected void printMenu() {
		System.out.println("Main Menu");
		System.out.println("------------------------");
		System.out.println("O - Operations Reporting");
		System.out.println("L - Loans");
		System.out.println("R - Returns");
		System.out.println("C - Recalls");
		System.out.println("T - Rerates");
		System.out.println("D - Delegations");
		System.out.println("E - Events");
		System.out.println("P - Parties");
		System.out.println("F - Figi Lookup");
	}

	@Override
	protected boolean prompt() {
		System.out.print(ApplicationConfig.ACTING_PARTY.getPartyId() + " > ");
		return true;
	}

	@Override
	protected void handleArgs(String[] args, BufferedReader consoleIn) {
		
		if (args[0].equalsIgnoreCase("l")) {
			loansConsole.execute(consoleIn);
		} else if (args[0].equalsIgnoreCase("r")) {
			ReturnsConsole returnsConsole = new ReturnsConsole();
			returnsConsole.execute(consoleIn);
		} else if (args[0].equalsIgnoreCase("c")) {
			RecallsConsole recallsConsole = new RecallsConsole();
			recallsConsole.execute(consoleIn);
		} else if (args[0].equalsIgnoreCase("t")) {
			ReratesConsole reratesConsole = new ReratesConsole();
			reratesConsole.execute(consoleIn);
		} else if (args[0].equalsIgnoreCase("d")) {
			DelegationsConsole delegationsConsole = new DelegationsConsole();
			delegationsConsole.execute(consoleIn);
		} else if (args[0].equalsIgnoreCase("e")) {
			EventsConsole eventsConsole = new EventsConsole();
			eventsConsole.execute(consoleIn);
		} else if (args[0].equalsIgnoreCase("p")) {
			PartiesConsole partiesConsole = new PartiesConsole();
			partiesConsole.execute(consoleIn);
		} else if (args[0].equalsIgnoreCase("f")) {
			FigiConsole figiConsole = new FigiConsole();
			figiConsole.execute(consoleIn);
		} else if (args[0].equalsIgnoreCase("o")) {
			OperationsConsole operationsConsole = new OperationsConsole();
			operationsConsole.execute(consoleIn);
		} else {
			System.out.println("Unknown command");
		}		
	}

}