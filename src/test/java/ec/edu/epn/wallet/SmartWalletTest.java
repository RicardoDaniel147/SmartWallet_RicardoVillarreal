package ec.edu.epn.wallet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/*
    @autor: Ricardo Villarreal
    @fecha: 24/04/2026
*/

public class SmartWalletTest {

    private SmartWallet wallet;

    @BeforeEach
    void setUp() {
        wallet = new SmartWallet("Standard");
    }

    @Test
    void depositoValido() {
        wallet.deposit(10);
        assertEquals(10, wallet.getBalance());
    }

    @Test
    void depositoNegativo() {
        assertEquals(false, wallet.deposit(-10));
    }

    @Test
    void depositoExcedeLimiteStandard() {
        wallet.setBalance(4999);
        assertEquals(false, wallet.deposit(10));
    }

    @Test
    void depositoConCashback() {
        wallet.deposit(200);
        assertEquals(202, wallet.getBalance());
    }

    @Test
    void depositoDe100NoTieneCashback() {
        wallet.deposit(100);
        assertEquals(100, wallet.getBalance());
    }

    @Test
    void retiroValido() {
        wallet.setBalance(100);
        assertEquals(true, wallet.withdraw(50));
    }

    @Test
    void descuentoSaldoAlRetirar() {
        wallet.setBalance(100);
        wallet.withdraw(50);
        assertEquals(50, wallet.getBalance());
    }

    @Test
    void retiroNegativo() {
        assertEquals(false, wallet.withdraw(-10));
    }

    @Test
    void retiroExcedeBalance() {
        wallet.setBalance(100);
        assertEquals(false, wallet.withdraw(150));
    }

    @Test
    void cuentaInactivaAlQuedarseSinSaldo() {
        wallet.setBalance(50);
        wallet.withdraw(50);
        assertFalse(wallet.isActivo());
    }

}
