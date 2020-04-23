package com.elcom.business.aggregate;

import java.util.List;

import com.elcom.data.interview.UnitOfWorkInterview;
import com.elcom.data.interview.entity.Company;
import com.elcom.data.interview.entity.dto.CompanyDataPaging;
import com.elcom.data.interview.entity.dto.CompanyLessDTO;
import com.elcom.model.dto.CompanyUpsertDTO;
import com.elcom.model.dto.CompanytDetailsDTO;

public class CompanyAggregate {

    private UnitOfWorkInterview _uokInterview = null;

    public CompanyAggregate(UnitOfWorkInterview _uokEp) {
        this._uokInterview = _uokEp;
    }

    public CompanyDataPaging findAll(String name, String email, Integer status, int page, int rowsPerPage) throws Exception {
		
		return this._uokInterview.companyRepository().findAll(name, email, status, page, rowsPerPage);
	}
    
    public CompanytDetailsDTO findDetails(Long id) throws Exception {

        return this._uokInterview.companyRepository().findDetails(id);
    }

    public void insert(CompanyUpsertDTO item) {

        this._uokInterview.companyRepository().insert(item);
    }

    public void update(CompanyUpsertDTO item) {

        this._uokInterview.companyRepository().update(item);
    }

    public List<CompanyLessDTO> loadFromLetter(Long userTo) throws Exception {
        return this._uokInterview.companyRepository().loadFromLetter(userTo);
    }
    
    public boolean update(Company item) {
        return this._uokInterview.companyRepository().update(item);
    }
}
