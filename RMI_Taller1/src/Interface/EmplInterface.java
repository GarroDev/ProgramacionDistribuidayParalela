package Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Cristian Garro Sabogal
 */
public interface EmplInterface extends Remote{
    void llenarmatriz (int empleados, int meses, int min, int max) throws RemoteException;
    int [][] obtenerMatriz() throws RemoteException;
    int [] totalPorEmpleado() throws RemoteException;
    int [] promedioPorMeses() throws RemoteException;
    int totalGeneral() throws RemoteException;
}
