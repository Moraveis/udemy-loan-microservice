package course.udemy.springboot.loans.service;

import course.udemy.springboot.loans.constants.LoanConstants;
import course.udemy.springboot.loans.dto.LoanDto;
import course.udemy.springboot.loans.entity.Loan;
import course.udemy.springboot.loans.exception.LoanAlreadyExistsException;
import course.udemy.springboot.loans.exception.ResourceNotFoundException;
import course.udemy.springboot.loans.mapper.LoanMapper;
import course.udemy.springboot.loans.repository.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;

    @Override
    public void createLoan(String mobileNumber) {
        Optional<Loan> optionalLoan = loanRepository.findByMobileNumber(mobileNumber);
        if (optionalLoan.isPresent()) {
            throw new LoanAlreadyExistsException("Loan already registered with given mobileNumber " + mobileNumber);
        }
        loanRepository.save(createNewLoan(mobileNumber));
    }

    private Loan createNewLoan(String mobileNumber) {
        Loan newLoan = new Loan();
        long randomLoanNumber = 100000000000L + new Random().nextInt(900000000);
        newLoan.setLoanNumber(Long.toString(randomLoanNumber));
        newLoan.setMobileNumber(mobileNumber);
        newLoan.setLoanType(LoanConstants.HOME_LOAN);
        newLoan.setTotalLoan(LoanConstants.NEW_LOAN_LIMIT);
        newLoan.setAmountPaid(0);
        newLoan.setOutstandingAmount(LoanConstants.NEW_LOAN_LIMIT);
        return newLoan;
    }

    @Override
    public LoanDto fetchLoan(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
        return LoanMapper.mapToLoanDto(loan, new LoanDto());
    }

    @Override
    public boolean updateLoan(LoanDto loanDto) {
        Loan loans = loanRepository.findByLoanNumber(loanDto.getLoanNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "LoanNumber", loanDto.getLoanNumber()));
        LoanMapper.mapToLoans(loanDto, loans);
        loanRepository.save(loans);
        return true;
    }

    @Override
    public boolean deleteLoan(String mobileNumber) {
        Loan loans = loanRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Loan", "mobileNumber", mobileNumber));
        loanRepository.deleteById(loans.getLoanId());
        return true;
    }
}
