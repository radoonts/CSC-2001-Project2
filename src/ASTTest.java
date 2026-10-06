import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ASTTest {
    //eval() tests
    @Test
    public void numNodeEvalReturnsStoredNumber() {
        AST number = new NumNode(7.5); //check to see if it works through the interface

        assertEquals(7.5, number.eval());
    }

    @Test
    public void evalAddition() {
        AST leftNode = new NumNode(3);
        AST rightNode = new NumNode(2);
        BinopNode binop = new BinopNode("+", leftNode, rightNode);

        assertEquals(5, binop.eval());
    }

    @Test
    public void evalSubtraction() {
        AST leftNode = new NumNode(3);
        AST rightNode = new NumNode(2);
        BinopNode binop = new BinopNode("-", leftNode, rightNode);

        assertEquals(1, binop.eval());
    }

    @Test
    public void evalMultiplication() {
        AST leftNode = new NumNode(3);
        AST rightNode = new NumNode(2);
        BinopNode binop = new BinopNode("*", leftNode, rightNode);

        assertEquals(6, binop.eval());
    }

    @Test
    public void evalDivision() {
        AST leftNode = new NumNode(3);
        AST rightNode = new NumNode(2);
        BinopNode binop = new BinopNode("/", leftNode, rightNode);

        assertEquals(1.5, binop.eval());
    }

    @Test
    public void evalPow() {
        AST leftNode = new NumNode(3);
        AST rightNode = new NumNode(2);
        BinopNode binop = new BinopNode("^", leftNode, rightNode);

        assertEquals(9, binop.eval());
    }

    @Test
    public void evalThrowsError() {
        AST leftNode = new NumNode(3);
        AST rightNode = new NumNode(2);
        BinopNode binop = new BinopNode("#", leftNode, rightNode);

        assertThrows(IllegalArgumentException.class, () -> binop.eval());
    }

    @Test
    public void evalNested() {
        AST leftNestedNode = new NumNode(3);
        AST rightNestedNode = new NumNode(4);
        AST leftNode = new NumNode(2);
        AST rightNode = new BinopNode("+", leftNestedNode, rightNestedNode);
        BinopNode binop = new BinopNode("*", leftNode, rightNode);

        assertEquals(14, binop.eval());
    }
}
