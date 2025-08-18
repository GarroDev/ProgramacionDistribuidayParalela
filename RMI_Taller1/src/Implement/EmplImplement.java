package Implement;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.ThreadLocalRandom;
import Interface.EmplInterface;

/**
 *
 * @author Cristian Garro Sabogal
 */
public class EmplImplement extends UnicastRemoteObject implements EmplInterface {
    private int[][] matriz = new int[0][0];

    public EmplImplement() throws RemoteException {super ();}

    @Override
    public synchronized void llenarmatriz(int empleados, int meses, int min, int max) throws RemoteException{
        if(empleados <= 0 || meses <= 0) throw new RemoteException("Dimensiones inválidas");
        if(min < 0 || max <= min) throw new RemoteException("Rango invalido");
        matriz = new int[empleados][meses];
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        for (int i=0; i< empleados; i++){
            for(int j=0; j<meses;j++){
                int val = rnd.nextInt(min,max);
                matriz[i][j] = val;
            }
        }
    }

    @Override
    public synchronized int[][] obtenerMatriz() throws RemoteException {
        int [][] copia = new int[matriz.length][];
        for (int i=0;i < matriz.length;i++){
            copia[i]= matriz[i].clone();
        }
        return copia;
    }

    @Override
    public int[] totalPorEmpleado() throws RemoteException {
        int [] totales = new int[matriz.length];
        for (int i=0;i < matriz.length;i++){
            int sum = 0;
            for(int j=0;j<matriz[i].length;j++){
                sum += matriz[i][j];
                totales[i]=sum;
            }
        }
        return totales;
    }

    @Override
    public synchronized int[] promedioPorMeses() throws RemoteException {
        if (matriz.length == 0) return new int[0];
        int meses = matriz[0].length;
        int[] proms = new int[meses];
        for (int j = 0; j < meses; j++) {
            int sum = 0;
            for (int i = 0; i < matriz.length; i++) sum += matriz[i][j];
            int avg = sum / matriz.length;
            proms[j] = Math.round(avg * 100) / 100;
        }
        return proms;
    }

    @Override
    public synchronized int totalGeneral() throws RemoteException {
        int sum = 0;
        for (int[] fila : matriz)
            for (int v : fila) sum += v;
        return Math.round(sum * 100) / 100;
    }    



}

