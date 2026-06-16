package com.re.hackathon.controller;

import com.re.hackathon.dto.GamingGearDTO;
import com.re.hackathon.service.GamingGearService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.function.EntityResponse;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/gears")
public class GamingGearController {
    private final GamingGearService gamingGearService;

    // lấy danh sách
    @GetMapping
    public EntityResponse<List<GamingGearDTO>> findAll() {
        return gamingGearService.findAll();
    }

    // thêm mới gamingGear validate
    @PostMapping("/add")
    public EntityResponse<GamingGearDTO> CreateNewGamingGear(@Valid @RequestBody GamingGearDTO gamingGearDTO) {
        return gamingGearService.InsertGamingGear(gamingGearDTO);
    }

    //cập nhật tất cả thông tin của 1 thiết bị + validate
    @PatchMapping("/update/{id}")
    public EntityResponse<GamingGearDTO> UpdateEntierGamingGear(@Valid @RequestBody GamingGearDTO gamingGearDTO, @PathVariable Long id) {
        return gamingGearService.UpdateEntierGamingGear(gamingGearDTO,id);
    }

    // cập nhật 1 phần thông tin của 1 thết bị + validate
    @PutMapping("/update/{id}")
    public EntityResponse<GamingGearDTO> UpdatePriceGamingGear(@RequestBody GamingGearDTO gamingGearDTO, @PathVariable Long id) {
        return gamingGearService.UpdatePriceGamingGear(gamingGearDTO,id);
    }

    // xóa thiết bị (soft delete & hard delete)
    @DeleteMapping("delete/{id}")
    public EntityResponse<GamingGearDTO> DeleteSoftGamingGear (@RequestBody GamingGearDTO gamingGearDTO, @PathVariable Long id) {
        return gamingGearService.DeleteSoftGamingGear(gamingGearDTO,id);
    }

    @DeleteMapping("delete/{id}")
    public EntityResponse<GamingGearDTO> DeleteHardGamingGear (@RequestBody GamingGearDTO gamingGearDTO, @PathVariable Long id) {
        return gamingGearService.DeleteHardGamingGear(gamingGearDTO,id);
    }

    // tìm kiếm thiết bị theo tên, sreial_code.
    // phân trang -> tích hợp vào lấy danh sách sp.
}
