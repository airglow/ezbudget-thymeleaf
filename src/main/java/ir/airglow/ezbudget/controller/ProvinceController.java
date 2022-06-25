package ir.airglow.ezbudget.controller;

import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ir.airglow.ezbudget.dto.ProvinceDto;
import ir.airglow.ezbudget.service.ProvinceService;

@Log4j2
@Controller
@RequestMapping("/provinces")
public class ProvinceController {

	private final ProvinceService provinceService;
	
	public ProvinceController(ProvinceService provinceService) {
		this.provinceService = provinceService;
	}
	
    @GetMapping
    public String index(Model model) {
    	
    	List<ProvinceDto> provinces = provinceService.list();
    	
    	model.addAttribute("provinces", provinces);
    	
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model) {

        model.addAttribute("province", new ProvinceDto());

        return "create";
    }

    @PostMapping
    public String store(@ModelAttribute(name = "province") ProvinceDto provinceDto) {

        Long provinceId = provinceService.create(provinceDto);

        log.info("insert new province: " + provinceId);

        return "redirect:/provinces/create";
    }

}
