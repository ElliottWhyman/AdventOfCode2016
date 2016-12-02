package util;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

public class Vector {

    private static Table<Integer, Integer, Vector> vectorTable = HashBasedTable.create();

    private final int x;
    private final int y;

    private Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector vector) {
        return Vector.valueOf(this.x + vector.x, this.y + vector.y);
    }

    public Vector subtract(Vector vector) {
        return Vector.valueOf(this.x - vector.x, this.y - vector.y);
    }

    public Vector multiply(Vector vector) {
        return Vector.valueOf(this.x * vector.x, this.y * vector.y);
    }

    public Vector multiply(int constant) {
        return Vector.valueOf(this.x * constant, this.y * constant);
    }

    public Vector divide(Vector vector) {
        return Vector.valueOf(this.x / vector.x, this.y / vector.y);
    }

    public double magnitudeSquared() {
        return Math.pow(this.x, 2) + Math.pow(this.y, 2);
    }

    public Vector normaliseAndRound() {
        int x = (int) Math.ceil(this.x / Math.sqrt(this.magnitudeSquared()));
        int y = (int) Math.ceil(this.y / Math.sqrt(this.magnitudeSquared()));
        return Vector.valueOf(x, y);
    }

    public int getCartesianX() {
        return this.x;
    }

    public int getCartesianY() {
        return this.y;
    }

    public static Vector valueOf(int x, int y) {
        Vector vector = vectorTable.get(x,y);
        if (vector != null)
            return vector;
        vectorTable.put(x,y, new Vector(x,y));
        return valueOf(x,y);
    }

    @Override
    public String toString() {
        return "Vector(" + x + ", " + y + ")";
    }

    @Override
    public int hashCode() {
        return x * 31 + y;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Vector) {
            Vector Vector = (Vector) object;
            if (Vector.getCartesianX() == this.getCartesianX() && Vector.getCartesianY() == this.getCartesianY()) {
                return true;
            }
        }
        return false;
    }

}
