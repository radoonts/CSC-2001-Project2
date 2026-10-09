import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    //parsePostfix() tests
    @Test
    void parsePostfixAddition() {
        AST value = Parser.parsePostfix("2 3 +");

        assertEquals(5.0, value.eval());
    }

    @Test
    void parsePostfixSubtraction() {
        AST value = Parser.parsePostfix("2 3 -");

        assertEquals(-1.0, value.eval());
    }

    @Test
    void parsePostfixMultiplication() {
        AST value = Parser.parsePostfix("2 3 *");

        assertEquals(6.0, value.eval());
    }

    @Test
    void parsePostfixDivision() {
        AST value = Parser.parsePostfix("10 5 /");

        assertEquals(2.0, value.eval());
    }

    @Test
    void parsePostfixPow() {
        AST value = Parser.parsePostfix("2 3 ^");

        assertEquals(8.0, value.eval());
    }

    @Test
    void parsePostFixMultiple() {
        AST value = Parser.parsePostfix("2 3 + 4 -");

        assertEquals(1.0, value.eval());
    }

    @Test
    void parsePostfixNested() {
        AST value = Parser.parsePostfix("2 3 2 ^ ^");

        assertEquals(512.0, value.eval());
    }

    @Test
    void parsePostfixEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostfix(""));
    }

    @Test
    void parsePostfixInvalidToken() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostfix("2 3 a"));
    }

    @Test
    void parsePostfixInsufficientOperands() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostfix("2 +"));
    }

    @Test
    void parsePostfixTooManyOperands() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parsePostfix("2 3 4 +"));
    }

    //parseInfix() tests
    @Test
    void parseInfixSingleNumber() {
        AST value = Parser.parseInfix("7.5");

        assertEquals(7.5, value.eval());
    }

    @Test
    void parseInfixAddition() {
        AST value = Parser.parseInfix("2 + 3");

        assertEquals(5.0, value.eval());
    }

    @Test
    void parseInfixSubtraction() {
        AST value = Parser.parseInfix("20 - 8");

        assertEquals(12.0, value.eval());
    }

    @Test
    void parseInfixMultiplicationPrecedence() {
        AST value = Parser.parseInfix("2 + 3 * 4");

        assertEquals(14.0, value.eval());
    }

    @Test
    void parseInfixAddAfterMultiply() {
        AST value = Parser.parseInfix("2 * 3 + 4");

        assertEquals(10.0, value.eval());
    }

    @Test
    void parseInfixParenthesis() {
        AST value = Parser.parseInfix("2 * ( 3 + 4 )");

        assertEquals(14.0, value.eval());
    }

    @Test
    void parseInfixLeftAssociative() {
        AST value = Parser.parseInfix("8 / 4 / 2");

        assertEquals(1.0, value.eval());
    }

    @Test
    void parseInfixRightAssociative() {
        AST value = Parser.parseInfix("2 ^ 3 ^ 2");

        assertEquals(512.0, value.eval());
    }

    @Test
    void parseInfixNestedParenthesis() {
        AST value = Parser.parseInfix("( 2 + ( 3 * 4 ) )");

        assertEquals(14.0, value.eval());
    }

    @Test
    void parseInfixEmpty() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInfix(""));
    }

    @Test
    void parseInfixInvalidToken() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInfix("2 a 3"));
    }

    @Test
    void parseInfixInsufficientOperands() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInfix("2 +"));
    }

    @Test
    void parseInfixTooManyOperands() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInfix("2 + 3 2"));
    }

    @Test
    void parseInfixMismatchedOpenParen() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInfix("( 2 + 3"));
    }

    @Test
    void parseInfixMismatchedCloseParen() {
        assertThrows(IllegalArgumentException.class, () -> Parser.parseInfix("2 + 3 )"));
    }
}
