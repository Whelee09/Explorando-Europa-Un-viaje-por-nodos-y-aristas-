public class Ciudad {
    private String cod;
    private int id;
    private String nombre;
    public Ciudad() {
    }
    public Ciudad(String cod, int id, String nombre) {
        this.cod = cod;
        this.id = id;
        this.nombre = nombre;
    }
    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
}
