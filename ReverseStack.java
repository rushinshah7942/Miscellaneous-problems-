/*
Reverse a stack using recursion
You are not allowed to use loop constructs like while, for..etc, and you can only use the following ADT functions on Stack S:
isEmpty(S)
push(S)
pop(S)

Solution:
------------
The idea of the solution is to hold all values in Function Call Stack until the stack becomes empty. When the stack becomes empty, insert all held items one by one at the bottom of the stack.

*/

public static void revertStack(Stack<Integer> s) {
    if (s.isEmpty()) {
        return;
    } else {
        Integer a = s.pop();
        revertStack(s);
        appendStack(s, a);
    }
}

public static void appendStack(Stack<Integer> s, Integer a) {
    if (s.isEmpty()) {
        s.push(a);
        return;
    } else {
        Integer o = s.pop();
        appendStack(s, a);
        s.push(o);
    }
}