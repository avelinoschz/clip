package app.clip.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;

import org.apache.commons.lang3.EnumUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.clip.catalogs.Actions;
import app.clip.catalogs.Errors;
import app.clip.models.TransactionRequest;
import app.clip.services.TransactionService;
import app.clip.system.CustomException;

public class TransactionController {
	
	private final static Logger LOG = LogManager.getLogger(TransactionController.class);
	private TransactionService service = new TransactionService();
	
	public void selectAction(String[] params) throws CustomException, ParseException, IOException {
		TransactionRequest request = reviewRequest(params);
		
		LOG.info("Selecting service...");
		switch(request.getAction()) {
			case "add":
				service.add(request);
				break;
			case "show":
				service.show(request);
				break;
			case "list":
				service.list(request);
				break;
			case "sum":
				service.sum(request);
				break;
		}
	}
	
	private TransactionRequest reviewRequest(String[] params) throws CustomException{
		LOG.info("Reviewing request...");
		Integer numberParams = reviewParameters(params);
		TransactionRequest request = null;
		
		if(numberParams==3) {
			if(params[1].equals(Actions.ADD.getDescription())){
				if(isValidJson(params[2])) {
					request = new TransactionRequest(params[0], params[1]);
					request.setJson(params[2]);
				} else {
					throw new CustomException(Errors.WRONG_FORMAT_JSON.getCode(), Errors.WRONG_FORMAT_JSON.getDescription());
				}
			} else {
				throw new CustomException(Errors.NOT_FOUND_ACTION.getCode(), Errors.NOT_FOUND_ACTION.getDescription());
			}
		}else if(numberParams==2) {
			if(params[1].equals(Actions.LIST.getDescription()) || params[1].equals(Actions.SUM.getDescription())) {
				request = new TransactionRequest(params[0], params[1]);
			} else {
				if(isValidTransactionId(params[1])) {
					request = new TransactionRequest(params[0], Actions.SHOW.getDescription());
					request.setTransaction_id(params[1]);
				} else {
					throw new CustomException(Errors.WRONG_FORMAT_TRANSACTIONID.getCode(), Errors.WRONG_FORMAT_TRANSACTIONID.getDescription());
				}
			}
		}
		return request;
	}
	
	private Integer reviewParameters(String[] params) throws CustomException {
		LOG.info("Reviewing parameters...");
		int numberParams = params.length;
		LOG.info("Number of parameters entered: " + numberParams);
		if(numberParams!=0) {
			LOG.info("Input parameters:" + Arrays.toString(params));
			if(numberParams<2 || numberParams>3) {
				throw new CustomException(Errors.WRONG_PARAMS.getCode(), Errors.WRONG_PARAMS.getDescription());
			}
		}else {
			throw new CustomException(Errors.NO_PARAMS.getCode(), Errors.NO_PARAMS.getDescription());
		}
		return numberParams;
	}
	
	@SuppressWarnings("unused")
	private boolean isValidAction(String action) {
		boolean result = false;
		LOG.info("Validating action: " + action);
		if(EnumUtils.isValidEnum(Actions.class, action)) {
			result = true;
		}
		return result;
	}
	
	@SuppressWarnings("unused")
	private boolean isValidUser(String user_id) {
		//TODO
		return true;
	}
	
	private boolean isValidJson(String json) {
		//TODO
		return true;
	}
	
	private boolean isValidTransactionId(String transaction_id) {
		//TODO
		return true;
	}

}
