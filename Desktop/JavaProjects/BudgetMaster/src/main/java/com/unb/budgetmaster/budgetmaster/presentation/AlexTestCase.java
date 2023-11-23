package com.unb.budgetmaster.budgetmaster.presentation;
import com.unb.budgetmaster.budgetmaster.domain.implementation.TransactionImpl;
import org.junit. Test;

public class AlexTestCase {
    private String successMessage;
    private TransactionImpl transactionImpl;

    @Test
    public void testTransactionImpl() {
        successMessage = "This test was executed successfully!";
        transactionImpl = new TransactionImpl();
        double expectedValue = 3;

        if(transactionImpl.getTransactionDetail(0).getAmount() == expectedValue) {
            System.out.println(successMessage);
        }
    }
}
