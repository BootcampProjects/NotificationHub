package org.kodluyoruz.trendyol.models;

import org.kodluyoruz.trendyol.models.dtos.BlackListCompanyDTO;

import java.util.ArrayList;
import java.util.List;

public class BlackList {

    private static BlackList blackList_instance = null;


    private List<BlackListCompanyDTO> blackListCompanies;

    private BlackList() {
        blackListCompanies = new ArrayList<BlackListCompanyDTO>();
    }

    public static BlackList getInstance() {
        if (blackList_instance == null)
            blackList_instance = new BlackList();

        return blackList_instance;
    }

    public List<BlackListCompanyDTO> getBlackListCompanies() {
        return blackListCompanies;
    }

    public void setBlackListCompanies(List<BlackListCompanyDTO> blackListCompanies) {
        this.blackListCompanies = blackListCompanies;
    }

    public void addCompany(BlackListCompanyDTO blackListCompany) {
        this.blackListCompanies.add(blackListCompany);
    }
}
