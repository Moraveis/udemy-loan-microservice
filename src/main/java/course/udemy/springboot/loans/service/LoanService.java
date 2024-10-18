package course.udemy.springboot.loans.service;

import course.udemy.springboot.loans.dto.LoanDto;

public interface LoanService {

    void createLoan(String mobileNumber);

    LoanDto fetchLoan(String mobileNumber);

    boolean updateLoan(LoanDto loansDto);

    boolean deleteLoan(String mobileNumber);

}
