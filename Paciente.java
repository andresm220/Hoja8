public class Paciente implements Comparable<Paciente> {
    
    private String nombre;
    private String sintoma;
    private char codigo;


    public Paciente(String nombre, String sintoma, char codigo) {
        this.nombre = nombre;
        this.sintoma = sintoma;
        this.codigo = codigo;
    }

    @Override
    public int compareTo(Paciente otro) {
        return this.codigo - otro.codigo;
    }

    public void printInfo() {
        System.out.println(nombre + ", " + sintoma + ", " + codigo );
    }
}