package it.istat.sintesi.interpreter.restcontroller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import it.istat.sintesi.interpreter.bean.DataBean;
import it.istat.sintesi.interpreter.bean.DataModelBean;
import it.istat.sintesi.interpreter.domain.wd.WdEdizione;
import it.istat.sintesi.interpreter.services.InterpreterService;
import it.istat.sintesi.interpreter.services.NotificationService;
import it.istat.sintesi.interpreter.smeta.domain.SmFase;

@RequestMapping("/interpreter")
@RestController
public class InterpreterController {

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private MessageSource messages;

	@Autowired
	private InterpreterService interpreterService;

	@GetMapping(value = "/mdatalist/{edizione}/{fase}/**")
	public DataBean vmicrodati(HttpServletRequest request,@PathVariable("edizione") Long edizione,@PathVariable("fase") Long fase) throws Exception {
		notificationService.removeAllMessages();
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

		AntPathMatcher apm = new AntPathMatcher();
		String keyParams = apm.extractPathWithinPattern(bestMatchPattern, path);
		WdEdizione eiwf = new WdEdizione();
		eiwf.setEdizione(edizione);
		
		SmFase smfase=new SmFase(fase);
		DataBean dataBean = interpreterService.getUnitMicroDataList(eiwf,smfase,keyParams);
		return dataBean;
	}

	@GetMapping("/mdata/{edizione}/{fase}/**")
	public DataBean viewQuest(HttpServletRequest request,@PathVariable("edizione") Long edizione,@PathVariable("fase") Long fase) throws Exception {

		notificationService.removeAllMessages();
		String path = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		String bestMatchPattern = (String) request.getAttribute(HandlerMapping.BEST_MATCHING_PATTERN_ATTRIBUTE);

		AntPathMatcher apm = new AntPathMatcher();
		String keyParams = apm.extractPathWithinPattern(bestMatchPattern, path);
		WdEdizione eiwf = new WdEdizione();
		eiwf.setEdizione(edizione);
		SmFase smfase=new SmFase(fase);
		DataBean dataBean = interpreterService.getUnitMicroDataFirst(eiwf,smfase, keyParams);
		return dataBean;
	}
	
	@GetMapping("/dostep/{edizione}/{step}")
	public Object executeStep(HttpServletRequest request,@PathVariable("edizione") Long edizione,@PathVariable("step") Long step) throws Exception {

		notificationService.removeAllMessages();
 	
		Object dmb = interpreterService.doStep(edizione,step);

		return dmb;
	}

}
