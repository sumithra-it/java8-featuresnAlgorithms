package kaavya;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Streams {
	private static void printCharInt(int intChar) {
		System.out.print((char) intChar);
	}
	public static void main(String[] args) {
	final String str = "Sai1926Baba";

	//String operations using streams
	List<Person> peopleList = new ArrayList<Person>();
	peopleList.add(new Person(" ", 25));peopleList.add(new Person("Kaavya", 14));peopleList.add(new Person("Raam", 18));
	printPeople("New List:", peopleList);

	//filter empty strings
	List<Person> filtered = peopleList.stream().filter(p -> p.name != " ").collect(Collectors.toList());
	printPeople("Filtered list for empty names:", filtered);
	
	System.out.println("Test 1 - lang.Character class acting on int/char with maptoObject");
	str.chars()
		.mapToObj(c -> Character.toChars(c))
		.forEach(System.out :: print);
	System.out.println("\nNames List:");
	List<String> names = peopleList.stream().map(Person :: getName).collect(Collectors.toList());
	names.forEach(System.out :: print);
	
	System.out.println("\nTest 1.2");
	str.chars()
		.mapToObj(c -> Character.valueOf((char)c))
		.forEach(System.out :: print);

	System.out.println("\nTest 2 - Using static functional interface with filter");
	str.chars()
		.filter(Character :: isDigit)
		.forEach(c -> printCharInt(c));
	System.out.println("\nTest 2.2 - Filter");
	str.chars()
		.filter(Character :: isAlphabetic)
		.forEach(Streams :: printCharInt);

	System.out.println("\nTest 3 - Comparator Interface");
	List <Person> people = Arrays.asList(new Person("Sumithra", 47), new Person("Pravin", 50),
										 new Person("Kaavya", 10), new Person("Raam", 10));
	List<Person> sortedPeople = 
								people.stream()
								.sorted((Person person1, Person person2) -> person1.AgeDifference(person2))
								//.sorted((person1, person2) -> person1.AgeDifference(person2))
								.collect(Collectors.toList());
	System.out.println("Sorted List of people is: " + sortedPeople);

	System.out.println("Test 3.2 - Using method references");
	sortedPeople = 
					people.stream()
					.sorted(Person :: AgeDifference)
					.collect(Collectors.toList());
	printPeople("Sort simplified with methods references for sorted() API", sortedPeople);

	System.out.println("Test 4 - Multple comparisons using comparing() and thenComparing() methods");
	Function<Person, String> byName = person -> person.getName();
	Function<Person, Integer> byAge = person -> person.getAge();
	sortedPeople = 
					people.stream()
					.sorted(Comparator.comparing(byAge).thenComparing(byName))
					.collect(Collectors.toList());
	printPeople("Sort first by Age and then by name", sortedPeople); 

	System.out.println("Test 5 - Grouping By");
	Map<Integer, List<Person>> mapofPeople = 
					people.stream()
					.collect(Collectors.groupingBy(Person :: getAge));
	System.out.println("People by grouped/mapped by age:" + mapofPeople);

	System.out.println("Test 6 - Grouping By and mapping");
	Map<Integer, List<String>> mapbyagename = 
					people.stream()
					.collect(Collectors.groupingBy(
								Person :: getAge, 
								Collectors.mapping(Person :: getName, Collectors.toList())
							));
	System.out.println("People by grouped/mapped by age:" + mapbyagename);
	System.out.println("\nTest 7 - Stream map");
	List<Integer> intList = people.stream().map((Person p) -> p.age + 20).collect(Collectors.toList());
	intList.forEach((i) -> System.out.println(i));
	System.out.println("\nTest 7.2 - Stream map");
	List<String> strList = people.stream().map((Person p) -> p.name.concat("MS.")).collect(Collectors.toList());
	strList.forEach((i) -> System.out.println(i));
	
	System.out.println("\nTest 7.2 - Summary stats");
	IntSummaryStatistics agestats = people.stream().mapToInt((p) -> p.age).summaryStatistics();
	System.out.println("Age range: " + agestats.getMax() + " to " + agestats.getMin() + 
			" with average: " + agestats.getAverage());
	
	System.out.println("\nTest 8 - Peek operation on Stream with stream.iterate");
	List<Integer> numbers = Stream.iterate(1, (Integer n) -> n + 1)
	    .peek(n -> System.out.println("number generated: - " + n))
	    .filter(n -> (n % 2 == 0))
	    .peek(n -> System.out.println("Even number filter passed for - " + n)).limit(5)
	    .collect(Collectors.toList());
	System.out.println("Final list of numbers:");
	numbers.forEach((p) -> System.out.print(p +" ,"));

	System.out.println("\nTest 9 - stream.generate method:");
	Stream.generate(Math :: random).limit(5).forEach(i -> System.out.print(i + ", "));
	
	System.out.println("\nTest 10 - flatmap - Stream<Stream<R>> becomes Stream<R>");
	//returns as output a stream of type R which is actually a flattened stream containing the elements in all the streams of type R obtained when mapper is applied on the input stream’s elements of type T
	List<String> nameCharList = people.stream()
	           .map(emp-> emp.getName().split(""))
	           .flatMap(array->Arrays.stream(array))
	           .map(str2 -> str2.toUpperCase()) 
	           .filter(str3 -> !(str3.equals(" ")))
	           .collect(Collectors.toList());
	
	nameCharList.forEach(str4 -> System.out.print(str4 + ","));
	
	System.out.println("\nTest 11 - Stream.reduce - to combine elements repeatedly to produce a single result");
	int oldest = people.stream().map(p -> p.age).reduce(0, (age1, age2) -> age1 < age2 ? age2:age1);
	System.out.println("Oldest was " + oldest + " years");
	Optional<Person> person = people.stream().filter(p -> (p.age == oldest)).findAny();
	if (person.isPresent()) System.out.println("Person was: " +person.get().name);
  }

	public static void printPeople(String message, List<Person> peopleList) {
		System.out.println(message);
		//peopleList.forEach(System.out :: println);
		peopleList.forEach((Person p) ->  System.out.println("Name:" + p.name + " Age:" + p.age));	

	}
	
}//end main class

	class Person {
		final String name;
		final int age;
		public Person (String name, int age) {
			this.name = name; this.age = age;
		}
		public int AgeDifference(final Person person2) {
			return this.age - person2.age;
		}
		public int getAge() { return this.age; }
		public static int fetchAgeDiff(Person person1, Person person2) {
			return person1.age - person2.age;
		}
		public String getName() {return this.name;}
		public String toString() {return String.format("Name %s - Age %d", name, age); }
	} //end Person
