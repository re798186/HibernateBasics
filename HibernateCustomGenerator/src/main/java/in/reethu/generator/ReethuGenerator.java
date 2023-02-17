package in.reethu.generator;

import java.io.Serializable;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

public class ReethuGenerator implements IdentifierGenerator {

	static int i= 100;
	static {
		System.out.println("RandomGenerator.class file is loading...");
	}

	public ReethuGenerator() {
		System.out.println("RandomGenerator.class object is created...");
	}

	@Override
	public Serializable generate(SharedSessionContractImplementor arg0, Object arg1) throws HibernateException {
		System.out.println("RandomGenerator.generate()");
		System.out.println(arg1);
		String idValue = "REETHU"+ ++i;
		return idValue;
	}

}
