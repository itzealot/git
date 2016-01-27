package spring.beans.factorybean;

import org.springframework.beans.factory.FactoryBean;

/**
 * 自定义的bean需要实现 FactoryBean<T>接口
 * @author zengtao
 *
 */
public class CarFactoryBean implements FactoryBean<Car>{

	private String brand;
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	@Override
	public Car getObject() throws Exception {
		// TODO Auto-generated method stub
		return new Car(brand, 500000);
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return Car.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
