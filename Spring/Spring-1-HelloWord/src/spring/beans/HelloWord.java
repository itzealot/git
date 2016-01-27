package spring.beans;

public class HelloWord {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void hello() {
		System.out.println("Hello, " + name);
	}
}
