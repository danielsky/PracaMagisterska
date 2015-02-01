package pl.skimina;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;


@Controller
public class Main {
	
	private Log log = LogFactory.getLog(Main.class);
	
	private ExecutorService service = Executors.newFixedThreadPool(1);
	
	private HashMap<String, Worker> workersMap = new HashMap<String, Worker>();
	
	
	@RequestMapping(value={"/", "/index.do"})
	public String index(ModelMap map){
		map.put("material_wew", MaterialPlyn.values());
		return "index";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/calculations.do")
	public String calc(ModelMap map, HttpServletRequest request) throws Exception{
		
		ParametrySymulacji ps = null;
		
		try{
			
			ps = new ParametrySymulacji(request);
			
		}catch(NumberFormatException nex){
			log.info("Nieprawidlowa ilosc sekcji", nex);
			throw new Exception("Ilość sekcji nie jest liczbą całkowitą");
		}
		
		String errorMsg = ps.validate();
		if(errorMsg != null) throw new Exception(errorMsg);
		
		ps.configure();
		
		log.info("Ilosc sekcji: "+ps.ilosc_sekcji);
		
		Data data = new Data(ps);
		Gson gson = new Gson();
		String json = gson.toJson(data);
		
		String uuid = UUID.randomUUID().toString();
		
		map.addAttribute("dataset", json);
		map.addAttribute("identifier", uuid);
		
		Worker worker = new Worker(ps);
		
		workersMap.put(uuid, worker);
		service.execute(worker);
		
		return "calculations";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/update.do")
	public @ResponseBody String update(HttpServletRequest request) throws Exception{
		String identifier = request.getParameter("identifier");
		log.info("Identifier: "+identifier);
		if(identifier != null){
			Worker worker = workersMap.get(identifier);
			if(worker != null){
				//log.info("worker not null");
				Gson gson = new Gson();
				return gson.toJson(worker.getCurrentData());
			}
		}
		return "No data";
	}
	
	
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleError(HttpServletRequest req, Exception exception) {

	    ModelAndView mav = new ModelAndView();
	    mav.addObject("errorMsg", exception.getMessage());
	    mav.setViewName("error");
	    return mav;
	  }

}
