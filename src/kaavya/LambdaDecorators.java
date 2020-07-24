package kaavya;

import java.awt.Color;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

public class LambdaDecorators {
	private Function<Color, Color> filter;

	public Color applyColor(Color inputColor) {
	Color finalColor = filter.apply(inputColor);
	return finalColor;
	}

public void setFilters(Function <Color, Color>... filters ) {
filter = Stream.of(filters)
.reduce((filter, nextfilter) -> filter.compose(nextfilter))
// .orElse(sumi -> sumi); //dummy 
.orElseGet(Function :: identity); //identity() - Returns a function that always returns its input argument.
}
//constructor
public LambdaDecorators() { setFilters(); }
public static void main(String args[]) {
LambdaDecorators colorDecorator = new LambdaDecorators();

Consumer<String> print = (filterInfo) -> 
System.out.println(String.format("With %s: %s", filterInfo, 
colorDecorator.applyColor(new Color(200, 100, 200))));
//Consumer<> Interface Represents an operation that accepts a single input argument and returns no result
print.accept("none of the filters");

//send one filter/color
colorDecorator.setFilters(Color :: brighter);
print.accept("Bright filter");

//multiple decorators
colorDecorator.setFilters(Color::brighter, Color :: darker);
print.accept("Bright n Dark filter");
}

}