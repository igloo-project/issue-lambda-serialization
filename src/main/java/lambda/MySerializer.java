package lambda;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MySerializer {

	public static void main(String[] args) throws IOException, ClassNotFoundException {
		// use a lambda definition
		serializeAndDeserialize(false);
		System.out.println("Deserialization without method reference OK");
		// use a method reference - this will trigger exception
		// Caused by: java.lang.invoke.LambdaConversionException: Incorrect number of parameters for static method newInvokeSpecial lambda.MyOuterClass$MyInnerClass.<init>:(MyOuterClass)void; 0 captured parameters, 0 functional interface method parameters, 1 implementation parameters
		serializeAndDeserialize(true);
		System.out.println("Deserialization with method reference OK");
	}

	/**
	 * 
	 * @param useMethodReference use either a lambda definition or a method reference to store method
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private static void serializeAndDeserialize(boolean useMethodReference) throws IOException, ClassNotFoundException {
		MyOuterClass l = new MyOuterClass(useMethodReference);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(l);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
		ois.readObject();
	}

}
