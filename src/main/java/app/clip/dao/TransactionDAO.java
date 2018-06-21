package app.clip.dao;

import java.util.List;

import app.clip.models.Transaction;

public interface TransactionDAO {

	public void add(List<Transaction> transaction) throws Exception;
	public Transaction show(String user_id, String transactio_id) throws Exception;
	public List<Transaction> list(String user_id) throws Exception;
	public Float sum(String user_id) throws Exception;

}
