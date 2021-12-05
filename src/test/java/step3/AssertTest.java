package step3;

import javax.naming.InsufficientResourcesException;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AssertTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    Account account;

    @BeforeEach
    void init() {
        account = new Account("an account Name");
    }

    @Test
    void depositIncreasesBalance() {
        int initialBalance = account.getBalance();
        account.deposit(100);
        assertTrue(account.getBalance() > initialBalance);
    }

    @Test
    void hasPositiveBalance() {
        account.deposit(50);
        assertTrue(account.hasPositiveBalance());
    }

    @Test
    void testWithWorthlessAssertionComment() {
        account.deposit(50);
        assertEquals(account.getBalance(), 50, "account balance is 100");
    }

    @Test
    void throwsWhenWithdrawingTooMuch() {
        Exception actualException = assertThrows(InsufficientResourcesException.class,
            () -> account.withdraw(100));

        assertEquals("Not enough Balance", actualException.getMessage());
    }


    @Test
    void expectedRule() throws InsufficientResourcesException {
        thrown.expect(InsufficientResourcesException.class);
        thrown.expectMessage("Not enough Balance");

        account.withdraw(100);
    }

    @Test
    void readsFromTestFile() throws IOException {
        String fileName = "test.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write("test data");
        writer.close();
    }
}
