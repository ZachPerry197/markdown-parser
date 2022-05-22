import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

public class MarkdownParseTest extends MarkdownParse {

    @Test
    public void additionTest() {
        assertEquals(3, 1 + 2);
    }

    @Test
    public void snippet1Test() {
        ArrayList<String> correctList = new ArrayList<>();
        correctList.add("`google.com");
        correctList.add("google.com");
        correctList.add("ucsd.edu");
        String snippet1 = """
            `[a link`](url.com)

            [another link](`google.com)`
            
            [`cod[e`](google.com)
            
            [`code]`](ucsd.edu)
                """;
        ArrayList<String> outputList = getLinks(snippet1);
        assertEquals(correctList, outputList);
    }

    @Test
    public void snippet2Test() {
        ArrayList<String> correctList = new ArrayList<>();
        correctList.add("a.com");
        correctList.add("a.com(())");
        correctList.add("example.com");
        String snippet2 = """
            [a [nested link](a.com)](b.com)

            [a nested parenthesized url](a.com(()))
            
            [some escaped \\[ brackets \\]](example.com)
                """;
        ArrayList<String> outputList = getLinks(snippet2);
        assertEquals(correctList, outputList);
    }

    @Test
    public void snippet3Test() {
        ArrayList<String> correctList = new ArrayList<>();
        correctList.add("https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule");
        String snippet3 = """
            [this title text is really long and takes up more than 
            one line
            
            and has some line breaks](
                https://www.twitter.com
            )
            
            [this title text is really long and takes up more than 
            one line](
            https://sites.google.com/eng.ucsd.edu/cse-15l-spring-2022/schedule
            )
            
            
            [this link doesn't have a closing parenthesis](github.com
            
            And there's still some more text after that.
            
            [this link doesn't have a closing parenthesis for a while](https://cse.ucsd.edu/
            
            
            
            )
            
            And then there's more text
                """;
        ArrayList<String> outputList = getLinks(snippet3);
        assertEquals(correctList, outputList);
    }
}