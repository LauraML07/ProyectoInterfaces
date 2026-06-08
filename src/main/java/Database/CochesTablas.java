package Database;

public class CochesTablas {
    private int id;
    private String marca;
    private String matricula;
    private int fecha_matricula;
    private int n_puertas;

    public CochesTablas(int id, String marca, String matricula, int fecha_matricula, int n_puertas) {
        this.id = id;
        this.marca = marca;
        this.matricula = matricula;
        this.fecha_matricula = fecha_matricula;
        this.n_puertas = n_puertas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getFecha_matricula() {
        return fecha_matricula;
    }

    public void setFecha_matricula(int fecha_matricula) {
        this.fecha_matricula = fecha_matricula;
    }

    public int getN_puertas() {
        return n_puertas;
    }

    public void setN_puertas(int n_puertas) {
        this.n_puertas = n_puertas;
    }
}
