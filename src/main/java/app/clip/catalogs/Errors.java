package app.clip.catalogs;

public enum Errors {
	NO_PARAMS(1, "No parameters entered."),
	WRONG_PARAMS(2, "Wrong number of parameters."),
	NOT_FOUND_ACTION(3, "Action entered not found."),
	NOT_FOUND_USER(4, "User entered not found."),
	WRONG_FORMAT_JSON(5, "Not valid Json format."),
	WRONG_FORMAT_TRANSACTIONID(6, "Not valid transactionid format."),
	NO_RECORDS_FOUND(7, "No records found."),
	NOT_FOUND_TRANSACTION(8, "Transaction not found.");

	
	private final int code;
	private final String description;
	
	Errors(int code, String description) {
		this.code = code;
		this.description = description;
	}

	public int getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
	
	@Override
	public String toString() {
		return code + ":" + description;
	}

}
