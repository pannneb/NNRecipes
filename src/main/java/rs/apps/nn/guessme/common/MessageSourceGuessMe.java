package rs.apps.nn.guessme.common;

import org.springframework.context.HierarchicalMessageSource;

public interface MessageSourceGuessMe extends HierarchicalMessageSource {

	public String getMessage(String code, String[] args);

}
