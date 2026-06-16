package com.re.hackathon.controller;

import com.re.hackathon.dto.GamingGearDTO;
import com.re.hackathon.service.GamingGearService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/gears")
public class GamingGearController {
    private final GamingGearService gamingGearService;

    // lay danh sach gear
    @GetMapping
    public ResponseEntity<List<GamingGearDTO>> findAll(
            @RequestParam(required = false) String productName,
            @RequestParam(required = false) String serialCode,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size) {
        return gamingGearService.findAll(productName, serialCode, page, size);
    }

    // them moi gear
    @PostMapping("/add")
    public ResponseEntity<GamingGearDTO> CreateNewGamingGear(@Valid @RequestBody GamingGearDTO gamingGearDTO) {
        return gamingGearService.InsertGamingGear(gamingGearDTO);
    }

    // sua toan bo gear
    @PatchMapping("/update/{id}")
    public ResponseEntity<GamingGearDTO> UpdateEntierGamingGear(@Valid @RequestBody GamingGearDTO gamingGearDTO, @PathVariable Long id) {
        return gamingGearService.UpdateEntierGamingGear(gamingGearDTO, id);
    }

    // sua gear 1 phan
    @PutMapping("/update/{id}")
    public ResponseEntity<GamingGearDTO> UpdatePriceGamingGear(@RequestBody GamingGearDTO gamingGearDTO, @PathVariable Long id) {
        return gamingGearService.UpdatePriceGamingGear(gamingGearDTO, id);
    }

    // xoa gear soft
    @DeleteMapping("/soft/{id}")
    public ResponseEntity<GamingGearDTO> DeleteSoftGamingGear(@RequestBody(required = false) GamingGearDTO gamingGearDTO, @PathVariable Long id) {
        return gamingGearService.DeleteSoftGamingGear(gamingGearDTO, id);
    }

    // xoa gear hard
    @DeleteMapping("/hard/{id}")
    public ResponseEntity<GamingGearDTO> DeleteHardGamingGear(@RequestBody(required = false) GamingGearDTO gamingGearDTO, @PathVariable Long id) {
        return gamingGearService.DeleteHardGamingGear(gamingGearDTO, id);
    }
}
