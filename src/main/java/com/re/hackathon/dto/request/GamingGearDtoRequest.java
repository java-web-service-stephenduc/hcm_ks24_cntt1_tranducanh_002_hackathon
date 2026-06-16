package com.re.hackathon.dto.request;

import com.re.hackathon.entity.Type;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GamingGearDtoRequest {
    @NotBlank(message = "ten san pham khong duoc de trong")
    private String productName;

    @NotBlank(message = "serial-code san pham khong duoc de trong")
    private String serialCode;

    @Min(value = 1, message = "gia phai lon hom khong")
    private Double price;

    private Type type;
}
