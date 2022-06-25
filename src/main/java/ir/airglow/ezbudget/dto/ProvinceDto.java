package ir.airglow.ezbudget.dto;

import ir.airglow.ezbudget.entity.Province;
import lombok.Data;

@Data
public class ProvinceDto {
	
	private String code;
	private String name;
	
	public static ProvinceDto entityToDto(Province province) {
		
		ProvinceDto provinceDto = new ProvinceDto();
		provinceDto.setCode(province.getCode());
		provinceDto.setName(province.getName());
		
		return provinceDto;
	}
	
	public static Province dtoToEntity(ProvinceDto provinceDto) {
		
		Province province = new Province();
		province.setCode(provinceDto.getCode());
		province.setName(provinceDto.getName());
		
		return province;
		
	}

}
