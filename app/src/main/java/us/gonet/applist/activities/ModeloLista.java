package us.gonet.applist.activities;

public class ModeloLista implements Comparable<ModeloLista> {

    private String nombre;

    private boolean checked;

    private int posicion;

    ModeloLista(String nombre, boolean checked, int posicion) {
        this.nombre = nombre;
        this.checked = checked;
        this.posicion = posicion;
    }

    public int getPosicion() {
        return posicion;
    }

    public void setPosicion(int posicion) {
        this.posicion = posicion;
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
        } else{
            return nombre.compareTo(o.nombre);
        }
    }
}























