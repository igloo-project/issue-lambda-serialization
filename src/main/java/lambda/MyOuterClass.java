package lambda;

import java.io.Serializable;
import java.util.function.Supplier;

public class MyOuterClass implements Serializable {

	private static final long serialVersionUID = 5390565572939096897L;
	public Supplier<MyOuterClass.MyInnerClass> supplier;

	@SuppressWarnings("unchecked")
	public MyOuterClass(boolean useMethodReference) {
		if (useMethodReference) {
			this.supplier = (Supplier<MyOuterClass.MyInnerClass> & Serializable) MyInnerClass::new;
		} else {
			this.supplier = (Supplier<MyOuterClass.MyInnerClass> & Serializable) (() -> new MyInnerClass());
		}
	}

	public class MyInnerClass implements Serializable {
		private static final long serialVersionUID = 2478179807896338433L;
		public MyInnerClass() {
		}
	}

}
