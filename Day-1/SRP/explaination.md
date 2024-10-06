# Understanding the Single Responsibility Principle (SRP) with an Example

The **Single Responsibility Principle (SRP)** is one of the five SOLID principles of object-oriented design, aimed at building maintainable, scalable, and robust systems. SRP ensures that a class or module has only one reason to change, meaning it should have only one responsibility. This leads to a clean and modular codebase that is easier to manage.

## What is the Single Responsibility Principle (SRP)?

The **Single Responsibility Principle** states:

> "A class should have only one reason to change, meaning it should have only one responsibility or perform a single, well-defined task."

In simpler terms, each class should focus on doing one thing and doing it well. By adhering to SRP, we can avoid unnecessary complexity and tightly coupled code, leading to more flexible and maintainable systems.

## Example Scenario: Car and Insurance System

Let's walk through an example where we have two entities:
1. **Car**: A class that represents a car, storing attributes like model, brand, year, and price.
2. **Insurance**: A class responsible for calculating the insurance premium based on the attributes of the car.

We will design these classes to follow SRP.

## Initial Design

### `Car` Class

The `Car` class will be responsible for holding car details and nothing else. Here’s how this class is designed to adhere to SRP:

```java
public class Car {
    String model;
    String brand;
    int year;
    int price;
    
    // Constructor to initialize the Car object
    public Car(String model, String brand, int year, int price) {
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }
}
```

- The **responsibility** of the `Car` class is to store details about the car.
- It does not handle insurance calculation or any other unrelated tasks, ensuring it adheres to SRP.

### `Insurance` Class

Next, we define the `Insurance` class, which will calculate the insurance premium for a car based on its attributes:

```java
public class Insurance {
    private Car car;  // Reference to a Car object
    private int baseValue = 5000;  // Base value for insurance calculation

    // Constructor to initialize the Insurance object
    public Insurance(Car car) {
        this.car = car;
    }

    // Method to calculate and display insurance value based on car year
    public void calculateInsurance() {
        if (this.car.year <= 2020) {
            System.out.println("The insurance for this car of the model year " + car.year + " is: " + baseValue * 2);
        } else {
            System.out.println("The insurance for this car model is: " + baseValue);
        }
    }
}
```

- The **responsibility** of the `Insurance` class is to calculate the insurance premium.
- It does not modify the car's attributes but instead uses the car's data to perform its calculations.
- This class also follows SRP because its focus is strictly on insurance-related logic.

## Why the Design Follows SRP

### `Car` Class Responsibility
- The `Car` class is only responsible for storing car details like model, brand, year, and price.
- It doesn’t deal with insurance or any other unrelated tasks.
- **Adherence to SRP**: Any change in car details (e.g., adding new attributes like mileage) will only affect the `Car` class.

### `Insurance` Class Responsibility
- The `Insurance` class handles only the insurance premium calculation based on the car's attributes.
- It does not modify or interact with the car beyond accessing its information for calculations.
- **Adherence to SRP**: Any change in how the insurance is calculated will affect only the `Insurance` class.

## Suggestions to Improve Flexibility

While the current code follows SRP, there are some improvements we can make for future scalability and maintainability.

### 1. Decouple Insurance Calculation Logic

Insurance calculation logic can become more complex over time. To accommodate such future complexity, we can decouple the calculation logic from the `Insurance` class and move it into a separate `InsuranceCalculator` class:

```java
public class InsuranceCalculator {
    // Method to calculate the premium based on car attributes
    public int calculate(Car car, int baseValue) {
        if (car.getYear() <= 2020) {
            return baseValue * 2;
        } else {
            return baseValue;
        }
    }
}
```

This way, the `Insurance` class only manages the insurance policy, while the actual calculation is handled elsewhere:

```java
public class Insurance {
    private Car car;
    private int baseValue = 5000;
    private InsuranceCalculator calculator;

    // Constructor to initialize Insurance with a Car object
    public Insurance(Car car, InsuranceCalculator calculator) {
        this.car = car;
        this.calculator = calculator;
    }

    // Method to display the insurance premium
    public void displayInsurance() {
        int premium = calculator.calculate(car, baseValue);
        System.out.println("The insurance for the car model is: " + premium);
    }
}
```

This **decoupling** allows us to modify the calculation logic separately, without impacting the `Insurance` class.

### 2. Encapsulate Car Attributes

Currently, the `Insurance` class directly accesses the `car.year` attribute. A better approach would be to encapsulate these details using getter methods. This not only hides the internal implementation but also makes it easier to add future enhancements to how attributes are accessed.

Here’s the improved `Car` class with encapsulated fields:

```java
public class Car {
    private String model;
    private String brand;
    private int year;
    private int price;
    
    public Car(String model, String brand, int year, int price) {
        this.model = model;
        this.brand = brand;
        this.year = year;
        this.price = price;
    }

    // Getter for the year
    public int getYear() {
        return year;
    }

    // Other getters can be added here as needed
}
```

In the `Insurance` class, we now use the getter to access the year:

```java
public void displayInsurance() {
    int premium = calculator.calculate(car, baseValue);
    System.out.println("The insurance for the car model year " + car.getYear() + " is: " + premium);
}
```

This encapsulation follows best practices and makes the system more flexible for future changes.

## Conclusion

The design adheres to the **Single Responsibility Principle** by ensuring that each class has one clearly defined responsibility:
- The `Car` class stores car details.
- The `Insurance` class calculates insurance premiums.

We also discussed improvements such as **decoupling the insurance calculation logic** and **encapsulating car attributes**. These changes would make the code more flexible, scalable, and maintainable for future enhancements.

By following SRP, we ensure that our code is modular and easier to maintain. Changes to one part of the system do not unnecessarily affect other parts, resulting in fewer bugs and greater ease in extending the system.
