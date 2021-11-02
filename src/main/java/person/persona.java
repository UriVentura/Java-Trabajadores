package person;

import exceptions.*;

public class persona {

    String nombre;
    Integer edad;
    String dni;

    public persona(String nombre, Integer edad, String dni) {
        this.nombre = nombre;
        this.edad = edad;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws NombreIncorrecto {
        if (nombre.matches(".*\\d.*")) {
            throw new NombreIncorrecto("El nombre no puede tener dígitos.");
        } else if(nombre.length()<3) {
            throw new NombreIncorrecto("El nombre debe tener mínimo 3 letras");
        } else {
            this.nombre = nombre;
        }
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) throws EdadIncorrecta {
        if (edad < 1 || edad > 110) {
            throw new EdadIncorrecta("La edad no puede ser menor a 1 ni "+
            "mayor a 110.");
        } else {
            this.edad = edad;
        }
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) throws DniIncorrecto {
        if (dni.length()!=9) {
            throw new DniIncorrecto("El DNI ha de ser una string de 9 valores.");
        } else {
            this.dni = dni;
        }
    }

   Exception EdadIncorrecta = new Exception("La edad no puede ser menor a 1 o "
    +"mayor a 110.");

}