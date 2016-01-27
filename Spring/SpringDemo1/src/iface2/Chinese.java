package iface2;

public class Chinese implements Human {

	private Language language;
	public Chinese() {
		//has nothing
	}
	public Chinese(Language language) {
		this.language = language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	@Override
	public void speak() {
		// TODO Auto-generated method stub
		System.out.println(language.kind());
	}

}
