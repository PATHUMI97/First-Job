package com.tutuu.firstjobapp.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepositoty companyRepositoty;

    @Override
    public void createCompany(Company company) {

        companyRepositoty.save(company);
    }

    @Override
    public List<Company> findAll() {

        return companyRepositoty.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {

        return companyRepositoty.findById(id).orElse(null);
    }

    @Override
    public boolean deleteCompanyById(Long id) {

            if (companyRepositoty.existsById(id)){
                companyRepositoty.deleteById(id);
                return true;
            }
            return false;
    }

    @Override
    public boolean updateCompany(Long id, Company updateCompany) {
        Optional<Company> optionalCompany = companyRepositoty.findById(id);
        if (optionalCompany.isPresent()) {
            Company company = optionalCompany.get();
            company.setName(updateCompany.getName());
            company.setDescription(updateCompany.getDescription());
            company.setJobs(updateCompany.getJobs());
            companyRepositoty.save(company);
            return true;
        }
        return false;
    }
}
