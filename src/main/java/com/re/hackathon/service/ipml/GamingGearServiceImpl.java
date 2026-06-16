package com.re.hackathon.service.ipml;

import com.re.hackathon.dto.GamingGearDTO;
import com.re.hackathon.entity.GamingGear;
import com.re.hackathon.repository.GamingGearRepository;
import com.re.hackathon.service.GamingGearService;
import com.re.hackathon.exception.NotFoundException;
import com.re.hackathon.exception.ValidateException;
import com.re.hackathon.mapping.GamingGearMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class GamingGearServiceImpl implements GamingGearService {
    private final GamingGearRepository gamingGearRepository;

    // lay danh sach gear
    @Override
    public ResponseEntity<List<GamingGearDTO>> findAll(String productName, String serialCode, Integer page, Integer size) {
        int pageNo = (page == null) ? 0 : page;
        int pageSize = (size == null) ? 10 : size;
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        Page<GamingGear> gearPage;
        if (productName != null || serialCode != null) {
            gearPage = gamingGearRepository.searchActive(productName, serialCode, pageable);
        } else {
            gearPage = gamingGearRepository.findByIsDeleteFalse(pageable);
        }

        if (gearPage.isEmpty()) {
            throw new NotFoundException("Gaming Gear Not Found");
        }

        List<GamingGearDTO> dtoList = gearPage.getContent().stream()
                .map(GamingGearMapping::toDto)
                .collect(Collectors.toList());

        log.info("Gaming gears found");
        return ResponseEntity.ok(dtoList);
    }

    // them moi gear
    @Override
    @Transactional
    public ResponseEntity<GamingGearDTO> InsertGamingGear(GamingGearDTO gamingGearDTO) {
        if (gamingGearDTO.getProductName() == null || gamingGearDTO.getProductName().trim().isEmpty()) {
            throw new ValidateException("ten san pham khong duoc de trong");
        }
        if (gamingGearDTO.getSerialCode() == null || gamingGearDTO.getSerialCode().trim().isEmpty()) {
            throw new ValidateException("serial-code san pham khong duoc de trong");
        }
        if (gamingGearDTO.getPrice() == null || gamingGearDTO.getPrice() <= 0) {
            throw new ValidateException("gia san pham phai lon hon 0");
        }

        GamingGear entity = GamingGearMapping.toEntity(gamingGearDTO);
        entity.setDelete(false);
        GamingGear saved = gamingGearRepository.save(entity);
        log.info("Gaming gear inserted successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(GamingGearMapping.toDto(saved));
    }

    // sua toan bo gear
    @Override
    @Transactional
    public ResponseEntity<GamingGearDTO> UpdateEntierGamingGear(GamingGearDTO gamingGearDTO, Long id) {
        GamingGear entity = gamingGearRepository.findById(id)
                .filter(g -> !g.isDelete())
                .orElseThrow(() -> new NotFoundException("san pham khong ton tai"));

        if (gamingGearDTO.getProductName() == null || gamingGearDTO.getProductName().trim().isEmpty()) {
            throw new ValidateException("ten san pham khong duoc de trong");
        }
        if (gamingGearDTO.getSerialCode() == null || gamingGearDTO.getSerialCode().trim().isEmpty()) {
            throw new ValidateException("serial-code san pham khong duoc de trong");
        }
        if (gamingGearDTO.getPrice() == null || gamingGearDTO.getPrice() <= 0) {
            throw new ValidateException("gia san pham phai lon hon 0");
        }
        if (gamingGearDTO.getType() == null) {
            throw new ValidateException("loai thiet bi khong duoc de trong");
        }

        entity.setProductName(gamingGearDTO.getProductName());
        entity.setSerialCode(gamingGearDTO.getSerialCode());
        entity.setPrice(gamingGearDTO.getPrice());
        entity.setType(gamingGearDTO.getType());

        GamingGear saved = gamingGearRepository.save(entity);
        log.info("Gaming gear fully updated");
        return ResponseEntity.ok(GamingGearMapping.toDto(saved));
    }

    // sua gear 1 phan
    @Override
    @Transactional
    public ResponseEntity<GamingGearDTO> UpdatePriceGamingGear(GamingGearDTO gamingGearDTO, Long id) {
        GamingGear entity = gamingGearRepository.findById(id)
                .filter(g -> !g.isDelete())
                .orElseThrow(() -> new NotFoundException("san pham khong ton tai"));

        if (gamingGearDTO.getProductName() != null) {
            if (gamingGearDTO.getProductName().trim().isEmpty()) {
                throw new ValidateException("ten san pham khong duoc de trong");
            }
            entity.setProductName(gamingGearDTO.getProductName());
        }
        if (gamingGearDTO.getSerialCode() != null) {
            if (gamingGearDTO.getSerialCode().trim().isEmpty()) {
                throw new ValidateException("serial-code san pham khong duoc de trong");
            }
            entity.setSerialCode(gamingGearDTO.getSerialCode());
        }
        if (gamingGearDTO.getPrice() != null) {
            if (gamingGearDTO.getPrice() <= 0) {
                throw new ValidateException("gia san pham phai lon hon 0");
            }
            entity.setPrice(gamingGearDTO.getPrice());
        }
        if (gamingGearDTO.getType() != null) {
            entity.setType(gamingGearDTO.getType());
        }

        GamingGear saved = gamingGearRepository.save(entity);
        log.info("Gaming gear partially updated");
        return ResponseEntity.ok(GamingGearMapping.toDto(saved));
    }

    // xoa gear soft
    @Override
    @Transactional
    public ResponseEntity<GamingGearDTO> DeleteSoftGamingGear(GamingGearDTO gamingGearDTO, Long id) {
        GamingGear entity = gamingGearRepository.findById(id)
                .filter(g -> !g.isDelete())
                .orElseThrow(() -> new NotFoundException("san pham khong ton tai"));

        entity.setDelete(true);
        GamingGear saved = gamingGearRepository.save(entity);
        log.info("Gaming gear soft deleted");
        return ResponseEntity.ok(GamingGearMapping.toDto(saved));
    }

    // xoa gear hard
    @Override
    @Transactional
    public ResponseEntity<GamingGearDTO> DeleteHardGamingGear(GamingGearDTO gamingGearDTO, Long id) {
        GamingGear entity = gamingGearRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("san pham khong ton tai"));

        gamingGearRepository.delete(entity);
        log.info("Gaming gear hard deleted");
        return ResponseEntity.ok(GamingGearMapping.toDto(entity));
    }
}
