package course.udemy.springboot.loans.mapper;

import course.udemy.springboot.loans.dto.LoanDto;
import course.udemy.springboot.loans.entity.Loan;

public class LoanMapper {

    public static LoanDto mapToLoanDto(Loan loans, LoanDto LoanDto) {
        LoanDto.setLoanNumber(loans.getLoanNumber());
        LoanDto.setLoanType(loans.getLoanType());
        LoanDto.setMobileNumber(loans.getMobileNumber());
        LoanDto.setTotalLoan(loans.getTotalLoan());
        LoanDto.setAmountPaid(loans.getAmountPaid());
        LoanDto.setOutstandingAmount(loans.getOutstandingAmount());
        return LoanDto;
    }

    public static Loan mapToLoans(LoanDto LoanDto, Loan loans) {
        loans.setLoanNumber(LoanDto.getLoanNumber());
        loans.setLoanType(LoanDto.getLoanType());
        loans.setMobileNumber(LoanDto.getMobileNumber());
        loans.setTotalLoan(LoanDto.getTotalLoan());
        loans.setAmountPaid(LoanDto.getAmountPaid());
        loans.setOutstandingAmount(LoanDto.getOutstandingAmount());
        return loans;
    }
}
