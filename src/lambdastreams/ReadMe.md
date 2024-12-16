###### Given a stream of objects, write a function using map that extracts a specific property from each object and returns a new stream containing only those extracted values.
Example:

```Java

// Assuming a class Person with name and age properties
List<Person> people = ...;  // List of people objects

Stream<String> namesStream = people.stream()
  // Implement map function here
  .collect(Collectors.toList());
```

###### Given a stream of integers, write a function using a map that squares each element and returns a new stream containing the squared values.

```Example:

Input stream: 1, 2, 3, 4
Output stream: 1, 4, 9, 16
Ans : list.stream().map(i-> i*i).collect(Collector.toList())
Given a stream of strings, write a function using a map that converts all characters in each string to uppercase 
and returns a new stream containing the modified strings. 

Example:

Input stream: "hello", "world", "java" 
Output stream: "HELLO", "WORLD", "JAVA" 

list.stream().map(str-> str.toUpperCase()).collect(Collector.toList())

```

----------------------------------


###### Given a stream of objects, write a function using map that extracts a specific property from each object and returns a new stream containing only those extracted values.

```
Example:

Java
// Assuming a class Person with name and age properties
List<Person> people = ...;  // List of people objects

Stream<String> namesStream = people.stream()
  // Implement map function here
  .collect(Collectors.toList());

// namesStream should contain: ["Alice", "Bob", "Charlie"] (assuming people have these names)


Stream<String> namesStream = people.stream()
.map(obj->obj.getName())
 .collect(Collectors.toList());

```

###### Given a stream of integers, write a function using map and filter that removes all even numbers and squares the remaining odd numbers in the new stream.

```
Example:

Input stream: 1, 2, 3, 4, 5
Output stream:1, 9, 25
people.stream()
  .filter(i -> i % 2 != 0) // Filter even numbers
  .map(i -> i * i)           // Square remaining odd numbers
  .collect(Collectors.toList());
```

###### Given a stream of strings, write a stream operation that groups the strings by their length.The resulting data structure should be a Map where the key is the string length (integer) and the value is a List containing all strings of that length.

```

Input stream: "apple", "banana", "cherry", "tomato", "kiwi"

Output Map: {
  3: ["kiwi"],
  5: ["apple", "cherry"],
  6: ["banana", "tomato"]
}



Map<Integer, List<String>> groupedByLength = list.stream()
  .collect(Collectors.groupingBy(String::length));

```
###### Given a stream of strings, group them by their first character (case-insensitive)

```
Input: "apple", "banana", "cherry", "tomato", "kiwi"

Output: {
  'a': ["apple"],
  'b': ["banana"],
  'c': ["cherry"],
  'k': ["kiwi"],
  't': ["tomato"]
}

input.stream().collect(Collector.groupingBy(str -> str.substring(0,1)))
```


###### Given a stream of strings, group them by their length. The resulting data structure should be a Map where the key is the string length (integer) and the value is a List containing all strings of that length. 

list.stream().collect(Collector.groupinBy(String::length))

```
Given a stream of custom objects, group them by a specific property value.

Example (assuming a Product class with category property):


Map<String, List<Product>> groupedByCategory = products.stream()
  .collect(Collectors.groupingBy(Product::getCategory));

```

###### Group the Person objects by their age. The resulting data structure should be a Map where the key is the age (Integer) and the value is a List containing all Person objects with that specific age.

```
people .stream().collector(Collector.groupingBy(Person::getAge))
```



###### Group the strings by their length, but only include strings with a length exceeding a certain threshold.


```
// Assuming a list of strings and a threshold of 5 characters
List<String> words = Arrays.asList("apple", "banana", "cherry", "tomato", "mango");
int threshold = 5;

// Expected output Map (only containing words with length exceeding threshold)
{
  6: ["banana", "tomato"],
  5: ["mango"]
}

words.stream().filter(str -> str.length > thresholdValue).collect(Collector.groupingBy(str -> str.length())

Another solution 


Collector<String, ?, Map<Integer, List<String>>> filterThenGroupBy = Collectors.groupingBy(
  String::length,
  Collectors.filtering(word -> word.length() > threshold, Collectors.toList())
);

Map<Integer, List<String>> groupedByLength = words.stream()
  .collect(filterThenGroupBy);
```


###### Group the orders by the product and calculate the total quantity for each product. The resulting data structure should be a Map where the key is the Product object and the value is the total quantity ordered for that product.

```
// Assuming a list of Order objects with product and quantity
List<Order> orders = Arrays.asList(
    new Order(new Product("apple"), 2),
    new Order(new Product("banana"), 3),
    new Order(new Product("apple"), 1),
    new Order(new Product("cherry"), 5)
);

// Expected output Map
{
  Product(name="apple"): 3,
  Product(name="banana"): 3,
  Product(name="cherry"): 5
}



orders.stream().collect(Collector.groupingBy(Orders ::getProduct,
														 Collector.sunmmingInt(Orders :: getQuantity)))
```


##### Collector Class Methods

###### The Collectors class provides a rich set of methods for performing various reduction operations on streams. These operations involve transforming and accumulating elements into a collection or a single summary value. Here are some key methods:


###### toList(): 
Collects elements into a List.

###### toSet(): 
Collects elements into a Set.

###### toMap(Function keyMapper, Function valueMapper): 
Collects elements into a Map by applying key and value mappers to each element.

###### counting(): 
Counts the number of elements in the stream.

###### averagingInt(ToIntFunction mapper): 
Calculates the average of integer-valued functions applied to the elements. Similar methods exist for averagingDouble and averagingLong.

###### minBy(Comparator comparator): 
Returns the minimum element according to a provided comparator.

###### maxBy(Comparator comparator): 
Returns the maximum element according to a provided comparator.

###### groupingBy(Function classifier): 
Groups elements based on a classification function. Can be used with downstream collectors for further processing within groups.

###### partitioningBy(Predicate predicate): 
Partitions elements into two groups based on a predicate (true or false).

---------------



###### Group the integers by their absolute value. The resulting data structure should be a Map where the key is the absolute value (Integer) and the value is a List containing all integers with that absolute value.

```

List<Integer> numbers = Arrays.asList(1, -2, 3, -1, 4);

// Expected output Map:
{
  1: [1, -1],
  2: [-2],
  3: [3],
  4: [4]
}

numbers.stream().collect(Collector.groupingBy(Math::abs))

```

###### Group the customers by country and then by city within each country. The resulting data structure should be a nested Map where the outer key is the country (String) and the inner key is the city (String). The value at each level is a List containing the corresponding Customer objects.

```

// Assuming a list of Customer objects with city and country properties
List<Customer> customers = Arrays.asList(
  new Customer("Alice", "London", "UK"),
  new Customer("Bob", "Paris", "France"),
  new Customer("Charlie", "London", "UK"),
  new Customer("David", "Berlin", "Germany")
);

// Expected output Map:
{
  "UK": {
    "London": [Customer(name="Alice", city="London", country="UK"), Customer(name="Charlie", city="London", country="UK")],
  },
  "France": {
    "Paris": [Customer(name="Bob", city="Paris", country="France")],
  },
  "Germany": {
    "Berlin": [Customer(name="David", city="Berlin", country="Germany")],
  }
}
customers.stream().collect(Collector.groupingBy(Customer :: getCountry()), 
                     Collector.groupingBy(Customer :: getCity(), Collector.toList())

```

###### Group the products by price range. The price range should be determined by dividing the price by 100 (rounded down to the nearest integer) and using that value as the key. The resulting data structure should be a Map where the key is the price range (Integer) and the value is a List containing all products within that price range.

```
// Assuming a list of Product objects with price property
List<Product> products = Arrays.asList(
  new Product("Laptop", 1200),
  new Product("Phone", 500),
  new Product("Headphones", 150),
  new Product("Watch", 350)
);

// Expected output Map:
{
  12: [Product(name="Laptop", price=1200)],  // Price range 12
  5: [Product(name="Phone", price=500)],     // Price range 400-499
  1: [Product(name="Headphones", price=150)], // Price range 100-199
  3: [Product(name="Watch", price=350)]       // Price range 300-399
}

products.stream().collect(Collector.groupingBy(product -> product.getPrice()/100, 
                                                Colector.toList()))

```
###### Group the entries in this Map by the average value of the integer Lists.The resulting data structure should be a Map<Double, List<String>> where the key is the average value (Double) and the value is a List of Strings (keys from the original Map) whose corresponding lists had that average value.

```

Map<String, List<Integer>> data = new HashMap<>();
data.put("A", Arrays.asList(1, 2, 3));
data.put("B", Arrays.asList(4, 5));
data.put("C", Arrays.asList(1, 1));

// Expected output Map:
{
  2.0: ["A"],
  4.5: ["B"],
  1.0: ["C"]
}

data.entrySet().stream().collect(Collector.groupingBy(entry -> entry.getValue().stream().mapToDouble(i -> i.doubleValue()).average().orElse(0.0))
OR 

Map<Double, List<String>> groupedByAverage = data.entrySet().stream()
  .collect(Collectors.groupingBy(entry -> {
    Double average = entry.getValue().stream()
      .mapToDouble(Integer::doubleValue)
      .average()
      .orElseThrow(() -> new IllegalArgumentException("Empty list average"));
    return average;
  }));

```
##### Map Manipulation Challenge (flatMap + filtering)
Scenario: You have a Map<String, List<Product>> where the key is a category (String) and the value is a List of Product objects.

Task: Create a new Map where the key remains the same (category) but the value is a List containing only the names (Strings) 

of products with a price exceeding a certain threshold.

```
Map<String, List<Product>> productsByCategory = new HashMap<>();
productsByCategory.put("Electronics", Arrays.asList(
  new Product("Laptop", 1200),
  new Product("Phone", 500),
  new Product("Headphones", 150)
));
productsByCategory.put("Clothes", Arrays.asList(
  new Product("Shirt", 30),
  new Product("Jeans", 80)
));

int priceThreshold = 400;

// Expected output Map:
{
  "Electronics": ["Laptop"],
  "Clothes": ["Jeans"]
}



productsByCategory.entrySet().stream().collect(Collector.toMap(entry-> entry.getKey(), 
                                               entry.getValue().stream().filter(product -> product.getPrice() > limit)
                                               .map(product-> product.getName())
                                               .collect(Collector.toList())
                                              )
```

```
Example 2 using toMap

List<Person> people = Arrays.asList(
  new Person("Alice", 25),
  new Person("Bob", 30),
  new Person("Charlie", 25)
);

// Key extraction function: extract name as key
// No value transformation (identity function)
Map<String, Person> nameToPersonMap = people.stream()
  .collect(Collectors.toMap(Person::getName, person -> person));

System.out.println(nameToPersonMap); 

// Output: {Alice=Person(name=Alice, age=25), Bob=Person(name=Bob, age=30), Charlie=Person(name=Charlie, age=25)}

```

##### What is flatMap?

flatMap is an intermediate operation in Java streams that allows you to transform each element 

in a stream into a stream of zero or more elements, and then flatten all the resulting sub-streams into a single new stream.

```
List<String> words = Arrays.asList("apple banana cherry".split(" "));

// Without flatMap (clumsy):
List<String> uniqueLetters = new ArrayList<>();
for (String word : words) {
  for (char letter : word.toCharArray()) {
    if (!uniqueLetters.contains(letter + "")) {
      uniqueLetters.add(letter + "");
    }
  }
}

// With flatMap (more concise):
List<String> uniqueLetters = words.stream()
  .flatMap(word -> Arrays.stream(word.split(""))) // Split each word into a stream of characters
  .distinct() // Remove duplicates
  .collect(Collectors.toList());

  ```