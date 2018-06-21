package app.clip.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.clip.catalogs.Errors;
import app.clip.models.Transaction;
import app.clip.system.CustomException;
import app.clip.util.Formatter;

public class TransactionDAOImpl implements TransactionDAO {
	
	private final static Logger LOG = LogManager.getLogger(TransactionDAOImpl.class);
	
//	private final static String configuration = "config/application.properties";
	private static String database = "database.txt";
	
//	public TransactionDAOImpl() throws IOException {
//		InputStream input = new FileInputStream(configuration);
//		Properties prop = new Properties();
//		prop.load(input);
//		database = prop.getProperty("database");
//		LOG.info("Connecting to database: " + database);
//	}
	
	@Override
	public void add(List<Transaction> transactions) throws IOException {
		LOG.info("Adding transaction...");
		
		FileWriter fw = new FileWriter(database, true);
		BufferedWriter bw = new BufferedWriter(fw);
	    PrintWriter pw = new PrintWriter(bw);
		for(Transaction transaction: transactions) {
			LOG.info("Adding transaction: " + transaction.toString());
			pw.println(Formatter.transactionToJson(transaction));
		}
		pw.close();
		bw.close();
		fw.close();
		
		LOG.info("Transaction added succesfully!");
		return;
	}

	@Override
	public Transaction show(String user_id, String transaction_id) throws IOException, CustomException {
		LOG.info("Searching transaction id: " + transaction_id + " User: " + user_id);
		
		FileReader fr = new FileReader(database);
		BufferedReader br = new BufferedReader(fr);
		String line;
		
		Transaction temp = new Transaction();
		Transaction transaction = null;
		
		while ((line = br.readLine()) != null) {
			temp = Formatter.jsonStringToTransaction(line);
			if(temp.getUser_id().intValue()==Integer.valueOf(user_id) && temp.getTransaction_id().equals(transaction_id)) {
				transaction = temp;
				break;
			}
			temp = new Transaction();
		}
		br.close();
		fr.close();
		
		if(transaction==null) {
			throw new CustomException(Errors.NOT_FOUND_TRANSACTION.getCode(), Errors.NOT_FOUND_TRANSACTION.getDescription());
		}
		
		return transaction;
	}

	@Override
	public List<Transaction> list(String user_id) throws IOException, CustomException {
		LOG.info("Listing transactions from user: " + user_id);
		
		FileReader fr = new FileReader(database);
		BufferedReader br = new BufferedReader(fr);
		String line;
		
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction temp = new Transaction();
		
		while ((line = br.readLine()) != null) {
			temp = Formatter.jsonStringToTransaction(line);
			if(temp.getUser_id().intValue()==Integer.valueOf(user_id)) {
				transactions.add(temp);
			}
			temp = new Transaction();
		}
		br.close();
		fr.close();
		
//		if(transactions.size()==0) {
//			throw new CustomException(Errors.NO_RECORDS_FOUND.getCode(), Errors.NO_RECORDS_FOUND.getDescription());
//		}
		
		return transactions;
	}

	@Override
	public Float sum(String user_id) throws IOException {
		LOG.info("Transactions sum of: " + user_id);
		
		FileReader fr = new FileReader(database);
		BufferedReader br = new BufferedReader(fr);
		String line;
		
		Transaction temp = new Transaction();
		Float total = 0F;
		
		while ((line = br.readLine()) != null) {
			temp = Formatter.jsonStringToTransaction(line);
			if(temp.getUser_id().intValue()==Integer.valueOf(user_id)) {
				total += temp.getAmount();
			}
			temp = new Transaction();
		}
		br.close();
		
		return total;
	}

}
