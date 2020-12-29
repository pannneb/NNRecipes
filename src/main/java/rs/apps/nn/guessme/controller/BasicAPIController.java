package rs.apps.nn.guessme.controller;

import rs.apps.nn.guessme.api.EnumResponseStatus;
import rs.apps.nn.guessme.api.ResponseData;
import rs.apps.nn.guessme.common.MessageSourceGuessMe;
import rs.apps.nn.guessme.exception.ValidateException;

public class BasicAPIController {

	public void handleException(ResponseData rd, MessageSourceGuessMe messageSource, Exception e) {
		if (e instanceof ValidateException) {
			ValidateException v = (ValidateException) e;
			rd.setStatus(v.getValExcCode());
			rd.setDescription(messageSource.getMessage(v.getValExcDesc(), v.getParams()));
		} else {
			rd.setStatus(EnumResponseStatus.RESP_GENERAL_ERR.getId());
			rd.setDescription(e.getMessage());
		}
	}

}
