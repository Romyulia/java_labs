import java.util.ArrayList;
import java.util.Comparator;

public class StringCalculator {

    public int add(String numbers) throws StringCalculatorExceptions{
        int sumOfNumbers = 0;
        String delimiters = "";
        String[] num;
        if(numbers.length() == 0) {
            return 0;
        }
        else if(numbers.indexOf("//")==0){
            delimiters = parseDelimiters(numbers);
            num = numbers.substring(numbers.indexOf('\n')+1).split(delimiters);
            sumOfNumbers = summingNumbers(num);
            return sumOfNumbers;
        }
        else{
            delimiters = ",|\n";
            num = numbers.split(delimiters);
            sumOfNumbers = summingNumbers(num);
            return sumOfNumbers;
        }
    }
    private String parseDelimiters(String numbers){
        ArrayList<String> delimiters = new ArrayList<>();
        if(numbers.charAt(2) == '['){
            int counter = 0;
            do{
                counter++;
                StringBuilder stringBuilder = new StringBuilder();
                do{
                    if(numbers.charAt(2+counter) == '*' | numbers.charAt(2+counter) == '+'|
                            numbers.charAt(2+counter) == '?' | numbers.charAt(2+counter) == '^'|
                            numbers.charAt(2+counter) == '$'){
                        stringBuilder.append("\\");
                    }
                    stringBuilder.append(numbers.charAt(2+counter));
                    counter++;
                }while(numbers.charAt(2+counter) != ']');
                delimiters.add(stringBuilder.toString());
                counter++;
            }while(numbers.charAt(2 + counter) == '[');
        }
        else{
            int counter = 0;
            StringBuilder stringBuilder = new StringBuilder();
            do{
                if(numbers.charAt(2+counter) == '*' | numbers.charAt(2+counter) == '+'|
                        numbers.charAt(2+counter) == '?' | numbers.charAt(2+counter) == '^'|
                        numbers.charAt(2+counter) == '$'){
                    stringBuilder.append("\\");
                }
                stringBuilder.append(numbers.charAt(2+counter));
                counter++;
            }while(numbers.charAt(2 + counter) != '\n');
            delimiters.add(stringBuilder.toString());
        }
        delimiters.add(",");
        delimiters.add("\n");
        delimiters.sort(Comparator.reverseOrder());
        String delim = String.join("|", delimiters);
        return delim;
    }
    private int summingNumbers(String[] numbers) throws StringCalculatorExceptions{
        int sumOfNumbers = 0;
        int number;
        String negativeNumbers = "";
        for(String num:numbers) {
            try {
                number = Integer.parseInt(num);
                if(number<0){
                    negativeNumbers += number + " ";
                } else if (number>1000) {
                    number=0;
                }
                sumOfNumbers += number;
            } catch (NumberFormatException e){
                throw new StringCalculatorExceptions("Error! Your input is invalid.");
            }
        }
        if(!negativeNumbers.isEmpty()) {
            throw new StringCalculatorExceptions("Error! You entered negative numbers: " + negativeNumbers);
        }
        return sumOfNumbers;
    }


    public static void main(String[] args) throws StringCalculatorExceptions{
        String str = "//[***][%]\n1***2%3";
        StringCalculator stringCalculator = new StringCalculator();
        int sum = stringCalculator.add(str);
        System.out.println(sum);
    }
}
