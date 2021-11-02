package person;

import static helps.help.entryStr;
import static helps.help.putInt;
import static helps.help.siguiente;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.DniIncorrecto;
import exceptions.EdadIncorrecta;
import exceptions.Limits;
import exceptions.NombreIncorrecto;
import exceptions.PosicionIncorrecta;

public class GestionaPersonas {
    // Creo un HashMap de trabajadores, es la "key" principal donde recojo los datos de cada uno
    static HashMap<String,persona> trabajadores = new HashMap<String,persona>();

    // Creo una Array para definir que trabajadores añadiré a mi grupoTrabajo
    static ArrayList<persona> grupoTrabajo = new ArrayList<persona>();

    public static void main(String[] args) {
            // Creo dos trabajadores estaticos ya incluidos al inicializar el programa.
            persona p1 = new persona("Paco Sanz", 90, "12345678A");
            trabajadores.put(p1.getDni(), p1);
            persona p2 = new persona("Lionel Messi", 10, "10101010B");
            trabajadores.put(p2.getDni(), p2);

            // Menu principal con las opciónes del programa.
            // Añadido "Case 5" con la posibilidad de ver los trabajadores sin entrar
            // En otra opción.
            int opcion;
            do {
                System.out.println("\n------Qué quieres hacer?------"+
                "\n 1. Crea un trabajador"+
                "\n 2. Borra un trabajador"+
                "\n 3. Añade un trabajador al grupo de trabajo"+
                "\n 4. Borra un trabajador del grupo de trabajo"+
                "\n 5. Ver lista de trabajadores y del grupo de trabajo"+
                "\n 0. Salir");
                opcion = putInt("Introduce el valor entre 0 y 5: ",0,5);

                //Abro un Switch/Case para poder seleccionar donde entraré a traves de una entrada por teclado.
                switch(opcion)
                {
                    case 0:
                        System.out.println("Adiós!");
                        break;
                    case 1:
                        System.out.println("Elegida la opción "+ opcion);
                        verTrabajador(opcion);
                        creaTrabajador();
                        break;
                    case 2:
                        System.out.println("Elegida la opción "+ opcion);
                        verTrabajador(opcion);
                        deleteTrabajador();
                        break;
                    case 3:
                        System.out.println("Elegida la opción "+ opcion);
                        verTrabajador(opcion);
                        try{
                            addTrabajadorGrupoTrabajo();
                        } catch (Limits e){
                            System.out.println(e.getMessage());
                            e.printStackTrace();
                        } catch (Exception e) {
                            System.out.println("Error");
                            e.printStackTrace();
                        } finally {
                            siguiente();
                        }
                        break;
                    case 4:
                        System.out.println("Elegida la opción "+ opcion);
                        verTrabajador(opcion);
                        try {
                            deleteTrabajadorGrupoTrabajo();
                        } catch (PosicionIncorrecta e) {
                            System.out.println(e.getMessage());
                            e.printStackTrace();
                            siguiente();
                        }
                            break;
                    case 5:
                        System.out.println("Elegida la opción "+ opcion);
                        verTrabajador(opcion);
                        break;
                }  
            } while (opcion !=0);          
    }
    
    //Esta funcion crea un trabajador obteniendo los datos de la clase persona
    //Introducimos los datos, nombre, dni, edad, lo que tenemos recogido en la clase
    static void creaTrabajador() {
        boolean errorStatus = false;
        persona nuevo = new persona(null, null, null);
        do {
            try {
                if (nuevo.getNombre() == null){
                    nuevo.setNombre(entryStr("Indica nombre de la persona (Solo letras): "));
                }
                if (nuevo.getEdad() == null){
                    nuevo.setEdad(putInt("Indica la edad (Entre 1 y 110): "));
                }
                if (nuevo.getDni() == null){
                    nuevo.setDni(entryStr("Indica el DNI (Una string de 9 caracteres): "));
                }
                errorStatus = false;
                trabajadores.put(nuevo.getDni(), nuevo);
            } catch (DniIncorrecto e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                errorStatus = true;
                nuevo.dni = null;
            } catch (EdadIncorrecta e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                errorStatus = true;
                nuevo.edad = null;
            } catch (NombreIncorrecto e) {
                e.printStackTrace();
                System.out.println(e.getMessage());
                errorStatus = true;
                nuevo.nombre = null;
            }
        } while (errorStatus);

    }
    //En esta funcion borramos el trabajador, usamos el remove indicandole la key.
    static void deleteTrabajador() {
        try {
            String dni = entryStr("\nIntroduce el DNI del trabajador a borrar: ");
            System.out.println("Borrado: "+trabajadores.get(dni).getNombre() +
                " -> Edad: "+trabajadores.get(dni).getEdad()+
                " -> DNI: "+trabajadores.get(dni).getDni()
                );
            trabajadores.remove(dni);
            siguiente("Pulse ENTER para seguir...");
        } catch (NullPointerException e) {
            System.out.println("No existe ese trabajador.");
        } catch (Exception e) {
            System.out.println("Error");
        }
    }
    //En esta funcion añadimos un trabajador YA CREADO a un grupo de trabajo.
    static void addTrabajadorGrupoTrabajo() throws Limits {
        if (grupoTrabajo.size() >= 2) {
            throw new Limits("El grupo de trabajo no puede ser de más de 2 personas");
        }
        else {
            try {
            String dni = entryStr("\nIntroduce el DNI del trabajador a añadir"+
                " al grupo de trabajo: ");
            grupoTrabajo.add(trabajadores.get(dni));
            } catch (NullPointerException e) {
            System.out.println("DNI incorrecto.");
            } catch (Exception e) {
            System.out.println("Error");
            e.printStackTrace();
            }
        }
    }
    //En esta funcion eliminamos un trabajador YA DENTRO de un grupo de trabajo desde su indice (0 -> 1)
    //Ya que no puede haber más de 2 trabajadores en el grupo de trabajo.
    static void deleteTrabajadorGrupoTrabajo() throws PosicionIncorrecta {
            int i = putInt("Índice del trabajador a borrar: ");
            if (i > grupoTrabajo.size() - 1 || i < 0) {
                throw new PosicionIncorrecta("Posición incorrecta.");
            } else {
                System.out.println("Borrado del grupo de trabajo: "+
                    grupoTrabajo.get(i).toString());
                grupoTrabajo.remove(i);
            }
        }
    //Esta función la usamos para ver el listado de trabajadores y del grupo de trabajo, así cada vez que elegimos una opción
    //Vemos como está y los cambios hechos.
    static void verTrabajador(int opcion)
    {
        if (opcion != 0) {
            System.out.println("\n---Trabajadores:---");
            for (String key : trabajadores.keySet()) {
                persona p = trabajadores.get(key);
                System.out.println(p.getDni() +" - " + "_"+p.getNombre() +"_"+
                " edad:" + p.getEdad());
            }

            System.out.println("\n---Grupo de Trabajo:---");
            for (persona p : grupoTrabajo) {
                System.out.println(p.getDni() +" - " + "_"+p.getNombre() +"_"+
                " edad:" + p.getEdad());
            }
            siguiente("\nPulse ENTER para seguir...");
        } 
    }
}