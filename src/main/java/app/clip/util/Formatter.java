package app.clip.util;

import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import app.clip.models.Transaction;

public class Formatter {
		
	public static Transaction jsonStringToTransaction(String jsonString) {
		Transaction transaction = new Transaction();
		JsonObject json = new JsonParser().parse(jsonString).getAsJsonObject();
		
		transaction.setTransaction_id(json.get("transaction_id")!=null?json.get("transaction_id").getAsString():UUID.randomUUID().toString());
		transaction.setAmount(json.get("amount").getAsFloat());
		transaction.setDescription(json.get("description").getAsString());
		transaction.setDate(json.get("date").getAsString());
		transaction.setUser_id(json.get("user_id").getAsInt());
		
		return transaction;
	}

	public static String transactionToJson(Transaction transaction) {
		Gson gson = new Gson();
		String jsonString = gson.toJson(transaction);
		
		return jsonString;
	}
	
}
