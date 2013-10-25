/*
 * CS 61C Fall 2013 Project 1
 *
 * DoublePair.java is a class which stores two doubles and 
 * implements the Writable interface. It can be used as a 
 * custom value for Hadoop. To use this as a key, you can
 * choose to implement the WritableComparable interface,
 * although that is not necessary for credit.
 */

import java.io.DataInput;
import java.io.DataOutput;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class DoublePair implements Writable {
    // Declare any variables here
    private double x;
    private double y;
    /**
     * Constructs a DoublePair with both doubles set to zero.
     */
    public DoublePair() {
	x = 0;
	y = 0;
    }

    /**
     * Constructs a DoublePair containing double1 and double2.
     */ 
    public DoublePair(double double1, double double2) {
	x = double1;
	y = double2;
    }

    /**
     * Returns the value of the first double.
     */
    public double getDouble1() {
	return x;        
    }

    /**
     * Returns the value of the second double.
     */
    public double getDouble2() {
        return y;
    }

    /**
     * Sets the first double to val.
     */
    public void setDouble1(double val) {
	x = val;
    }

    /**
     * Sets the second double to val.
     */
    public void setDouble2(double val) {
	y = val;
    }

    /**
     * write() is required for implementing Writable.
     */
    public void write(DataOutput out) throws IOException {
        out.writeDouble(x);
	out.writeDouble(y);
    }

    /**
     * readFields() is required for implementing Writable.
     */
    public void readFields(DataInput in) throws IOException {
	x = in.readDouble();
	y = in.readDouble();
    }

    public static void main(String[] args) {
	DoublePair blank = new DoublePair();
	DoublePair first = new DoublePair(5, 10);
	DoublePair second = new DoublePair(123, 9234);
	DoublePair third = new DoublePair(5, 12);

	System.out.println("Testing Serialization/deserialization");
	String dataFile = "doublepairtestdata";

	try {
            DataOutputStream out = null;
            try {
                out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)));
                first.write(out);
            } finally {
                out.close();
            }
    
            DataInputStream in = null;
            try {
                in = new DataInputStream(new BufferedInputStream(new FileInputStream(dataFile)));
                blank.readFields(in);
            } finally {
                in.close();
            }
            System.out.print("DoublePair fourth should have the same value as DoublePair first. Result is ");
            System.out.println((first.getDouble1() == blank.getDouble1() && first.getDouble2() == blank.getDouble2()) ? "CORRECT." : "INCORRECT");
        } catch (IOException e) {
            System.err.println("Error with serialization test.");
        }
    }
}
