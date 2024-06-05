import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBook {


    static HashMap<String,String> addressBk = new HashMap<>();

    public static void load(String fileName) throws IOException {
        File file = new File(fileName);
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line= br.readLine()) != null){
            String[] rec = line.split(",",0);
                if (rec.length == 2) {
                    addressBk.put(rec[0],rec[1]);
                }
        }
        br.close();
    }

    public static void save(String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        for (Map.Entry<String, String> entry : addressBk.entrySet()) {
            writer.write(entry.getKey() + "," + entry.getValue());
            writer.newLine();
        }
        writer.close();


    }

    public static void list(){
        System.out.println("Contactos: ");
        for (Map.Entry<String, String> entry : addressBk.entrySet()){
            System.out.println(entry.getKey() + "," +entry.getValue());
        }
    }

    public static void create(String number, String name) {

        addressBk.put(number,name);

    }

    public static void delete(String number) {
        addressBk.remove(number);

    }

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        String fileName = "addressbook.txt";


        try {
            load(fileName);

        } catch (IOException e) {
            System.out.println("No se puede cargar el archivo");
        }

        while (true){
            System.out.println("Directorio :");
            System.out.println("1. Mostrar contactos de la agenda");
            System.out.println("2. Crear un nuevo contacto");
            System.out.println("3. Borrar un contacto");
            System.out.println("4. Salir");

            int choice = scanner.nextInt();

            scanner.nextLine();

            switch(choice){
                case 1:
                    list();
                    break;
                case 2:
                    System.out.println("Ingresa el numero: ");
                    String number = scanner.nextLine();
                    System.out.println("Ingresa el nombre: ");
                    String name = scanner.nextLine();
                    create(number,name);
                    try {
                        save(fileName);

                    } catch (IOException e){
                        System.out.println("No se guardo el contacto: ");
                    }
                    break;
                case 3:
                    System.out.println("Ingresa el numero a eliminar: ");
                    String numberToDelete = scanner.nextLine();
                    delete(numberToDelete);

                    try {
                        save(fileName);

                    } catch (IOException e) {
                        System.out.println("No se elimino el contacto");

                    }
                    break;

                case 4:
                    try{
                        save(fileName);

                    } catch (IOException e){
                        System.out.println("Error");

                    }
                    System.out.println("Cerrando...");
                    return;
                default:
                    System.out.println("Seleccion no disponible");
            }

        }



        }
    }

