/*
Problem - 223 Leetcode
----------------------
Find the total area covered by two rectilinear rectangles in a 2D plane.

Assume that the total area is never beyond the maximum possible value of int.
*/

public class Solution {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) 
    {
        if(C<E||G<A )
        return (G-E)*(H-F) + (C-A)*(D-B);
 
    if(D<F || H<B)
        return (G-E)*(H-F) + (C-A)*(D-B);
 
    int right = Math.min(C,G);
    int left = Math.max(A,E);
    int top = Math.min(H,D);
    int bottom = Math.max(F,B);
 
    return (G-E)*(H-F) + (C-A)*(D-B) - (right-left)*(top-bottom);
    }
}