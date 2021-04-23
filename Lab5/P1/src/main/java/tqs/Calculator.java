package tqs;

import java.util.Stack;

public class Calculator {

    private Stack<Integer> stack;

    public Calculator(){
        stack = new Stack<>();
    }

    public void push(int value){
        stack.push(value);
    }

    public void push(String operator){
        if(stack.size()>=2){
            int value1 = stack.pop();
            int value2 = stack.pop();

            int newValue = 0;
            if (operator.equals("+")) {
                newValue = value1 + value2;
            }
            else if( operator.equals("-")){
                newValue = value1 - value2;
            }
            stack.push(newValue);
        }

    }

    public int value(){
        if(stack.size()==1){
            return stack.pop();
        }
        return 0;
    }
}
