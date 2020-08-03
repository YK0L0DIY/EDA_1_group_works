import java.io.*;
import java.util.Scanner;

public class Corrector {

    public static void main(String[] args) {
        tabHash<String> dicionario = new QuadHashTable<>();
        dicionario.alocarTabela(1400000);

        //Dictionary
        try {
            File file = new File("wordlist-ao-20101027_2.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            while ((st = br.readLine()) != null)
                dicionario.insere(st);

        } catch (FileNotFoundException e) {
            System.out.println("Dicionário não encontrado.");
        } catch (java.io.IOException e) {
            System.out.println(e.getMessage());
        }

        //Text File
        try {
            File file2 = new File("text.txt");
            BufferedReader br1 = new BufferedReader(new FileReader(file2));

            String line;
            int linenumber = 0;
            while ((line = br1.readLine()) != null) {
                linenumber++;
                Scanner all = new Scanner(line);
                while (all.hasNext()) {
                    String word = all.next();
                    StringBuffer posibiliti;
                    String[] alphabet = {"a", "b", "c", "d", "e", "f", "g", "h", "i",
                            "j", "k", "l", "m", "n", "o", "p",
                            "q", "r", "s", "t", "u", "v", "w",
                            "x", "y", "z", "ç", "á", "à", "â",
                            "é", "è", "ê", "í", "Í", "î", "ó",
                            "N", "O", "P", "Q", "R", "T", "U",
                            "V", "X", "Z", "Y", "W", "ò", "ô",
                            "ú", "ù", "E", "A", "B", "C", "D",
                            "F", "G", "H", "I", "J", "K", "L" , "-"};

                    if (dicionario.procurar(word) == null) {
                        for (int i = 0; i < alphabet.length; i++) {
                            for (int x = 0; x <= word.length(); x++) {
                                posibiliti = new StringBuffer(word);
                                posibiliti.insert(x, alphabet[i]);

                                if (dicionario.procurar(posibiliti.toString()) != null) {
                                    System.out.println("linha " + linenumber + ": sugestão " + posibiliti);

                                }
                            }
                        }
                        for (int x = 0; x < word.length(); x++) {
                            posibiliti = new StringBuffer(word);
                            posibiliti.deleteCharAt(x);

                            if (dicionario.procurar(posibiliti.toString()) != null) {
                                System.out.println("linha " + linenumber + ": sugestão " + posibiliti);

                            }
                        }
                        for (int x = 0; x < word.length(); x++) {
                            for (int y = 0; y < word.length(); y++) {
                                posibiliti = new StringBuffer(swap(word, x, y));

                                if (dicionario.procurar(posibiliti.toString()) != null) {
                                    System.out.println("linha " + linenumber + ": sugestão " + posibiliti);

                                }
                            }
                        }


                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("Ficheiro de texto não encontrado.");
        } catch (java.io.IOException e) {
            System.out.println("nao sei");
        }

    }

    static String swap(String str, int i, int j) {
        char[] ch = str.toCharArray();
        char temp = ch[i];
        ch[i] = ch[j];
        ch[j] = temp;
        return String.valueOf(ch);
    }
}
