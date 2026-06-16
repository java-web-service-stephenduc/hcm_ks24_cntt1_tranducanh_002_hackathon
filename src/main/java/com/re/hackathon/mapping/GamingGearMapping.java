package com.re.hackathon.mapping;

import com.re.hackathon.dto.GamingGearDTO;
import com.re.hackathon.dto.request.GamingGearDtoRequest;
import com.re.hackathon.dto.response.GamingGearDtoResponse;
import com.re.hackathon.entity.GamingGear;

public class GamingGearMapping {

    public static GamingGearDTO toDto(GamingGear entity) {
        if (entity == null) {
            return null;
        }
        GamingGearDTO dto = new GamingGearDTO();
        dto.setId(entity.getId());
        dto.setProductName(entity.getProductName());
        dto.setSerialCode(entity.getSerialCode());
        dto.setPrice(entity.getPrice());
        dto.setType(entity.getType());
        dto.setDelete(entity.isDelete());
        return dto;
    }

    public static GamingGear toEntity(GamingGearDTO dto) {
        if (dto == null) {
            return null;
        }
        GamingGear entity = new GamingGear();
        entity.setId(dto.getId());
        entity.setProductName(dto.getProductName());
        entity.setSerialCode(dto.getSerialCode());
        entity.setPrice(dto.getPrice());
        entity.setType(dto.getType());
        entity.setDelete(dto.isDelete());
        return entity;
    }

    public static GamingGear toEntity(GamingGearDtoRequest request) {
        if (request == null) {
            return null;
        }
        GamingGear entity = new GamingGear();
        entity.setProductName(request.getProductName());
        entity.setSerialCode(request.getSerialCode());
        entity.setPrice(request.getPrice());
        entity.setType(request.getType());
        entity.setDelete(false);
        return entity;
    }

    public static GamingGearDtoResponse toResponse(GamingGear entity) {
        if (entity == null) {
            return null;
        }
        GamingGearDtoResponse response = new GamingGearDtoResponse();
        response.setId(entity.getId());
        response.setProductName(entity.getProductName());
        response.setSerialCode(entity.getSerialCode());
        response.setPrice(entity.getPrice());
        response.setType(entity.getType());
        response.setDelete(entity.isDelete());
        return response;
    }
}
