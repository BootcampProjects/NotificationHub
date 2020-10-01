package org.kodluyoruz.trendyol.models;

import lombok.Getter;
import lombok.Setter;
import org.kodluyoruz.trendyol.models.dtos.BlackListCompanyDTO;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BlackList {

    private static BlackList blackList_instance = null;

    private List<BlackListCompanyDTO> blackListCompanies;

    private BlackList() {
        blackListCompanies = new ArrayList<>();
    }

    public static BlackList getInstance() {
        if (blackList_instance == null)
            blackList_instance = new BlackList();

        return blackList_instance;
    }

    public void addCompany(BlackListCompanyDTO blackListCompany) {
        this.blackListCompanies.add(blackListCompany);
    }
}
