package Client;

import javax.swing.JEditorPane;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import Interface.EmplInterface;

/**
 *
 * @author Cristian Garro Sabogal
 */
public class EmplClientJOption {
public static void main(String[] args) {
          try {
            int empleados = askInt("Número de empleados:");
            int meses     = askInt("Número de meses:");
            int min       = 80;
            int max       = 2800;

            Registry reg = LocateRegistry.getRegistry("localhost", 8585);
            EmplInterface service = (EmplInterface) reg.lookup("EmplImplement");

            service.llenarmatriz(empleados, meses, min, max);
            int[][] matriz    = service.obtenerMatriz();
            int[] totEmpleado = service.totalPorEmpleado();
            int[] promMes     = service.promedioPorMeses(); 
            int total         = service.totalGeneral();

            String html = buildHtml(matriz, totEmpleado, promMes, total);

            JEditorPane pane = new JEditorPane("text/html", html);
            pane.setEditable(false);
            JScrollPane scroll = new JScrollPane(pane);
            scroll.setPreferredSize(new java.awt.Dimension(700, 450));

            JOptionPane.showMessageDialog(null, scroll, "Resultados", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error: " + e.getClass().getSimpleName() + "\n" + e.getMessage(),
                    "Fallo", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static int askInt(String msg) {
        while (true) {
            String s = JOptionPane.showInputDialog(null, msg, "Entrada", JOptionPane.QUESTION_MESSAGE);
            if (s == null) System.exit(0); // cancelar
            try { return Integer.parseInt(s.trim()); }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Ingresa un entero válido.", "Dato inválido", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
     private static String buildHtml(int[][] m, int[] totEmp, int[] promMes, int total) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body style='font-family:sans-serif'>");
        sb.append("<h2>Matriz de salarios</h2><table border='1' cellspacing='0' cellpadding='4'>");
        sb.append("<tr><th>Empleado</th>");
        for (int j = 0; j < m[0].length; j++) sb.append("<th>Mes ").append(j+1).append("</th>");
        sb.append("</tr>");
        for (int i = 0; i < m.length; i++) {
            sb.append("<tr><td>Empleado ").append(i+1).append("</td>");
            for (int j = 0; j < m[i].length; j++) sb.append("<td>").append(m[i][j]).append("</td>");
            sb.append("</tr>");
        }
        sb.append("</table>");

        sb.append("<h3>Total por empleado</h3><ul>");
        for (int i = 0; i < totEmp.length; i++)
            sb.append("<li>Empleado ").append(i+1).append(": ").append(totEmp[i]).append("</li>");
        sb.append("</ul>");

        sb.append("<h3>Promedio por mes</h3><ul>");
        for (int j = 0; j < promMes.length; j++)
            sb.append("<li>Mes ").append(j+1).append(": ").append(promMes[j]).append("</li>");
        sb.append("</ul>");

        sb.append("<h3>Total general: ").append(total).append("</h3>");
        sb.append("</body></html>");
        return sb.toString();
    }
}
    
