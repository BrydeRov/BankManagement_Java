import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client {
    public int id;
    public String nombre;
    private String password;
    public List<CuentaBancaria> cuentas;

    public Client(String nombre, String password){
        this.nombre = nombre;
        this.password = password;
        this.cuentas = new ArrayList<>();
    }

    public void agregarCuenta(CuentaBancaria cuenta){
        cuentas.add(cuenta);
    }

    public List<CuentaBancaria> getCuentas() {
        return cuentas;
    }
}
