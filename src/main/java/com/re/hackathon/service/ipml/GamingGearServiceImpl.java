package com.re.hackathon.service.ipml;

import com.re.hackathon.dto.GamingGearDTO;
import com.re.hackathon.entity.GamingGear;
import com.re.hackathon.repository.GamingGearRepository;
import com.re.hackathon.service.GamingGearService;
import com.re.hackathon.exception.NotFoundException;
import com.re.hackathon.exception.ValidateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class GamingGearServiceImpl implements GamingGearService {
    private final GamingGearRepository gamingGearRepository;

    @Override
    public EntityResponse<List<GamingGearDTO>> findAll(){
        if(gamingGearRepository.findAll().isEmpty()) {
            throw new NotFoundException("Gaming Gear Not Found");
        }
        log.info("Gaming gears found");
        return (EntityResponse<List<GamingGearDTO>>) gamingGearRepository.findAll();
    }

    @Override
    public EntityResponse<GamingGearDTO> InsertGamingGear(GamingGearDTO gamingGearDTO){
        if ( gamingGearDTO.getProductName() == null || gamingGearDTO.getSerialCode() == null ){
            throw new ValidateException("du lieu khong duoc de trong");
        }
        if ( gamingGearDTO.getPrice() <= 0){
            throw new ValidateException("gia san pham phai lon hon 0");
        }
        return (EntityResponse<GamingGearDTO>) gamingGearRepository.save(new GamingGear());
    }

    @Override
    public EntityResponse<GamingGearDTO> UpdateEntierGamingGear(GamingGearDTO gamingGearDTO, Long id) {
        return null;
    }

    @Override
    public EntityResponse<GamingGearDTO> UpdateLimitGamingGear(GamingGearDTO gamingGearDTO, Long id) {
        return null;
    }

    @Override
    public EntityResponse<GamingGearDTO> DeleteSoftGamingGear(GamingGearDTO gamingGearDTO, Long id) {
        return null;
    }

    @Override
    public EntityResponse<GamingGearDTO> DeleteHardGamingGear(GamingGearDTO gamingGearDTO, Long id) {
        return null;
    }


}
