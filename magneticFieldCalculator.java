public class magneticFieldCalculator 
{
    public static double magneticField(double m) 
    {
        // Constants
        double D = 0.1405; // Distance in meters
        double U = 4 * Math.PI * 1e-7; // permability of free space (H/m)
        int N = 50; // Number of turns

        // Magnetic field equation: M = ((U * N) / D) * m
        double M = (((U * N) / D) * m) * 1e6; //In microteslas

        return M; // Return the calculated magnetic field
    }
}
