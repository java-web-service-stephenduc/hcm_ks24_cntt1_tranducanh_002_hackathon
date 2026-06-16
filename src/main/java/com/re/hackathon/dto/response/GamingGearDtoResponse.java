package com.re.hackathon.dto.response;

import com.re.hackathon.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GamingGearDtoResponse {
    private long id;
    private String productName;
    private String serialCode;
    private Double price;
    private Type type;
    private boolean isDelete;
}
