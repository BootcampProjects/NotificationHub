package org.kodluyoruz.trendyol.models.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BlackListCompanyDTO {
    private int companyId;
    private String companyName;
}
