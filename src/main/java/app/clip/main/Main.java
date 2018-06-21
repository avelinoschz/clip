package app.clip.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.clip.controllers.TransactionController;
import app.clip.system.CustomException;

public class Main {
	
	private static final Logger LOG = LogManager.getLogger(Main.class);

	public static void main(String[] args) {
		LOG.info("Starting PayClip Application...");
		try {
			new TransactionController().selectAction(args);
			LOG.info("Operation finished.");
		} catch(CustomException ce) {
			LOG.error(ce.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
