package rs.apps.nn.recipes.common;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:recipes.properties")
public class MessageSourceGuessMeImpl extends ResourceBundleMessageSource implements MessageSourceGuessMe {

	@Value("${recipes.locale.default}")
	private String defaultLocale;

	@Override
	public String getMessage(String code, String[] args) {
		return super.getMessage(code, args, Locale.getDefault());
	}
}
