package us.gonet.applist.activities;

public class ModeloLista implements Comparable<ModeloLista> {

    private String nombre;

    private boolean checked;

    public ModeloLista(String nombre, boolean checked) {
        this.nombre = nombre;
        this.checked = checked;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int compareTo(ModeloLista o) {
        String nom="";
        if (o.checked) {
             nom = o.nombre;
        }

        if (checked) {
            return this.nombre.compareTo(nom);
        }

        return nombre.compareTo(o.nombre);
    }
}
