import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class HangmanTester {

    private Hangman h;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();


    @BeforeEach
    public void beforeEach(){
        h = new Hangman();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @AfterEach
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
    }

    @Test
    public void constructor(){
        assertEquals(h.getAttemptsLeft(), 5);
        assertEquals(h.getStatus(), "_ _ _ _ _ ");
    }

    @Test
    public void testGetNumChars(){
        assertEquals(h.getNumChars("cat"), "_ _ _ ");
        assertEquals(h.getNumChars("banana"), "_ _ _ _ _ _ ");
    }

    @Test
    public void testGuess(){
        assertEquals(h.makeGuess(), "a");
    }


    // add true case
    @Test
    public void testOutOfAttempts(){
        assertEquals(h.outOfAttempts(), false);


    }

    @Test
    public void testChangeStatus(){
        assertEquals(h.changeStatus(6, "l"), "_ _ _ l _ ");
        assertEquals(h.changeStatus(0, "a"), "a _ _ l _ ");
    }



}
