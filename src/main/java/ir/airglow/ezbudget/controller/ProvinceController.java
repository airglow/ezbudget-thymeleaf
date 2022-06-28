package ir.airglow.ezbudget.controller;

import lombok.extern.log4j.Log4j2;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public String store(@ModelAttribute(name = "province") ProvinceDto province) {

        Long provinceId = provinceService.create(province);

        log.info("Insert new province: " + provinceId);

        return "redirect:/provinces/create";
    }
    
    @GetMapping("/{id}")
    public String show(Model model, @PathVariable("id") Long id) {
    	
    	ProvinceDto provinceDto = provinceService.findProvinceById(id);
    	
    	model.addAttribute("province", provinceDto);
    	
    	return "show";
    }
    
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
    	
    	ProvinceDto province = provinceService.findProvinceById(id);
    	
    	model.addAttribute("provinceId", id);
    	model.addAttribute("province", province);

    	return "edit";
    }
    
    @PutMapping("/{id}")
    public String update(@PathVariable("id") Long id, 
    		@ModelAttribute(name = "province") ProvinceDto province) {
    	
    	provinceService.update(id, province);
    	
    	log.info("Update province: " + id);
    	
    	return "redirect:/provinces";
    }

}
