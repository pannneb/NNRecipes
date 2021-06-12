package rs.apps.nn.recipes.controller;

import rs.apps.nn.recipes.api.EnumResponseStatus;
import rs.apps.nn.recipes.api.ResponseData;
import rs.apps.nn.recipes.common.MessageSourceGuessMe;
import rs.apps.nn.recipes.exception.ValidateException;

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
