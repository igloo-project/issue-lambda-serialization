package test;

import java.io.IOException;

import org.junit.Test;

import lambda.MySerializer;

public class TestSerialize {

	@Test
	public void testSerialize() throws IOException, ClassNotFoundException {
		MySerializer.main(null);
	}

}
