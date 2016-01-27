package hibernate.one2one.primary;

public class Department {
	private Integer departId;
	private String departName;
	
	private Manager manager;

	public Integer getDepartId() {
		return departId;
	}

	public void setDepartId(Integer departId) {
		this.departId = departId;
	}

	public String getDepartName() {
		return departName;
	}

	public void setDepartName(String departName) {
		this.departName = departName;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
}
