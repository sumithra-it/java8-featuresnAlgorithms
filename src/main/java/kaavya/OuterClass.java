package kaavya;
import java.time.LocalDateTime;

public class OuterClass {
	String name;
	String nameConverter() {
		System.out.println("Converting the name...");
		return name.toUpperCase();
	}
	public static void main(String[] args) {
		OuterClass outer = new OuterClass();
		outer.name = "This is tricky";
		System.out.println(outer.name + " " + outer.nameConverter());
				
		InnerClass inner = outer.new InnerClass();
		inner.datetime=LocalDateTime.now().minusDays(33);
		System.out.println("Age is older than 1 month:" + inner.isTimeLastMonth());
		
	}
	
	class InnerClass{
		LocalDateTime datetime;
		boolean isTimeLastMonth() {
			System.out.println("Determining the time...");
			return (datetime.compareTo(LocalDateTime.now()) >= 30) ;
		}
	}

}
