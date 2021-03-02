package net.kunmc.lab.boobsmod.constants;
public enum EnumParts {
    Boobs("boobs");


    public int patterns = 1;

    EnumParts(String name) {
        this.name = name;
    }
    public String name;
    public static EnumParts FromName(String name) {
        for (EnumParts e : values()) {
            if (e.name.equals(name))
                return e;
        }
        return null;
    }
}