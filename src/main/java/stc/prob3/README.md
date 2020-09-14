Problem 3
===

There is a group of employees who take a walk after lunch. As the weather is getting hot, they decided to play a ladder game to decide who will buy the ice cream for the whole group. Mr. Kim decided to draw the ladder instead of participating in the game. After drawing the ladder, he wondered who would end up with 'X' and buy the ice cream. Create a program to find it. 
In the example shown in 
. arbitrary vertical lines are added between two vertical lines with the starting points of x=0 and x=9 (two lines added in this example). Horizontal lines are randomly drawn between these vertical lines. The case of starting from x=0 will proceed as the arrow indicates. It goes down until it reaches a path to the right or left and makes the turn accordingly. Then it goes down again, makes a turn as needed, and then goes down. It is repeated until the bottom point is reached. To reach the 'X' mark, one has to start at the point x=4. Therefore the answer would be 4.   Its path is shown in orange color.

![](images/problem3_1.jpg)

Create a program returning a starting point X which will arrive a given destination in a ladder presented in a form of 100x100  2 dimensional matrix as shown in Figure 2. (The ladder is expressed in continuous ‘T' on the surface filled with '0'. The arrival point is expressed in '2')

![](images/problem3_2.jpg)

**Constraints**

A horizontal line beginning on a vertical line never crosses over another vertical line.

**Input**

The first line of the input file provides the test case number. The test case is given in next lines. Total of 10 test cases are given.

**Output**

The output file outputs the test case number following the '#' symbol. It is followed by a space, and then the x coordinate of the correct starting point is output.

Input Example
---

![](images/problem3_3.jpg)

Output Example
---

```text
#1 67
…
```