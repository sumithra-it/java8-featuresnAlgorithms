package kaavya.functionalInterfaces;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.BiPredicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors; 

public class ConsumerFunction {
	public static void main(String[] args) {
		//consumerExample(); //consume any type and do an operation and return nothing
		
		predicateExample();//for any type return boolean value
		
		//supplierExample();
		
		//methodReferenceExample();
		
		//sample autoboxing - coverting between primitives to corresponding wrapper
		Integer i = Integer.valueOf(345);
		if (i > 500) 
			i++;
	}
	
	private static void methodReferenceExample(){
		List<Integer> intList=Arrays.asList(1,2,3,4);
		BiPredicate<List<Integer>,Integer> isPartOf=List::contains;
		BiPredicate<List<Integer>,Integer> isPartOfLambda=(List<Integer> listInt, Integer value) -> listInt.contains(value);
		System.out.println("Is 1 a part of the intList - "+ isPartOf.test(intList, 1));
		System.out.println("Is 1 a part of the intList - "+ isPartOfLambda.test(intList, 1));

	}
	
	private static void supplierExample(){
		Supplier<String> supplier = () -> new String("This is supplied from the interface");
		
		System.out.println(supplier.get());
		
		//any method of an existing class can be invoked using the method reference syntax.
		//But method reference should be assigned to a functional interface.
		//method references are simplied form of lambda where the input and return types are not specified
		Supplier<String> supplier2 = ConsumerFunction :: supplierFunction;
		System.out.println(supplier2.get());
		
		 // Assignment context
	     Predicate<String> p = String::isEmpty;
	}
	
	private static String supplierFunction() {
		return new String("This is supplied from the interface");
	}
	
	private static void predicateExample() {
		Predicate<String> predicate = s -> s.contains("Java");
		
		List<String> techname = Arrays.asList("Java Script", "JDK", "Java8");
		
		//use predicate function to print only the list items that have java
		for (String name: techname) {
			if (predicate.test(name)) System.out.println(name);
		}
		
		// Compile regex as predicate
        Predicate<String> emailFilter = Pattern
                                        .compile("^(.+)@example.com$")
                                        .asPredicate();
 
        // Input list
        List<String> emails = Arrays.asList("alex@example.com", "bob@yahoo.com", 
                            "cat@google.com", "david@example.com");
 
        // Apply predicate filter
        List<String> desiredEmails = emails
                                    .stream()
                                    .filter(emailFilter)
                                    .collect(Collectors.toList());
 
        // Now perform desired operation
        desiredEmails.forEach(System.out::println);
	}
	
	private static void consumerExample() {
		Consumer<Integer> consumer = i -> System.out.print("Main: " + i); //input for Consumer interface is Integer and logic is to print.
		Consumer<Integer> consumerAndThen = consumer.andThen(i -> System.out.print("(then " + i + ")"));
		List<Integer> integerList = Arrays.asList(Integer.valueOf(1), Integer.valueOf(110), Integer.valueOf(122),
				Integer.valueOf(11), Integer.valueOf(33));
		printList(integerList, consumerAndThen); //consumer object has the logic to print
	}
	public static void printList(List<Integer> integerList, Consumer<Integer> consumer) {
		for (Integer num: integerList) {
			consumer.accept(num); //Accept method works as per the lambda definition assigned to the Consumer interface
		}
	}
//Source code of Consumer Interface
//@FunctionalInterface
//public interface Consumer<T> {
//    void accept(T t);
//    default Consumer<T> andThen(Consumer<? super T> after) {
//        Objects.requireNonNull(after);
//        return (T t) -> { accept(t); after.accept(t); };
//    }
//}
}
