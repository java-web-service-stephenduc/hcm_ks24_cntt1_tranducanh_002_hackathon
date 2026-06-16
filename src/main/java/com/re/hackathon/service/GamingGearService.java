package com.re.hackathon.service;

import com.re.hackathon.dto.GamingGearDTO;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

public interface GamingGearService {
    EntityResponse<List<GamingGearDTO>> findAll();

    EntityResponse<GamingGearDTO> InsertGamingGear(GamingGearDTO gamingGearDTO);

    EntityResponse<GamingGearDTO> UpdateEntierGamingGear(GamingGearDTO gamingGearDTO, Long id);

    EntityResponse<GamingGearDTO> UpdateLimitGamingGear(GamingGearDTO gamingGearDTO, Long id);

    EntityResponse<GamingGearDTO> DeleteSoftGamingGear(GamingGearDTO gamingGearDTO, Long id);

    EntityResponse<GamingGearDTO> DeleteHardGamingGear(GamingGearDTO gamingGearDTO, Long id);
}
