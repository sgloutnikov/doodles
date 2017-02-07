package drawingbook;

import java.util.*;

/*
Brie’s Drawing teacher asks her class to open their -page book to page number . Brie can either start turning pages
from the front of the book (i.e., page number ) or from the back of the book (i.e., page number ), and she always
turns pages one-by-one (as opposed to skipping through multiple pages at once). When she opens the book, page
is always on the right side:

Each page in the book has two sides, front and back, except for the last page which may only have a front side
depending on the total number of pages of the book.

Given n and p, find and print the minimum number of pages Brie must turn in order to arrive at page p.

Input Format

The first line contains an integer, , denoting the number of pages in the book.
The second line contains an integer, , denoting the page that Brie's teacher wants her to turn to.

Constraints
1 <= n <= 10^5
1 <= p <= n

Output Format
Print an integer denoting the minimum number of pages Brie must turn to get to page p.

Sample Input 0

6
2
Sample Output 0

1
Explanation 0
If Brie starts turning from page , she only needs to turn  page:

Untitled Diagram(6).png

If Brie starts turning from page , she needs to turn  pages:

Untitled Diagram(3).png

Because we want to print the minumum number of page turns, we print  as our answer.

Sample Input 1

5
4
Sample Output 1

0
 */

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double n = in.nextDouble();
        double p = in.nextDouble();

        int pageFlips;

        if (p <= (n/2)) {
            pageFlips = (int)Math.floor(p/2);
            System.out.println(pageFlips);
        } else {
            // Corner Case
            if (p == (n-1)) {
                if (n % 2 == 0) {
                    System.out.println(1);
                    return;
                }
                System.out.println(0);
                return;
            }
            pageFlips = (int)Math.floor((n-p)/2);
            System.out.println(pageFlips);
        }
    }
}

