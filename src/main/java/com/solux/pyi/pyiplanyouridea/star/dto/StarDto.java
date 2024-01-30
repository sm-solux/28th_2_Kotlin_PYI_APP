package com.solux.pyi.pyiplanyouridea.star.dto;

import com.solux.pyi.pyiplanyouridea.star.domain.Star;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StarDto {

    private Long starUuid;
    private BigDecimal starDetails;

    public StarDto(Star entity) {
        this.starUuid = entity.getStarUuid();
        this.starDetails = entity.getStarDetails();
    }

}
