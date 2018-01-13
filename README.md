# 2018 Western Engineering Competition

*Adam Kovalcik, Colin Nowers, Colin Russell & Dane Sisinni*

## Our Solution
We solved the problem by converting the raw json into a 3 dimensional character array, representing the grid. 
The number of ships and respective sizes were computed with a 3-D depth first search and stored in a hash map. 
In order to determine the laser path of maximum destruction, all possible paths along the 3 planes (X, Y, Z) 
were examined and the maximum destruction path returned.

The program was designed as a Java Web application, with a REST API providing easy access to the algorithm and
front-end interface for data visualization.

### Technologies Used
* **Backend:** Java, Spring Boot, Guava, Lombok, json-simple
* **Frontend:** HTML, CSS, Javascript, JQuery, plotly.js

## The Problem

### Background:
The remainder of the Rebel fleet has stumbled into an Imperial ambush. Armed with the
Death Star, the Imperials must now destroy as many of the Rebel ships as possible before
they can make the jump to lightspeed and escape. This leaves enough time to charge and fire
one shot from the Death Star laser cannon, so the Imperials need to make that single shot
count.

### Solution Requirement:
As weapons master aboard the Death Star, it is your job to determine, or rather write a
program that will determine, where the cannon should be aimed so as to inflict as much
damage to the fleet as possible. The Death Star’s sensors are able to determine where the
Rebel ships are located, and return this data in the form of an arbitrarily-sized
three-dimensional array of 1’s and 0’s; a 1 representing a sector of space where ship matter
was detected, and a 0 representing the cold void of outer-space. Therefore, any triple
coordinate (x, y, z) can represent a “cube” in a three dimensional space which either contains
part of a ship or not, and corresponds to the contents of the array at [x][y][z]. Cells adjacent
to each other (i.e. cubes adjoined to each other by faces, not by edges or vertices) represent
the same ship.

A single ship is thereby represented as a polycube, a solid figure formed by joining equal
cubes face to face. A ship can be arbitrarily big, and as small as a single cube. As long as a
collection of cubes is somehow connected by their faces, they count as one ship.

The Death Star laser cannon is extremely powerful, but limited in the way it can fire. It fires
in a straight line, and the beam will destroy any ship that it intersects with. That is, if the
beam hits any part of a ship (i.e. any “cube” that part of a ship occupies), the entire ship is
destroyed. The three dimensional space will be in the form of a rectangular prism, and the
laser can be fired orthogonal to any of the six surfaces of the prism and will travel through the
entire space. The beam itself is one “cube” wide, and will destroy any ships that lie on the
line of its shot.

Larger ships that consist of multiple cubes are of more strategic worth to the Rebels, so your
program should prioritize destroying these and find the direction of fire that will destroy as
many, and as large of ships as possible. For example, destroying five ships each consisting of
one cube would be the same net destruction as destroying one ship consisting of five cubes,
or a ship of three cubes and a ship of two cubes. In each scenario, the total Destruction Score
would be 5.

The final program should calculate the maximum attainable Destruction Score within the
confines of the problem, as well as the position and direction where the cannon should be
fired to achieve that score.

### Constraints:
Contestants will be allowed to use any current industry-standard programming language such
as C++, Java, Python, etc. However, it must be easy for people to run and use.

### Included file:
A JSON file “scanner_data.json”. This JSON file contains a JSON object that contains a
finite amount of JSON arrays. Each array corresponds to a layer in the k̂ unit vector
direction. Each array contains a finite amount of binary strings. Each binary string represents
a complete grid line in the î​ ​unit vector direction. Thus, the number of binary strings in a
JSON array represents the length of the ĵ ​unit vector direction. You may assume that the
given data represents a rectangular prism (all binary strings are of the same length).

```
Ex. [ (y = 1) (y = 2) (y = 3)
[“01001”, “01100”, “00011”], (z = 1)
[“00101”, “00000”, “01000”], (z = 2)
[“00001”, “00100”, “10001”], (z = 3)
[“00001”, “00110”, “00111”], (z = 4)
]
```

### Deliverables:
The only required output of the program will be a single file, solution.csv, that contains four
rows, the first containing the x,y,z coordinates of the position that the laser is going to be fired
from. The second row will contain values for the î, ĵ, k̂ unit vectors that specify the
direction of fire. The third row will contain the number of ships destroyed, and the fourth
row will contain the total Destruction Score.
For example, the corresponding solution file for the example on the previous page would
consist of the following:
```
0,5,5
1,0,0
3
24
```
 Solutions can optionally include a visual representation of the ships and laser path in any
form the contestants see fit.
