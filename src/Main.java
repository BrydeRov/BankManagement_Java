
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.text.NumberFormat;
import java.util.Locale;

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

    public static void  backOrOut(){
        Scanner inputContinuar = new Scanner(System.in);
        System.out.print("\n\n¿Desea regresar al menu? (Y/N): ");
        String continuar = inputContinuar.nextLine();

        if(continuar.equals("Y")){
            header();
        }else {
            System.exit(0);
        }
    }

    public static void header(){
        System.out.println("\t\t\tBank Management");
        System.out.println("=========================================\n");
    }

    public static CuentaBancaria showCuentas(){
        Scanner selectCuenta = new Scanner(System.in);
        System.out.println("Escoger cuenta");
        for (int i = 0; i < cliente.cuentas.size(); i++) {
            CuentaBancaria cuenta = cliente.cuentas.get(i);
            System.out.println((i + 1) + ". " + cuenta.getNumeroCuenta());
        }
        System.out.print(": ");
        int noCuenta = selectCuenta.nextInt();
        return cliente.cuentas.get(noCuenta - 1);
    }

    public static void main(String[] args) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);

        header();
        // Ingresar nombre
        System.out.print("Ingresa tu nombre para crear tu cuenta: ");
        Scanner inputNombre = new Scanner(System.in);
        String nombreCliente = inputNombre.nextLine();
        // Ingresar password
        System.out.print("Ingresa tu contraseña para crear tu cuenta: ");
        Scanner inputPassword = new Scanner(System.in);
        String passwordCliente = inputPassword.nextLine();
        cliente = new Client(nombreCliente, passwordCliente);

        Boolean loggedIn = true;

        do{
            while(cliente.cuentas.toArray().length == 0){
                header();
                System.out.print("\n\nCuentas con " + cliente.cuentas.toArray().length + " cuentas bancarias.");
                System.out.print("\n¿Deseas agregar cuentas? (Y/N): ");
                Scanner addAcc = new Scanner(System.in);
                String wantsToAddAcc = addAcc.nextLine();

                if(wantsToAddAcc.equals("Y")){
                    agregarCuentas();
                }
            }

            header();
            System.out.print("1.Depositar\n");
            System.out.print("2.Retirar\n");
            System.out.print("3.Ver Saldo\n");
            System.out.print("4.Agregar Cuentas\n");
            System.out.print("5.Salir\n");
            System.out.print(": ");
            Scanner inputAction = new Scanner(System.in);
            String action = inputAction.nextLine();

            switch (action) {
                case "1" -> {
                    // Solicitar al usuario que seleccione cuenta
                    CuentaBancaria selectedCuenta = showCuentas();
                    header();
                    System.out.print("Cuenta: " + selectedCuenta.getNumeroCuenta());
                    System.out.println("\nSaldo: " + currencyFormatter.format(selectedCuenta.saldo));

                    Scanner inputIngreso = new Scanner(System.in);
                    System.out.println("\nIngrese cantidad a depositar: ");

                    double ingreso = inputIngreso.nextDouble();
                    selectedCuenta.depositar(ingreso);
                    // Mostrar nuevo saldo
                    header();
                    System.out.print("Cuenta: " + selectedCuenta.getNumeroCuenta());
                    System.out.print("\nNuevo Saldo: " + currencyFormatter.format(selectedCuenta.saldo));

                    backOrOut();

                    break;
                }
                case "2" -> {
                    CuentaBancaria selectedCuenta = showCuentas();
                    header();
                    Scanner inputEgreso = new Scanner(System.in);
                    System.out.print("Cuenta: " + selectedCuenta.getNumeroCuenta());
                    System.out.println("\nSaldo: " + currencyFormatter.format(selectedCuenta.saldo));

                    System.out.println("Ingrese cantidad a retirar: ");
                    double egreso = inputEgreso.nextDouble();
                    selectedCuenta.retirar(egreso);

                    // Mostrar nuevo saldo
                    header();
                    System.out.print("Cuenta: " + selectedCuenta.getNumeroCuenta());
                    System.out.print("\nNuevo Saldo: " + currencyFormatter.format(selectedCuenta.saldo));

                    backOrOut();
                    break;
                }
                case "3" -> {
                    header();
                    CuentaBancaria selectedCuenta = showCuentas();

                    System.out.print("Cuenta: " + selectedCuenta.getNumeroCuenta());
                    System.out.print("\nSaldo Actual: " + currencyFormatter.format(selectedCuenta.saldo));

                    backOrOut();
                    break;
                }
                case "4" -> {
                    System.out.print("\n\nCuentas con " + cliente.cuentas.toArray().length + " cuentas bancarias.\n");
                    agregarCuentas();

                    backOrOut();
                    break;
                }
                case "5" -> {
                    loggedIn = false;
                    System.out.println("\nDesconectado");
                    break;
                }
                default -> System.out.print("No ha seleccionado ninguna opcion");
            }
        }while (loggedIn);
    }
}