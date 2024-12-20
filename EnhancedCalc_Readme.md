Enhanced Calculator:
The Enhanced Calculator is a Java-based console application designed for performing various mathematical operations. It provides a calculation history with timestamps, day of the week, and date for each operation, offering an intuitive and user-friendly experience.

Features:
Basic Arithmetic Operations:
Perform addition, subtraction, multiplication, division, and modulus operations.

Advanced Operations:

Power (x^y)
Square Root (√x)
Calculation History:

Keeps a record of all calculations performed.
Includes timestamps, day of the week, and dates.
User-friendly Interface:

Clear prompts for input and operations.
Handles invalid inputs gracefully with descriptive error messages.

How to Run
1.Clone the Repository:
            git clone https://github.com/<your-username>/Enhanced-Calculator.git  
cd Enhanced-Calculator

2.Compile the Java Code:
Make sure Java is installed on your system. Compile the program using:  
  javac EnhancedCalculator.java

3.Run the Program:
Execute the compiled program using:
  java EnhancedCalculator


  
  
  Operation Menu
The calculator supports the following operations:

Addition (+)
Subtraction (-)
Multiplication (*)
Division (/)
Modulus (%)
Power (x^y)
Square Root (√x)
Simply select the operation by entering the corresponding number when prompted.

Example Output

========================================
       Welcome to Enhanced Calculator
========================================

New Calculation:
----------------
Enter the first number: 5
Select an operation:
1. Addition (+)
2. Subtraction (-)
3. Multiplication (*)
4. Division (/)
5. Modulus (%)
6. Power (^)
7. Square Root (√)
Enter your choice (1-7): 1
Enter the second number: 3

Result: 5.00 + 3.00 = 8.00

Would you like to perform another calculation? (yes/no): no

========================================
           Calculation History
========================================
Total calculations performed: 1
[1] Operation: 5.00 + 3.00 = 8.00
    Time: 14:30:12
    Day: MONDAY
    Date: 2024-12-20

========================================
      Thank you for using our tool!
========================================


Error Handling
Division by Zero: Displays an error message and skips the calculation.
Invalid Inputs: Prompts the user to enter valid numbers or operations.
Square Root of Negative Numbers: Displays an error for invalid operations.


Requirements
Java Development Kit (JDK) 8 or higher.
A terminal or console to run the program.


Contributions
Contributions are welcome! If you have ideas for new features or improvements:

Fork the repository.
Make your changes.
Submit a pull request with your modifications.


License
This project is licensed under the MIT License. Feel free to use, modify, and distribute the software.





