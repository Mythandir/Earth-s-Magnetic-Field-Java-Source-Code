
public class uncertaintyCalculator { // Define the class

public static double findUncertainty(double meanX, double meanY, double meanXY, double meanX2) 
{
    // Constants
    double D = 0.1405; // Distance in meters
    double U = 4 * Math.PI * 1e-7; // permeability of free space (H/m)
    int N = 50; // Number of turns
    double delI = 0.001; // Current error allowance
    double delN = 1; // Radiance error allowance
    double delR = 0.001; // Radius error allowance
    double delTheta = 0.01745; // Angular error allowance

    // Check for division by zero
    if (Math.abs(meanX) < 1e-9 || Math.abs(meanX2) < 1e-9) {
        System.out.println("Error: meanX or meanX2 is too close to zero.");
        return 0;
    }

    double term1 = ((U * N * delI) / (D * meanX)) * 1e6;
    double term2 = ((U * meanY * delN) / (D * meanX)) * 1e6;
    double term3 = ((U * meanY * N * delR) / ((D * D) * meanX)) * 1e6;
    double term4 = (((U * meanY * N) / (D * meanX2)) * (1 + meanX2) * delTheta) * 1e6;

    // Compute total uncertainty
    double uncertainty = term1 + term2 + term3 + term4;
    return uncertainty; // Return the calculated uncertainty number
	}
}