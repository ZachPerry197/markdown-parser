//https://howtodoinjava.com/java/io/java-read-file-to-string-examples/

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.lang.String;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {

            if (currentIndex == markdown.length() - 1) {
                break;
            }
            if (markdown.charAt(currentIndex) == '[') {
                int openBracket = markdown.indexOf("[", currentIndex);
                if (openBracket == -1) {
                    currentIndex++;
                    continue;
                }
                int closeBracket = markdown.indexOf("]", openBracket);
                if (closeBracket == -1) {
                    currentIndex++;
                    continue;
                }
                int openParen = markdown.indexOf("(", closeBracket);
                if (openParen == -1) {
                    currentIndex++;
                    continue;
                }
                int closeParen = markdown.indexOf(")", openParen);
                if (closeParen == -1) {
                    currentIndex++;
                    continue;
                }
                if (openParen == closeBracket + 1) {
                toReturn.add(markdown.substring(openParen + 1, closeParen));
                currentIndex = closeParen + 1;
                }
                else {
                    currentIndex++;
                    continue;
                }
            }
            else {
                currentIndex++;
            }
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
        //System.out.println(content);
	    System.out.println(links);
    }
}
