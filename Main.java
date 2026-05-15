import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        
        String palavra;

        boolean palindromo=true;

        Scanner scan = new Scanner(System.in);


        System.out.println();

        System.out.print("Digite a palavra: ");

        palavra=scan.nextLine();




        Pilha<Character> pilha = new Pilha<>();

        for(int i=0;i<palavra.length();i++){

            pilha.empilhar(palavra.charAt(i));

        }


        




        for(int j=0;j<palavra.length();j++){

            char palavraString = palavra.charAt(j);
            char palavraPilha = pilha.desempilhar();

            if(palavraString!=palavraPilha){


                palindromo=false;
                break;
            }

        }
        
        

        System.out.println(palavra);

        if(palindromo==true){
            System.out.println("palindromo");
        }else{
            System.out.println("n é palindromo");
        }

    }
}
