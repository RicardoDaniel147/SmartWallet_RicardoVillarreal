package ec.edu.epn.wallet;

/*
    @autor: Ricardo Villarreal
    @fecha: 24/04/2026
*/

public class SmartWallet {
    private double balance;
    private String tipoUsuario; // "Premium" o "Standard"
    private boolean activo;

    public SmartWallet(String tipoUsuario) {
        this.balance = 0;
        this.tipoUsuario = tipoUsuario;
        this.activo = true;
    }

    public double getBalance() {
        return balance;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public boolean deposit(double amount) {

        // Monto mayor a 0
        if (amount <= 0) {
            return false;
        }

        // Usuarios Standard no pueden superar los $5000 en su saldo
        if (tipoUsuario.equals("Standard") && (balance + amount) > 5000) {
            return false;
        }

        // Usuario debe recibir un cashback del 1% si su monto es mayor a $100
        if (amount > 100) {
            double cashback = amount * 0.01;
            balance += amount + cashback;
        } else {
            balance += amount;
        }
        return true;
    }

    public boolean withdraw(double amount) {

        // No se pueden retirar montos negativos
        if (amount <= 0) {
            return false;
        }

        // No se pueden retirar más de lo que se tiene en el balance
        if (amount > balance) {
            return false;
        }

        balance -= amount;

        // Si el saldo es cero, la cuenta se desactiva
        if (balance == 0) {
            activo = false;
        }

        return true;
    }

}
