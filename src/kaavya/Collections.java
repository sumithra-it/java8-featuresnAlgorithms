package kaavya;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Collections {

public Collections() {
// TODO Auto-generated constructor stub
}
public static Predicate<String> containsPredicate(final String charseq) {
return name -> name.contains(charseq);
}
public static void main(String[] args) {
final List<String> treats = Arrays.asList("Ice Cream", "Frozen Custard", "Italian Ice", "MilkShakes", "Cake",
"Pretzel", "Churros");

final List<String> groceries = Arrays.asList("Sugar", "Ice Flavor Mix", "Custard Mix", "Water", "Chocolate Chips", "Chocolate melts");
/*
* //old way - Imperative for (String treat: treats) {
* System.out.println(treat); }
*/

System.out.println("Declarative way of iteraiting inside list - Upper Case");
treats.forEach(new Consumer<String>() {
public void accept(String treat) {
System.out.println(treat.toUpperCase());
}
});// end forEach

System.out.println("Test 1 - Using Lambda: way of iteraiting inside list - Lower Case");
treats.forEach((String treat) -> System.out.println(treat.toLowerCase())); 

System.out.println("Using Lambda: code in one line with ::");
treats.forEach(System.out :: println);

//Transform lists
System.out.println("Test 2.1 - Transform Lists:");
treats.stream()
.map(String :: toLowerCase)
.forEach(System.out :: println);

System.out.println("Test 2.2 - Transform Lists and return a new list of different type:");
treats.stream()
.map(treat -> treat.length())
.forEach((num) -> System.out.println("Length of characters in each treat is: " + num) );

System.out.println("Transform Lists and return a new list of different type. Using Lambda:");
treats.stream()
.map(String :: length) //returns iterator
.forEach((num) -> System.out.println("Length of characters in each treat is: " + num) );

System.out.println("Test 3 - Filter n Transform Lists based on criteria");
List<String> final_list = 
treats.stream()
.filter(treat -> treat.startsWith("I"))  //filter's lambda expressions returns a boolean. Filter returns the iterator
.collect(Collectors.toList()); // Collect() returns a Collection 
System.out.println("Total # number of treats with 2 words is: " + final_list.size()); 

System.out.println("Test 4 - Reusing Lambda expressions as function declarations");
final Predicate<String> startswithFilter = name -> name.startsWith("I");
final long count = treats.stream()
.filter(startswithFilter)
.count();
System.out.println("Count of items that start with I: " + count);


System.out.println("Test 5 - Reusing Lambda expressions - Lexical Scoping with static method");
final long count2 = treats.stream()
.filter(containsPredicate("Ice"))
.count();
System.out.println("Count of treats that contain phrase Ice: " + count2);
long count3 = groceries.stream()
.filter(containsPredicate("Mix"))
.count();
System.out.println("Count of groceries that contain phrase Mix: " + count3);


System.out.println("Test 6 - Reusing Lambda expressions - Lexical Scoping - with local Function<Input value, Return value");
Collections thisClass = new Collections();
thisClass.lambdaInsideFunction(treats, groceries);


System.out.println("Test 6 - Streams api for selecting an item in the list");
//Optional Class, Streams API and Optional.orElse to prevent NullPointerException  
final Optional<String> treatFound = 
treats.stream()
.filter(treat -> treat.startsWith("Ice"))
.findFirst();
System.out.println(String.format("First of the treats that contain phrase Ice is %s " , 
treatFound.orElse("None found"))); 
treatFound.ifPresent((treat) -> System.out.println("Treat that starts with Ice was found: " + treat)); 


System.out.println("Test 7 - Streams api to reduce opertaion");
count3 = 
treats.stream()
.mapToInt(name -> name.length())
.sum();
System.out.println("total alphabets in treats are: " + count3);

System.out.println("Test 8.1 - Streams api using reduce API");
Optional<String> longtreat = 
treats.stream()
.reduce((treat1, accumulator) -> 
treat1.length() > accumulator.length() ? treat1 : accumulator);
longtreat.ifPresent(name -> System.out.println("Longest treat is: " + name)); 


System.out.println("Test 9 - Streams api - collect and joining API");
String treatFormatted = 
treats.stream()
.map(String :: toUpperCase)
.collect(Collectors.joining(", "));
System.out.println("Joined Treat list is:" + treatFormatted);

}// end main

public void lambdaInsideFunction(List<String> treats, List<String> groceries){
long count = treats.stream()
.filter(containsFunction.apply("Ice")).count();
System.out.println("Count of treats have phrase ICE is: " + count);

count = groceries.stream()
.filter(containsFunction.apply("Mix")).count();
System.out.println("Count of Groceries have phrase Mix is: " + count);
}

//function returning a function
public Function<String, Predicate<String>> containsFunction = (charSeq) -> name -> name.contains(charSeq);

}// end class
