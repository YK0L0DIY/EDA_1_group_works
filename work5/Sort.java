//            developed by:
//       Yaroslavb Kolodiy n39859
//                and
//        Eduardo Medieros n39873

import java.util.Scanner;

public class Sort {

    static Comparable[] array;
    static Comparable[] tempMergArr;

    //mergesport
    public static void method1(Comparable[] A) {
        array = A;
        tempMergArr = new Comparable[A.length];
        doMerge(0, A.length - 1);
    }

    private static void doMerge(int lowerIndex, int higherIndex) {
        //ate nao ser 1 executa o merge dirita e merge esquerda
        if (lowerIndex < higherIndex) {
            int middle = lowerIndex + (higherIndex - lowerIndex) / 2; //meio do array original
            doMerge(lowerIndex, middle); //Sort da parte esquerda
            doMerge(middle + 1, higherIndex); //sort da parte direita
            mergeArrys(lowerIndex, middle, higherIndex); //junta amobas as partes
        }
    }

    private static void mergeArrys(int lowerIndex, int middle, int higherIndex) {
        //cria um array temporario que sera feito o sort
        for (int i = lowerIndex; i <= higherIndex; i++) {
            tempMergArr[i] = array[i];
        }

        int i = lowerIndex;
        int j = middle + 1;
        int k = lowerIndex;

        while (i <= middle && j <= higherIndex) {
            //compara os elementos do array tempoprario para dar join de ambom
            //se for menor/igual da parte esquerda

            try{
                //verifica se é numero (caso dee exeçao nao e numero)
                if (Double.parseDouble(tempMergArr[i].toString()) < Double.parseDouble(tempMergArr[j].toString())){
                    array[k] = tempMergArr[i];
                    i++;
                }

                // se for menor da parte direita adiciona
                else {
                    array[k] = tempMergArr[j];
                    j++;
                }
                k++;

            }catch (Exception e){
                if (tempMergArr[i].compareTo(tempMergArr[j]) <= 0) {
                    array[k] = tempMergArr[i];
                    i++;
                }
                // se for menor da parte direita adiciona
                else {
                    array[k] = tempMergArr[j];
                    j++;
                }
                k++;
            }
        }
        //copia o temporario para as posicoes corretas do array original
        while (i <= middle) {
            array[k] = tempMergArr[i];
            k++;
            i++;
        }
    }

    public static void method2(Comparable[] A) {
        doQuick(A,0,A.length-1);
    }

    private static void doQuick(Comparable[] A, int left, int right){
        if (left < right){
            int x = part(A, left,right);

            //left part sort
            doQuick(A, left, x);

            //right part sort
            doQuick(A, x+1, right);
        }
    }

    private static int part(Comparable[] A, int left, int right){
        int tempL = left;
        int tempR = right;

        //gets the element that's in the middle
        Comparable pivot = A[(left+right)/2];
        boolean hit = false;

        /*tempL increases and tempR decreases, like that they are converge into
          the middle of the array until they pass each other*/
        while (!hit){
            while(A[tempL].compareTo(pivot)<0)
                tempL++;
            while (A[tempR].compareTo(pivot)>0)
                tempR--;

            if (tempL < tempR){
                swap(A, tempL, tempR);
                tempL++;
                tempR--;
            }else {
                hit = !hit;
            }
        }
        return tempR;
    }

    private static void swap(Comparable[] A, int left, int right){
        Comparable temp = A[left];
        A[left] = A[right];
        A[right] = temp;
    }

    // metodo para ler array input e converte num array de Comparable
    public static Comparable[] le_array() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Your array:");
        String input = sc.nextLine();
        return input.split(" ");
    }

    // methido que recebe um array de Objetos e da print de cada elemento
    public static void printArray(Object[] x) {
        for (Object i : x) {
            System.out.print(i + ";");

        }
        System.out.println();
    }

    // main, fazer chamada dos metodos
    public static void main(String[] args) {
        Comparable[] array = le_array();
        System.out.println("Array original:");
        printArray(array);

        //chama metodo 1
        //method1(array);

        //calls medoto2
        method2(array);

        //prints the array already sorted
        System.out.println("Array apos sort:");
        printArray(array);
    }
}
