public class Parser {
    //methods
    /*
        purpose: a helper function to determine whether a token is an operator
        input: a token string
        output: a boolean value which represents whether or not a token is an operator
        @param token: String containing the tokenized String
    */
    private static boolean isOperator(String token) {
        return switch (token) {
            case "+", "-", "*", "/", "^" -> true;
            default -> false;
        };
    }

    /*
        purpose: a helper function to apply the operator to two popped values from the stack then add the computed
        value back to the stack
        input: a string operator and AST stack
        output: nothing because it will mutate the stack instead
        @param operator: String containing the operator
        @param stack: AST stack containing the nodes
     */
    private static void applyOperator(String operator, ArrayStack<AST> stack) {
        //before applying the operator, check if there aren't two nodes in the stack
        if(stack.size() < 2) {
            throw new IllegalArgumentException("insufficient operands");
        }

        //first pop the two AST nodes in the stack
        AST right = stack.pop();
        AST left = stack.pop();

        //apply the operator through the BinopNode and pushing it to the stack
        stack.push(new BinopNode(operator, left, right));
    }

    /*
        purpose: a helper function to help determine operator precedence for infix expressions
        input: a string operator
        output: an int representing different tiers of operator precedence
        @param operator: String containing the operator
     */
    private static int precedence(String operator) {
        //switch-case statement to determine precedence tier
        return switch(operator) {
            case "^" -> 3;
            case "*", "/" -> 2;
            default -> 1;
        };
    }

    /*
        purpose: determine whether to signal for left associative or not
        input: a string operator
        output: a boolean value representing whether to go for left associative
        @param operator: String containing the operator
     */
    private static boolean isLeftAssociative(String operator) {
        return switch(operator) {
            case "+", "-", "*", "/" -> true;
            default -> false;
        };
    }

    /*
        purpose: convert an input which is postfix form to a tree
        input: a string which holds the postfix/RPN expression
        output: an AST which contains the converted postfix expression as a treelike structure
        @param input: String containing the postfix expression
    */
    public static AST parsePostfix(String input) {
        if(input.isEmpty()) throw new IllegalArgumentException("empty input"); //empty string check

        String[] tokenizedInput = input.split("\\s+"); //separates the input by space
        ArrayStack<AST> stack = new ArrayStack<>();

        for(String token : tokenizedInput) {
            try {
                //add value to stack as double
                stack.push(new NumNode(Double.parseDouble(token)));
            } catch (NumberFormatException e) {
                //check if the non-double string is an operator, if not then throw exception
                if(isOperator(token)) {
                    //operator will be applied here
                    applyOperator(token, stack);
                } else {
                    throw new IllegalArgumentException("invalid token");
                }
            }
        }
        //check if theres only one AST node, if so then returned the popped value
        if(stack.size() > 1) throw new IllegalArgumentException("too many operands");
        return stack.pop();
    }

    /*
        purpose: convert a string that represents an infix expression into a postfix expression represented as a tree
        input: a string which holds the infix expression
        output: an AST which represents the postfix/RPN expression
        @param input: String containing the infix expression
     */
    public static AST parseInfix(String input) {
        if(input.isEmpty()) throw new IllegalArgumentException("empty input"); //empty string check

        String[] tokenizedInput = input.split("\\s+"); //seperates the input by space

        //create 2 stacks, one for expressions and another for operations
        ArrayStack<AST> expressionStack = new ArrayStack<>();
        ArrayStack<String> operatorStack = new ArrayStack<>();

        //iterate through the string
        for(String token : tokenizedInput) {
            try {
                //add value to the expression stack if its a number
                expressionStack.push(new NumNode(Double.parseDouble(token)));
            } catch (NumberFormatException e) {
                //handle parenthesis
                if(token.equals("(")) {
                    //directly push into operatorStack
                    operatorStack.push(token);
                } else if(token.equals(")")) {
                    //apply the expression inside parenthesis into the expressionStack
                    while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                        applyOperator(operatorStack.pop(), expressionStack);
                    }
                    //mismatched closing parenthesis check
                    if(operatorStack.isEmpty()) {
                        throw new IllegalArgumentException("mismatched close paren");
                    }
                    //remove the existing "("
                    operatorStack.pop();
                }
                else if(isOperator(token)) { //operator stack code after checking if its a valid operator
                    while(!operatorStack.isEmpty()) {
                        if(operatorStack.peek().equals("(")) break; //stops the loop if there's a open parenthesis

                        //check whether the top of operatorStack needs to be applied first
                        boolean higherPrecedence = precedence(operatorStack.peek()) > precedence(token);
                        boolean samePrecedenceAndIsLeftAssociative =
                                (precedence(operatorStack.peek()) == precedence(token)) && isLeftAssociative(token);

                        if(higherPrecedence || samePrecedenceAndIsLeftAssociative) {
                            //pop the top operator and apply it to expressionStack
                            applyOperator(operatorStack.pop(), expressionStack);
                        }
                        else break;
                    }
                    //push token onto operatorStack after loop ends
                    operatorStack.push(token);
                } else { //invalid token exception
                    throw new IllegalArgumentException("invalid token");
                }
            }
        }
        //drain the remaining operators
        while(!operatorStack.isEmpty()) {
            //check if there is an unfinished parenthesis statement
            if(operatorStack.peek().equals("(")) {
                throw new IllegalArgumentException("mismatched open paren");
            }
            //pop the operator and apply it to the expression stack
            applyOperator(operatorStack.pop(), expressionStack);
        }

        //one final check for extra operands
        if(expressionStack.size() > 1) {
            throw new IllegalArgumentException("too many operands");
        }

        //finally return the completed AST after applying operators to the infix expression
        return expressionStack.pop();
    }
}