package rs.apps.nn.recipes.controller.dynamicrowadd;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.jpos.iso.ISOException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import rs.asoft.online.testweb.model.EnumProcessorTypes;
import rs.asoft.online.testweb.model.IsoMsgField;
import rs.asoft.online.testweb.model.OnlineSimConfig;
import rs.asoft.online.testweb.model.TestMsgData;
import rs.asoft.online.testweb.model.TransSenderData;
import rs.asoft.online.testweb.service.TransSenderService;
import rs.asoft.online.testweb.util.ConfigLoader;

@Controller
@Slf4j
@RequestMapping("/packager/bps/")
public class TransSenderBPSController {

	private TransSenderService transSenderService;
	private List<IsoMsgField> listIsoFields = new ArrayList<IsoMsgField>();

	public static final String ONLINE_ENDPOINT_CONFIG_PATH = "src/main/resources/config/CLIENTPLSHDER/online-sim-endpoint-CLIENTPLSHDER.conf";
	public static final String ONLINE_ENDPOINT_CONFIG_MSGS_PATH = "src/main/resources/config/CLIENTPLSHDER/online-sim-test-msgs-CLIENTPLSHDER.conf";
	private String PROCESSOR_ID = EnumProcessorTypes.PR_TYPE_BPS.getId();
	
	public TransSenderBPSController(TransSenderService transSenderService) {
		super();
		this.transSenderService = transSenderService;
	}

	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String onlineSbWebtestViewPackagers(Model model) {
		log.info("start - GET");

		TransSenderData data = new TransSenderData();
		// Default for BPS
		data.setPackager("org.jpos.iso.packager.ISO87BPackager");
		model.addAttribute("transData", data);

		prepareLists(model);

		try {
			OnlineSimConfig config = ConfigLoader.loadEndpointConfig(ONLINE_ENDPOINT_CONFIG_PATH.replace("CLIENTPLSHDER", PROCESSOR_ID));
			log.info("config: {}", config);
			data.setConfig(config);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error: {}", e);
		}

		return "indexBPS";
	}

	private void prepareLists(Model model) {
		model.addAttribute("packagers", prepareListOfPackager());
		model.addAttribute("isomsgfields", prepareListOfTransactionFields());
		model.addAttribute("testTransactions", prepareListOfTestTransactions());
	}

	@RequestMapping(value = { "/submit", "/submit/" }, method = RequestMethod.POST)
	// @PostMapping("/submit")
	public String onlineSbWebtestMain(@ModelAttribute TransSenderData transSenderData, Model model) {
		log.info("start - submit - POST");

		if (model.getAttribute("transData") == null)
			model.addAttribute("transData", transSenderData);

		log.info("transData: {}", transSenderData);
		OnlineSimConfig config = null;
		try {
			config = ConfigLoader.loadEndpointConfig(ONLINE_ENDPOINT_CONFIG_PATH.replace("CLIENTPLSHDER", PROCESSOR_ID));
			log.info("config: {}", config);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error: {}", e);
		}

		try {
			transSenderService.createMessageFromHexStr(transSenderData, 0, true);
			transSenderService.sendTestMessage(transSenderData, config);
		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// transSenderData.setParsedTransactionData(transSenderData.getUnparsedTransactionData());

		prepareLists(model);

		return "indexBPS";
	}

	@RequestMapping(value = { "/preview" }, method = RequestMethod.POST)
	public @ResponseBody String onlineSbWebtestPreviewJSONAjax(@RequestBody TransSenderData transSenderData) {
		log.info("start - preview - AJAX");

		log.info("transData: {}", transSenderData);

		try {
			transSenderService.createMessageFromHexStr(transSenderData, 0, false);
		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return transSenderData.getParsedTransactionData();
	}

	@RequestMapping(value = "/preview1", method = RequestMethod.POST)
	@ResponseBody
	public String activateCard(HttpServletRequest request, Model model, TransSenderData transSenderData,
			BindingResult result) {
		log.info("start - preview1 - AJAX");

		log.info("transData: {}", transSenderData);

		try {
			transSenderService.createMessageFromHexStr(transSenderData, 0, false);
		} catch (ISOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return transSenderData.getParsedTransactionData();

	}

	private List<String> prepareListOfPackager() {

		// create scanner and disable default filters (that is the 'false' argument)
		final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(
				false);
		// add include filters which matches all the classes (or use your own)
		provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*")));

		// get matching classes defined in the package
		final Set<BeanDefinition> classes = provider.findCandidateComponents("org.jpos.iso.packager");

		List<String> packagersList = new ArrayList<String>();

		// this is how you can load the class type from BeanDefinition instance
		for (BeanDefinition bean : classes) {
			try {
				Class<?> clazz = Class.forName(bean.getBeanClassName());
				log.debug("Next class from packet: {}", clazz);
				packagersList.add(clazz.getCanonicalName());
			} catch (ClassNotFoundException e) {
				log.error("Exception:", e);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// ... do your magic with the class ...
		}

		return packagersList;

	}

	private List<TestMsgData> prepareListOfTestTransactions() {

		try {
			List<TestMsgData> configMsgsList = ConfigLoader.loadConfigMsgs(ONLINE_ENDPOINT_CONFIG_MSGS_PATH.replace("CLIENTPLSHDER", PROCESSOR_ID));
			return configMsgsList;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("error: {}", e);
		}
		return new ArrayList<TestMsgData>();
	}

	private synchronized List<IsoMsgField> prepareListOfTransactionFields() {
		if (listIsoFields == null || listIsoFields.isEmpty()) {
			listIsoFields = new ArrayList<IsoMsgField>();
			listIsoFields.add(new IsoMsgField("0", "MSG Type"));
			listIsoFields.add(new IsoMsgField("2", "Primary Account Number (PAN)"));
			listIsoFields.add(new IsoMsgField("3", "Processing Code"));
			listIsoFields.add(new IsoMsgField("4", "Amount, Transaction"));
			listIsoFields.add(new IsoMsgField("5", "Amount, Settlement"));
			listIsoFields.add(new IsoMsgField("6", "Amount, Cardholder Billing"));
			listIsoFields.add(new IsoMsgField("7", "Transmission Date and Time"));
			listIsoFields.add(new IsoMsgField("8", "Amount, Cardholder Billing Fee1"));
			listIsoFields.add(new IsoMsgField("9", "Conversion Rate, Settlement"));
			listIsoFields.add(new IsoMsgField("10", "Conversion Rate, Cardholder Billing"));
			listIsoFields.add(new IsoMsgField("11", "Systems Trace Audit Number"));
			listIsoFields.add(new IsoMsgField("12", "Time, Local Transaction"));
			listIsoFields.add(new IsoMsgField("13", "Date, Local Transaction"));
			listIsoFields.add(new IsoMsgField("14", "Date, Expiration"));
			listIsoFields.add(new IsoMsgField("15", "Date, Settlement"));
			listIsoFields.add(new IsoMsgField("16", "Date, Conversion"));
			listIsoFields.add(new IsoMsgField("17", "Date, Capture1"));
			listIsoFields.add(new IsoMsgField("18", "Merchant Type"));
			listIsoFields.add(new IsoMsgField("19", "Acquiring Institution Country Code1"));
			listIsoFields.add(new IsoMsgField("20", "Primary Account Number (PAN) Country Code"));
			listIsoFields.add(new IsoMsgField("21", "Forwarding Institution Country Code1"));
			listIsoFields.add(new IsoMsgField("22", "Point-of-Service (POS) Entry Mode"));
			listIsoFields.add(new IsoMsgField("23", "Card Sequence Number"));
			listIsoFields.add(new IsoMsgField("24", "Network International ID 1"));
			listIsoFields.add(new IsoMsgField("25", "Point-of-Service (POS) Condition Code 1"));
			listIsoFields.add(new IsoMsgField("26", "Point-of-Service (POS) Personal ID Number"));
			listIsoFields.add(new IsoMsgField("27", "Authorization ID Response Length1"));
			listIsoFields.add(new IsoMsgField("28", "Amount, Transaction Fee"));
			listIsoFields.add(new IsoMsgField("29", "Amount, Settlement Fee1"));
			listIsoFields.add(new IsoMsgField("30", "Amount, Transaction Processing Fee1"));
			listIsoFields.add(new IsoMsgField("31", "Amount, Settlement Processing Fee1"));
			listIsoFields.add(new IsoMsgField("32", "Acquiring Institution ID Code"));
			listIsoFields.add(new IsoMsgField("33", "Forwarding Institution ID Code"));
			listIsoFields.add(new IsoMsgField("34", "Primary Account Number (PAN), Extended1"));
			listIsoFields.add(new IsoMsgField("35", "Track 2 Data"));
			listIsoFields.add(new IsoMsgField("36", "Track 3 Data1"));
			listIsoFields.add(new IsoMsgField("37", "Retrieval Reference Number"));
			listIsoFields.add(new IsoMsgField("38", "Authorization ID Response"));
			listIsoFields.add(new IsoMsgField("39", "Response Code"));
			listIsoFields.add(new IsoMsgField("40", "Service Restriction Code1"));
			listIsoFields.add(new IsoMsgField("41", "Card Acceptor Terminal ID"));
			listIsoFields.add(new IsoMsgField("42", "Card Acceptor ID Code"));
			listIsoFields.add(new IsoMsgField("43", "Card Acceptor Name/Location"));
			listIsoFields.add(new IsoMsgField("44", "Additional Response Data"));
			listIsoFields.add(new IsoMsgField("45", "Track 1 Data"));
			listIsoFields.add(new IsoMsgField("46", "Additional Data—ISO Use1"));
			listIsoFields.add(new IsoMsgField("47", "Additional Data—National Use2"));
			listIsoFields.add(new IsoMsgField("48", "Additional Data—Private Use"));
			listIsoFields.add(new IsoMsgField("49", "Currency Code, Transaction"));
			listIsoFields.add(new IsoMsgField("50", "Currency Code, Settlement"));
			listIsoFields.add(new IsoMsgField("51", "Currency Code, Cardholder Billing"));
			listIsoFields.add(new IsoMsgField("52", "Personal ID Number (PIN) Data"));
			listIsoFields.add(new IsoMsgField("53", "Security-Related Control Information n-16"));
			listIsoFields.add(new IsoMsgField("54", "Additional Amounts an…120; LLLVAR"));
			listIsoFields.add(new IsoMsgField("55", "Integrated Circuit Card (ICC) System-Related Data"));
			listIsoFields.add(new IsoMsgField("56", "Reserved for ISO"));
			listIsoFields.add(new IsoMsgField("60", "Advice Reason Code3"));
			listIsoFields.add(new IsoMsgField("61", "Point-of-Service (POS) Data3"));
			listIsoFields.add(new IsoMsgField("62", "Intermediate Network Facility (INF) Data3"));
			listIsoFields.add(new IsoMsgField("63", "Network Data3"));
			listIsoFields.add(new IsoMsgField("64", "Message Authentication Code (MAC)1"));
			listIsoFields.add(new IsoMsgField("65", "Bit Map, Extended1"));
			listIsoFields.add(new IsoMsgField("66", "Settlement Code1"));
			listIsoFields.add(new IsoMsgField("67", "Extended Payment Code1"));
			listIsoFields.add(new IsoMsgField("90", "Original Data Elements"));
			listIsoFields.add(new IsoMsgField("91", "Issuer File Update Code"));
			listIsoFields.add(new IsoMsgField("92", "File Security Code1"));
			listIsoFields.add(new IsoMsgField("93", "Response Indicator1"));
			listIsoFields.add(new IsoMsgField("94", "Service Indicator"));
			listIsoFields.add(new IsoMsgField("95", "Replacement Amounts"));
			listIsoFields.add(new IsoMsgField("122", "Additional Record Data3"));
			listIsoFields.add(new IsoMsgField("123", "Receipt Free Text"));
			listIsoFields.add(new IsoMsgField("124", "Member-defined Data"));
			listIsoFields.add(new IsoMsgField("125", "New PIN Data"));
		}
		return listIsoFields;
	}

}
