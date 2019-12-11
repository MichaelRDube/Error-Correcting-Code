import java.util.Scanner;
public class ErrorCorrection {
    static String encode (String word) {
        word = word.toUpperCase();
        int [] numWord = new int [6];
        
        for (int i = 0; i < 4; i++) {
            if (word.charAt(i) >= 'A' && word.charAt(i) <= 'Z') {
                numWord[i+2] = (int)(word.charAt(i) - 'A') + 1;
            }
            else if (word.charAt(i) == ' ') {
                numWord[i+2] = 0;
            }
            else if (word.charAt(i) == '.') {
                numWord[i+2] = 27;
            }
            else if (word.charAt(i) == ',') {
                numWord[i+2] = 28;
            }
            else if (word.charAt(i) == ';') {
                numWord[i+2] = 29;
            }
            else if (word.charAt(i) == '?') {
                numWord[i+2] = 30;
            }
        }
        
        numWord[1] = (31 - (2*numWord[2] + 3*numWord[3] + 4*numWord[4] + 5*numWord[5])%31)%31;
        numWord[0] = (31 - (numWord[1] + numWord[2] + numWord[3] + numWord[4] + numWord[5])%31)%31;
        
        for (int i = 1; i >= 0; i--) {
            if (numWord[i] >= 1 && numWord[i] <= 26) {
                word = (char)(numWord[i]-1 + 'A') + word;
            }
            else if (numWord[i] == 0) {
                word = ' ' + word;
            }
            else if (numWord[i] == 27) {
                word = '.' + word;
            }
            else if (numWord[i] == 28) {
                word = ',' + word;
            }
            else if (numWord[i] == 29) {
                word = ';' + word;
            }
            else if (numWord[i] == 30) {
                word = '?' + word;
            }
        }
            
        return word;
    }
    
    static String correctError(String receivedWord) {
        receivedWord = receivedWord.toUpperCase();
        int [] numWord = new int [6];
        
        for (int i = 0; i < 6; i++) {
            if (receivedWord.charAt(i) >= 'A' && receivedWord.charAt(i) <= 'Z') {
                numWord[i] = (int)(receivedWord.charAt(i) - 'A') + 1;
            }
            else if (receivedWord.charAt(i) == ' ') {
                numWord[i] = 0;
            }
            else if (receivedWord.charAt(i) == '.') {
                numWord[i] = 27;
            }
            else if (receivedWord.charAt(i) == ',') {
                numWord[i] = 28;
            }
            else if (receivedWord.charAt(i) == ';') {
                numWord[i] = 29;
            }
            else if (receivedWord.charAt(i) == '?') {
                numWord[i] = 30;
            }
        }
        
        int errorValue = (numWord [0] + numWord[1] + numWord[2] + numWord[3] + numWord[4] + numWord[5])%31;
        int errorPosition = (numWord[1] + 2*numWord[2] + 3*numWord[3] + 4*numWord[4] + 5*numWord[5])%31;
        
        if (errorValue > errorPosition && errorPosition != 0) {
            errorValue = -1*(31-errorValue);
            errorPosition = -1*(31-errorPosition);
        }
        int val = errorValue;
        int pos = errorPosition;
        if (errorPosition != 0) {
            while (errorPosition%errorValue != 0) {
                errorPosition = (errorPosition+pos)%31;
                errorValue = (errorValue+val)%31;
            }
            errorPosition = errorPosition/errorValue;
        }
        errorValue = val;
        numWord[errorPosition] = (31+numWord[errorPosition]-errorValue)%31;
        
        receivedWord = "";
        
        for (int i = 0; i < 6; i++) {
            if (numWord[i] >= 1 && numWord[i] <= 26) {
                receivedWord += (char)(numWord[i]-1 + 'A');
            }
            else if (numWord[i] == 0) {
                receivedWord += ' ';
            }
            else if (numWord[i] == 27) {
                receivedWord += '.';
            }
            else if (numWord[i] == 28) {
                receivedWord += ',';
            }
            else if (numWord[i] == 29) {
                receivedWord += ';';
            }
            else if (numWord[i] == 30) {
                receivedWord += '?';
            }
        }
        //System.out.println(receivedWord);
        
        return receivedWord.substring(2);
    }

    public static void main(String[] args) {
    }
    
}
