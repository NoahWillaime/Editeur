package editeurtexte;

public interface Itexte {
    public void addLine(String ligne);

    public void clear();

    public int getSize();

    public String getLine(int index);

    public int getLineCount();

    public void setLine(int i, String ligne);
}
