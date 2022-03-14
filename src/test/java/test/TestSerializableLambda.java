package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.function.Function;

import org.junit.Test;

public class TestSerializableLambda {

	@Test
	public void testSerializableLambda() throws ClassNotFoundException, IOException {
		Broken<ChildLambdaProvider> r = new Broken<>();
		serializeAndDeserialize(r);
	}

	private static void serializeAndDeserialize(Object object) throws IOException, ClassNotFoundException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(object);
		ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(baos.toByteArray()));
		ois.readObject();
	}
	
	public static class Broken<E extends LambdaProvider<?>> implements Serializable {
		private static final long serialVersionUID = -2775595600924717218L;
		private Function<E, ?> idExpression;

		public Broken() {
			this.idExpression = (Serializable & Function<E, ?>) LambdaProvider::getId;
		}
	}

	public static class LambdaProvider<I extends Comparable<I>> {
		public I getId() {
			return null;
		}
	}

	public static class ChildLambdaProvider extends LambdaProvider<Long> {}
}
