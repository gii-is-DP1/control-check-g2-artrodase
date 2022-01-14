package org.springframework.samples.petclinic.feeding;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/feeding")
public class FeedingController {
    

	
	private FeedingService feedService;
	
	private PetService petServ;
	
	
	
	@Autowired
	public FeedingController(FeedingService feedService, PetService petServ) {
		this.feedService = feedService;
		this.petServ = petServ;
	}


	@GetMapping(value = "/create")
	public String createFeeding(Map<String, Object> model) {
		Feeding feed = new Feeding();
		model.put("feeding", feed);
		List<FeedingType> feedType = feedService.getAllFeedingTypes();
		model.put("feedingTypes", feedType);
		Collection<PetType> petTypes = petServ.findPetTypes();
		model.put("pets", petTypes);
		return "feedings/createOrUpdateFeedingForm";
	}

	
	@PostMapping(value = "/create")
	public String saveFeeding( RedirectAttributes rd, @Valid Feeding feeding, BindingResult result, ModelMap model) throws UnfeasibleFeedingException {
		if (result.hasErrors()) {
			model.put("message", "“La mascota seleccionada no se le puede asignar el plan de\r\n"
					+ "alimentación especificado");
			System.out.println(result);
			List<FeedingType> feedType = feedService.getAllFeedingTypes();
			model.put("feedingTypes", feedType);
			Collection<PetType> petTypes = petServ.findPetTypes();
			model.put("pets", petTypes);
			return "feedings/createOrUpdateFeedingForm";
		}else {
			feedService.save(feeding);
			return "redirect:/welcome";
		}
		 
		
	}
}
