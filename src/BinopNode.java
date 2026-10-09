public class BinopNode implements AST {
    //attributes
    private String operator;
    private AST left;
    private AST right;

    //constructor
    public BinopNode(String operator, AST left, AST right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    //methods
    /*
        purpose: the mathematical calculation of two expressions and an operator.
        input: none
        Output: a double value which represents the operation between two expressions.
     */
    @Override
    public double eval() {

        double leftChild = left.eval();
        double rightChild = right.eval();

        //determine what operation to use by using a switch statement
        switch(this.operator) {
            case "+":
                return leftChild + rightChild;
            case "-":
                return leftChild - rightChild;
            case "*":
                return leftChild * rightChild;
            case "/":
                return leftChild / rightChild;
            case "^":
                return Math.pow(leftChild, rightChild);
            default:
                throw new IllegalArgumentException("invalid operator");
        }
    }

}
