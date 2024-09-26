package ie.atu.mountainappmanager;

public class Mountain {
    // instance vars

    private String mountainId;
    private double heightMetres;
    private int walkDurationMinutes;
    // constructor

    public Mountain(String mountainId, double heightMetres, int walkDurationMinutes) {
        this.mountainId = mountainId;
        this.heightMetres = heightMetres;
        this.walkDurationMinutes = walkDurationMinutes;

    }

    // getters
    public String getMountainId() {
        return mountainId;
    }

    public double getHeightMetres() {
        return heightMetres;
    }

    public int getWalkDurationMinutes() {
        return walkDurationMinutes;
    }

    // setters
    public void setMountainId(String mountainId) {
        this.mountainId = mountainId;
    }

    public void setHeightMetres(double heightMetres) {
        this.heightMetres = heightMetres;
    }

    public void setWalkDurationMinutes(int walkDurationMinutes) {
        this.walkDurationMinutes = walkDurationMinutes;
    }

    // validation

    public static boolean mountainIdIsValid(String mountainId) {
        if (mountainId == null) {
            System.err.println("Mountain ID can not be null");
            return false;
        } else {
            return true;
        }
    }

    public static boolean heightMetresIsValid(double heightMetres) {
        if (heightMetres == 0.0) {
            System.err.println("HeightMeters can not be null");
            return false;
        } else {
            return true;
        }
    }

    public static boolean walkDurationMinutesIsValid(int walkDurationMinutes) {
        if (walkDurationMinutes == 0) {
            System.err.println("Walk can not be null");
            return false;
        } else {
            return true;
        }
    }

    public static boolean isValid(String mounatinId, double heightMetres, int walkDurationMinutes) {
        return mountainIdIsValid(mounatinId) && heightMetresIsValid(heightMetres)
                && walkDurationMinutesIsValid(walkDurationMinutes);
    }

}
