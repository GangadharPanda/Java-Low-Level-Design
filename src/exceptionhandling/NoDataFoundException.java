package exceptionhandling;

public class NoDataFoundException extends Throwable {
	final int errorCode;
	/**
	 * Rule : You can extend Throwable or it's child 
	 * Runtime Exception is basically cames when we miss anything at programing 
	 * 
	 * 
	 * While deciding a custom exception ,Make it Checked , if you wish that caller should be aware of this exception
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoDataFoundException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}
}
