public class NumNode implements AST {
    //attributes
    private double number;

    //constructor
    public NumNode(double number) {
        this.number = number;
    }

    //methods
    /*
        purpose: to obtain the value of a node
        input: none
        output: the double value of an expression
     */
    @Override
    public double eval() {
        return this.number;
    }

}
