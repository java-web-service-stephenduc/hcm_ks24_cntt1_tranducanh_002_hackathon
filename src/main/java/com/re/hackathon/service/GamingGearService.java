package com.re.hackathon.service;

import com.re.hackathon.dto.GamingGearDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GamingGearService {
    ResponseEntity<List<GamingGearDTO>> findAll(String productName, String serialCode, Integer page, Integer size);

    ResponseEntity<GamingGearDTO> InsertGamingGear(GamingGearDTO gamingGearDTO);

    ResponseEntity<GamingGearDTO> UpdateEntierGamingGear(GamingGearDTO gamingGearDTO, Long id);

    ResponseEntity<GamingGearDTO> UpdatePriceGamingGear(GamingGearDTO gamingGearDTO, Long id);

    ResponseEntity<GamingGearDTO> DeleteSoftGamingGear(GamingGearDTO gamingGearDTO, Long id);

    ResponseEntity<GamingGearDTO> DeleteHardGamingGear(GamingGearDTO gamingGearDTO, Long id);
}
