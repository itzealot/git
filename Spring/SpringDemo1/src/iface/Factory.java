package iface;


public class Factory {
	public Human createChinese() {
		return new Chinese();
	}
	public Human createAmerican() {
		return new American();
	}
}
