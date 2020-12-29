package rs.apps.nn.guessme.common;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:guessme.properties")
public class MessageSourceGuessMeImpl extends ResourceBundleMessageSource implements MessageSourceGuessMe {

	@Value("${guessme.locale.default}")
	private String defaultLocale;

	@Override
	public String getMessage(String code, String[] args) {
		return super.getMessage(code, args, Locale.getDefault());
	}
}
