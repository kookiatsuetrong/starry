import starry.StarryApp;

public class Start extends StarryApp {
	
	public void main() {
		loadFile("code/main.html");
	}
	
	public void setup() {
		setAction("refresh", e -> main() );
		
		String arch = System.getProperty("os.arch");
		System.out.println(arch); // amd64
		
		
		String os = System.getProperty("os.name");
		System.out.println(os);   // Windows 11
		
		try {
			getElement("report-arch")
				.setTextContent(arch);
			getElement("report-os")
				.setTextContent(os);
		} catch (Exception e) { }
	}
}
