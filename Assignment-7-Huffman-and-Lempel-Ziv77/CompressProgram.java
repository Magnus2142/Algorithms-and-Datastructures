import Huffman.Huffman;
import LempelZiv77.LempelZiv77;

import java.io.IOException;

public class CompressProgram {

    public static void main(String[] args) throws IOException {
        LempelZiv77 lempelZiv77 = new LempelZiv77();
        Huffman huffman = new Huffman();

        //Skriv inn pathen til filen du vil komprimere her
        //Jeg har brukt diverse.txt og diverse.lyx som er fra forelesningene i forskjellige formatervg
        String fileName = "src/diverse.txt";


        lempelZiv77.writeCompressedFile(fileName);
        huffman.writeCompressedFile(fileName);
    }
}
