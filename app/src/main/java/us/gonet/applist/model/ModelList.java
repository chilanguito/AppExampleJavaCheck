package us.gonet.applist.model;

public class ModelList implements Comparable<ModelList> {

    private String name;

    private boolean checked;

    private int mPosition;

    public ModelList(String name, boolean checked, int position) {
        this.name = name;
        this.checked = checked;
        this.mPosition = position;
    }

    public int getPosition() {
        return mPosition;
    }

    public void setPosition(int position) {
        this.mPosition = position;
    }

    public String getName() {
        return name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    @Override
    public int compareTo(ModelList o) {
        String nom = "";
        if (o.checked) {
            nom = o.name;
        }

        if (checked) {
            return this.name.compareTo(nom);
        } else {
            return name.compareTo(o.name);
        }
    }
}























