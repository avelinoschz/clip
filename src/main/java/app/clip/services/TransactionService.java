package app.clip.services;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.clip.dao.TransactionDAOImpl;
import app.clip.models.Transaction;
import app.clip.models.TransactionRequest;
import app.clip.system.CustomException;
import app.clip.util.Formatter;

public class TransactionService {
	
	private final static Logger LOG = LogManager.getLogger(TransactionService.class);
	
	public void add(TransactionRequest request) throws ParseException, IOException {
		LOG.info("Action requested: " + request.getAction());
		Transaction transaction = Formatter.jsonStringToTransaction(request.getJson());
		new TransactionDAOImpl().add(Arrays.asList(transaction));
		LOG.info(Formatter.transactionToJson(transaction));
		return;
	}
	
	public void show(TransactionRequest request) throws IOException, CustomException {
		LOG.info("Action requested: " + request.getAction());
		Transaction transaction = new TransactionDAOImpl().show(request.getUser_id(), request.getTransaction_id());
		LOG.info("Result: ");
		LOG.info(Formatter.transactionToJson(transaction));
		return;
	}
	
	public void list(TransactionRequest request) throws IOException, CustomException {
		LOG.info("Action requested: " + request.getAction());
		TransactionDAOImpl dao = new TransactionDAOImpl();
		List<Transaction> transactions = dao.list(request.getUser_id());
		LOG.info("Results: ");
		if(transactions.size()>0) {
			for(Transaction transaction: transactions) {
				LOG.info(Formatter.transactionToJson(transaction));
			}
		}else {
			LOG.info("[]");
		}
		
		return;
	}
	
	public void sum(TransactionRequest request) throws IOException, CustomException {
		LOG.info("Action requested: " + request.getAction());
		Float total = new TransactionDAOImpl().sum(request.getUser_id());

		StringBuffer sb = new StringBuffer();
		sb.append("{ ").
        append("\"user_id\":").append("\""+request.getUser_id()+"\", ").
        append("\"sum\":").append(total).
        append(" }");

		LOG.info("Result: ");
		LOG.info(sb.toString());
		return;
	}

}