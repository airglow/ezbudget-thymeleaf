package ir.airglow.ezbudget.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

import ir.airglow.ezbudget.dto.ProvinceDto;
import ir.airglow.ezbudget.entity.Province;
import ir.airglow.ezbudget.repository.ProvinceRepository;

@Service
public class ProvinceService {
	
	private final ProvinceRepository provinceRepository;
	
	public ProvinceService(ProvinceRepository provinceRepository) {
		this.provinceRepository = provinceRepository;
	}
	
	public List<ProvinceDto> list() {
		
		Iterable<Province> provincesIterable = provinceRepository.findAll();
		
		List<ProvinceDto> provinces = new ArrayList<>();
		
		provincesIterable.forEach(p -> provinces.add(ProvinceDto.entityToDto(p)));
		
		return provinces;
	}

	public Long create(ProvinceDto provinceDto) {

		Province province = ProvinceDto.dtoToEntity(provinceDto);

		provinceRepository.save(province);

		return province.getId();
	}
	
	public ProvinceDto findProvinceById(Long id) {
		
		Optional<Province> province = provinceRepository.findById(id);
		
		if (province.isEmpty()) {
			throw new EntityNotFoundException();
		}
		
		return ProvinceDto.entityToDto(province.get());
	}
	
	public Long update(Long id, ProvinceDto provinceDto) {
		
		Optional<Province> CurrentProvince = provinceRepository.findById(id);
		
		if (CurrentProvince.isEmpty()) {
			throw new EntityNotFoundException();
		}
		
		Province province = ProvinceDto.dtoToEntity(provinceDto);
		
		province.setId(id);
		
		provinceRepository.save(province);
		
		return province.getId();
	}
	
}
