public class CuentaBancaria {
    public String cuenta;
    public double saldo;

    public CuentaBancaria(String cuenta, double saldoInicial){
        this.cuenta = cuenta;
        this.saldo = saldoInicial;
    }

    public String getNumeroCuenta() {
        return cuenta;
    }
    public void depositar(double cantidad){
        saldo += cantidad;
    }

    public void retirar(double cantidad){
        if(cantidad <= saldo){
            saldo -= cantidad;
        }else{
            System.out.println("Saldo Insuficiente");
        }
    }

    public Double obtenerSaldo(){
        return saldo;
    }
}
