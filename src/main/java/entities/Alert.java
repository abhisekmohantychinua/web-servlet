package entities;

public class Alert {
	private String classes;
	private String message;
	private AlertType type;

	public Alert() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Alert(String classes, String message, AlertType type) {
		super();
		this.classes = classes;
		this.message = message;
		this.type = type;
	}

	public String getClasses() {
		return classes;
	}

	public void setClasses(String classes) {
		this.classes = classes;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public AlertType getType() {
		return type;
	}

	public void setType(AlertType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Alert [classes=" + classes + ", message=" + message + ", type=" + type + "]";
	}

}
