package app.clip.catalogs;

public enum Actions {
	ADD(1, "add"),
	SHOW(2, "show"),
	LIST(3, "list"),
	SUM(4, "sum");
	
	private int id;
	private String description;
	
	Actions(int id, String description) {
		this.id = id;
		this.description = description;
	}
	
	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
}
