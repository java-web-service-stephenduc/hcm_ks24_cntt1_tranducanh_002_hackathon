package com.re.hackathon.dto;

import com.re.hackathon.entity.Type;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GamingGearDTO {
    private long id;

    @NotBlank(message = "ten san pham khong duoc de trong")
    private String productName;

    @NotBlank(message = "serial-code san pham khong duoc de trong")
    private String serialCode;

    @Min(value = 1, message = "gia phai lon hom khong")
    private Double price;

    private Type type;
    private boolean isDelete;
}
