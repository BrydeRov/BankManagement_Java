
import java.util.NoSuchElementException;
import java.util.Scanner;
import javax.swing.*;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static Client cliente;
    public static void agregarCuentas() {
        System.out.print("¿Cuantas cuentas deseas agregar? ");
        Scanner inputNoCuentas = new Scanner(System.in);
        int numeroCuentas = inputNoCuentas.nextInt();

        CuentaBancaria[] cuentas = new CuentaBancaria[numeroCuentas];
        Scanner[] inputCuentas = new Scanner[numeroCuentas];

        for (int i = 0; i < numeroCuentas; i++) {
            inputCuentas[i] = new Scanner(System.in);
            String nombreCuenta = "";

            while (nombreCuenta.isEmpty()) {
                try {
                    System.out.print("Ingresa el nombre de la cuenta " + (i + 1) + ": ");
                    nombreCuenta = inputCuentas[i].nextLine();
                } catch (NoSuchElementException e) {
                    System.out.println("Se requiere una entrada válida.");
                    inputCuentas[i].next(); // Limpiar el buffer
                }
            }

            double saldoCuenta = -1;
            while (saldoCuenta < 0) {
                try {
                    System.out.print("Ingresa el saldo de la cuenta " + (i + 1) + ": ");
                    saldoCuenta = inputCuentas[i].nextDouble();
                    if (saldoCuenta < 0) {
                        System.out.println("El saldo debe ser un valor no negativo.");
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("Se requiere un valor numérico.");
                    inputCuentas[i].next(); // Limpiar el buffer
                }
            }

            cuentas[i] = new CuentaBancaria(nombreCuenta, saldoCuenta);
            cliente.agregarCuenta(cuentas[i]);
        }
    }

    public static void main(String[] args) {

        // Ingresar nombre
        System.out.print("Ingresa tu nombre para crear tu cuenta: ");
        Scanner inputNombre = new Scanner(System.in);
        String nombreCliente = inputNombre.nextLine();
        // Ingresar password
        System.out.print("Ingresa tu contraseña para crear tu cuenta: ");
        Scanner inputPassword = new Scanner(System.in);
        String passwordCliente = inputPassword.nextLine();
        cliente = new Client(nombreCliente, passwordCliente);

        if(cliente != null){
            System.out.print("wenas");
        }
//         agregarCuentas();
    }
}