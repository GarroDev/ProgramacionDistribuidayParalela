package Server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import Implement.EmplImplement;


/**
 *
 * @author Cristian Garro Sabogal
 */
public class EmplServer {
    public static void main(String[] args) throws Exception {
       try{
        Registry reg = LocateRegistry.createRegistry(8585);
        EmplImplement servicio = new EmplImplement();
        reg.rebind("EmplImplement", servicio);
        System.out.println("Servidor RMI");

       }catch(Exception e){
        e.printStackTrace();
       }
    }
}