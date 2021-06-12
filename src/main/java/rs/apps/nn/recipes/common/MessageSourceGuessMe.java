package rs.apps.nn.recipes.common;

import org.springframework.context.HierarchicalMessageSource;

public interface MessageSourceGuessMe extends HierarchicalMessageSource {

	public String getMessage(String code, String[] args);

}
