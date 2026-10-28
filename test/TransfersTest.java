import org.junit.jupiter.api.*;
import org.junit.platform.commons.annotation.Testable;

import static org.junit.jupiter.api.Assertions.*;
import java.time.Duration;

// section 4.2: your test goes here
class TransfersTest {

    @Test
    void testTimeout() throws Exception {
        
        Account alice = new Account("alice", 1000);
        Account bob = new Account("bob", 1000);
        
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Thread t1 = new Thread(() -> Transfers.transfer(alice, bob, 100));
            Thread t2 = new Thread(() -> Transfers.transfer(bob, alice, 100));

            t1.start();
            t2.start();

            t1.join();
            t2.join();
        });

        assertEquals(2000, alice.getBalance() + bob.getBalance());
    }

}
