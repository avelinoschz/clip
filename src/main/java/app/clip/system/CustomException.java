package app.clip.system;

public class CustomException extends Exception {

	private static final long serialVersionUID = 1L;
	
	int code;
	String message;
	
	public CustomException(int code, String message) {
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return code + ":" + message;
	}
}
