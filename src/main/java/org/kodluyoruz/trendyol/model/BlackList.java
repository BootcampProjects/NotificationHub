package org.kodluyoruz.trendyol.model;

import org.kodluyoruz.trendyol.model.dto.BlackListCompanyDTO;

import java.util.ArrayList;
import java.util.List;

public class BlackList {
    private List<BlackListCompanyDTO> blackListCompanies;

    public BlackList() {
        blackListCompanies = new ArrayList<BlackListCompanyDTO>();
    }

    public List<BlackListCompanyDTO> getCompanies() {
        return blackListCompanies;
    }

    public void addCompany(BlackListCompanyDTO blackListCompany) {
        this.blackListCompanies.add(blackListCompany);
    }
}
