package Client;

import java.util.Scanner;
import java.util.Arrays;     
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import Interface.EmplInterface;

/**
 *
 * @author Cristian Garro Sabogal
 */
public class EmplClient {
public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Número de empleados: ");
            int empleados = sc.nextInt();
            System.out.print("Número de meses: ");
            int meses = sc.nextInt();

            // Rango de salarios aleatorios
            int min = 800;   // salario mínimo simulado en USD
            int max = 2500;  // salario máximo simulado en USD

            Registry reg = LocateRegistry.getRegistry("localhost", 8585);
            EmplInterface service = (EmplInterface) reg.lookup("EmplImplement");

            service.llenarmatriz(empleados, meses, min, max);

            int[][] matriz = service.obtenerMatriz();
            int[] totEmpleado = service.totalPorEmpleado();
            int[] promMes = service.promedioPorMeses();
            int total = service.totalGeneral();

            System.out.println("\n=== Matriz de salarios ===");
            for (int i = 0; i < matriz.length; i++) {
                System.out.println("Empleado " + (i+1) + ": " + Arrays.toString(matriz[i]));
            }

            System.out.println("\nTotal pagado por empleado:");
            for (int i = 0; i < totEmpleado.length; i++) {
                System.out.printf("Empleado %d: %d%n", (i+1), totEmpleado[i]);
            }

            System.out.println("\nPromedio por mes:");
            for (int j = 0; j < promMes.length; j++) {
                System.out.printf("Mes %d: %d%n", (j+1), promMes[j]);
            }

            System.out.printf("%nTotal pagado en toda la matriz: %d%n", total);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
