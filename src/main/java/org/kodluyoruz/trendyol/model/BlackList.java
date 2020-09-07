package org.kodluyoruz.trendyol.model;

import java.util.List;

public class BlackList {
    private List<Company> companies;

    public List<Company> getCompanies() {
        return companies;
    }

    public void addCompany(Company company) {
        this.companies.add(company);
    }
}
