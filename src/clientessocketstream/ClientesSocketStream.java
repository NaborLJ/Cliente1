package clientessocketstream;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import javax.swing.JOptionPane;

public class ClientesSocketStream {

    public static void main(String[] args) {
        try {
            int num1 = 0;
            int num2 = 0;
            int operacion = 0;
            char opr;
            System.out.println("Creando socket cliente");
            //creamos el cliente
            Socket cs = new Socket();
            System.out.println("Estableciendo la conexion");
            InetSocketAddress addr = new InetSocketAddress("localhost", 5555);
            //establecemos la conexion con el servidor
            cs.connect(addr);

            ObjectInputStream resultado = new ObjectInputStream(cs.getInputStream());
            ObjectOutputStream dados = new ObjectOutputStream(cs.getOutputStream());
            
            System.out.println("Enviando mensaje");
            
            num1 = Integer.parseInt(JOptionPane.showInputDialog("Introduzca primer número"));
            operacion = Integer.parseInt(JOptionPane.showInputDialog("Que operación desea realizar? \n 1= + \n 2= - \n 3= X \n 4= / "));
            num2 = Integer.parseInt(JOptionPane.showInputDialog("Introduzca segundo número"));
           
             
             dados.writeInt(num1);
             dados.writeInt(num2);
             dados.writeInt(operacion);
             
             dados.flush();
             
            System.out.println("Mensaje enviado");

            System.out.println("Cerrando el socket cliente");
            
            int total = resultado.readInt();
            opr = resultado.readChar();
            System.out.println("Resultado de " + num1 + opr + num2 + " = " + total);
            
            resultado.close();
            cs.close();
            dados.close();

            System.out.println("Terminado");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
