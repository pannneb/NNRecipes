package rs.apps.nn.guessme.exception;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

public class ValidateException extends RuntimeException {

	@Autowired
	MessageSource messageSource;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String valExcCode;

	private String valExcDesc;

	private String[] params;

//	public static ValidateException getInstance(String valExcCode, String valExcDesc) {
//		ValidateException ve = new ValidateException();
//		ve.setValExcCode(valExcCode);
//		ve.setValExcDesc(messageSource.getMessage(valExcDesc, null, Locale.ENGLISH));
//		return ve;
//	}

	public ValidateException(String valExcCode, String valExcDesc) {
		super();
		this.valExcCode = valExcCode;
		this.valExcDesc = messageSource.getMessage(valExcDesc, null, Locale.ENGLISH);
	}

	public ValidateException(String valExcCode, String valExcDesc, String[] params) {
		super();
		this.valExcCode = valExcCode;
		this.valExcDesc = messageSource.getMessage(valExcDesc, null, Locale.ENGLISH);
		this.setParams(params);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ValidateException [valExcCode=").append(valExcCode).append(", valExcDesc=").append(valExcDesc)
		.append(", params=").append(params)
				.append("]");
		return builder.toString();
	}

	public String getValExcCode() {
		return valExcCode;
	}

	public void setValExcCode(String valExcCode) {
		this.valExcCode = valExcCode;
	}

	public String getValExcDesc() {
		return valExcDesc;
	}

	public void setValExcDesc(String valExcDesc) {
		this.valExcDesc = valExcDesc;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

}
