package com.tutuu.firstjobapp.company;

import java.util.List;

public interface CompanyService {
    void createCompany(Company company);

    List<Company> findAll();

    Company getCompanyById(Long id);
    boolean deleteCompanyById(Long id);
    boolean updateCompany(Long id, Company updateCompany);

}
