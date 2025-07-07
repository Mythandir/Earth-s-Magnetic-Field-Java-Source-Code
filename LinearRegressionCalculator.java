import java.io.*;
import java.util.*;
import java.lang.Math;

public class LinearRegressionCalculator {	//begin the program
	
    public static void main(String[] args) {
        String filename = "src/loggerProCurrentTheta.csv";
        List<Double> xValues = new ArrayList<>();	//this is just tan(A) (Degrees)
        List<Double> yValues = new ArrayList<>();	//this is just the current (Amps)

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) //This is the reader
        {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    double theta = Math.toRadians(Double.parseDouble(parts[0]));
                    double current = Double.parseDouble(parts[1]);

                    double x = Math.tan(theta);
                    xValues.add(x);
                    yValues.add(current);
                }
            }
        } //end of reading
       
        catch (IOException e) //standard error messages
        {
            System.out.println("Error reading the file: " + e.getMessage()); //if no file
            return;
        } //end of error message

        int n = xValues.size();
        if (n == 0) {
            System.out.println("No data found in the file."); //if no data is found
            return;
        } //end of error message

        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;

        for (int i = 0; i < n; i++) //summations for the average value
        {
            double x = xValues.get(i);
            double y = yValues.get(i);
            sumX += x; //n-values for tan(θ)
            sumY += y; //n-values for current
            sumXY += x * y; //self explanatory, just current multiplied by the compass angle
            sumX2 += x * x; //just the compass tangent squared
        } //end of summation loop

        double meanX = sumX / n; //average value for the tangent angle
        double meanY = sumY / n; //average value for the current
        double meanXY = sumXY / n; //average value for product of XY
        double meanX2 = sumX2 / n; //average value for X^2

        double m = (meanXY - (meanX * meanY)) / (meanX2 - (meanX * meanX)); //linear regression formula

        System.out.printf("The linear regression value is %.5f amps/tan(θ)%n", m); //output message

        // Compute magnetic field using the calculated regression value
        double M_microT = magneticFieldCalculator.magneticField(m);

        // Output the computed magnetic field in microteslas
        System.out.printf("The computed magnetic field is %.5f µT%n", M_microT);
        
        //compute uncertainty using the calculated magnetic field value in microteslas
        double uncertaintyNum = uncertaintyCalculator.findUncertainty(meanX, meanY, meanXY, meanX2);
        
        //output the computed uncertainty in microteslas
        System.out.printf("The uncertainty number is %.5f µT%n", uncertaintyNum);
    }//end of function
    
} //end
