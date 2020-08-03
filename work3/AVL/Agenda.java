package AVL;

import java.util.Iterator;

public class Agenda {

    private AVLTree<Contacto> myTree;

    public Agenda() {
        myTree = new AVLTree<>();
    }

    public void adicionar(String id, int numb1) {
        myTree.insert(new Contacto(id, numb1));
    }

    public void editar(String id, String newID){
        Contacto edit = editar(id);
        myTree.remove(edit);
        edit.setName(newID);
        myTree.insert(edit);
    }

    public void editar(String id, int newNum){
        if(editar(id).getNumber2()== 0){
            editar(id).setNumber2(newNum);
        }else
            editar(id).setNumber1(newNum);
    }

    public void editar(String id, int newNum , int numPretenido){

        if (numPretenido==1){
            editar(id).setNumber1(newNum);
        }else if(numPretenido==2){
            editar(id).setNumber2(newNum);
        }

    }

    public void editar(int num, String newID){
        Contacto edit = editar(num);
        myTree.remove(edit);
        edit.setName(newID);
        myTree.insert(edit);
    }

    public void editar(int num, int newNum){
        Contacto edit = editar(num);

        if(edit.number1==num){
            edit.setNumber1(newNum);
        }else edit.setNumber2(newNum);
    }

    private Contacto editar(String id){
        Contacto procura = myTree.getRoot();
        Iterator iterator = myTree.iterator();

        while (iterator.hasNext()) {
            if (procura.getName().equals(id)) {
                return procura;
            }
            procura = (Contacto) iterator.next();
        }

        return null;
    }

    public Contacto editar(int num){
        Contacto procura = myTree.getRoot();
        Iterator iterator = myTree.iterator();
        while (iterator.hasNext()) {
            if (procura.getNumber1() == num || procura.getNumber2() == num) {
                return procura;
            }
            procura = (Contacto) iterator.next();
        }
        return null;
    }

    public void remover(String id) {
        Contacto procura = myTree.getRoot();
        Iterator iterator = myTree.iterator();

        while (iterator.hasNext()) {
            if (procura.getName().equals(id)) {
                myTree.remove(procura);
                return;
            }
            procura = (Contacto) iterator.next();
        }
    }

    public void remover(int num) {
        Contacto procura = myTree.getRoot();
        Iterator iterator = myTree.iterator();
        while (iterator.hasNext()) {
            if (procura.getNumber1() == num || procura.getNumber2() == num) {
                myTree.remove(procura);
                return;
            }
            procura = (Contacto) iterator.next();
        }
    }

    public void listar() {
        myTree.printTree();
    }

    public void chamador(int num) {
        Contacto procura = myTree.getRoot();
        Iterator iterator = myTree.iterator();
        while (iterator.hasNext()) {
            if (procura.getNumber1() == num || procura.getNumber2() == num) {
                System.out.println(procura.getName());
                return;
            }
            try {
                procura = (Contacto) iterator.next();
            } catch (NullPointerException e){
                System.out.println("DESCONHECIDO");
                break; //em teoria saíria do while porque apanhou uma excepção...
            }
        }
    }

    //INNER CLASS
    public static class Contacto implements Comparable<Contacto> {

        private String name;
        private int number1;
        private int number2;


        public Contacto(String name, int number1, int number2) {
            this.name = name;
            this.number1 = number1;
            this.number2 = number2;
        }

        public Contacto(String name, int number1) {
            this.name = name;
            this.number1 = number1;
            this.number2 = 0;
        }

        public String getName() {
            return name;
        }

        public int getNumber1() {
            return number1;
        }

        public int getNumber2() {
            return number2;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setNumber1(int number1) {
            this.number1 = number1;
        }

        public void setNumber2(int number2) {
            this.number2 = number2;
        }

        public String toString() {
            if (number2 == 0) {
                return name + " " + number1;
            } else {
                return name + " " + number1 + " " + number2;
            }
        }

        public int compareTo(Contacto o) {
            return this.name.compareTo(o.getName());
        }
    }

    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        agenda.adicionar("yaroslav", 98232);
        agenda.adicionar("qweqwe", 98243332);
        agenda.adicionar("ed", 2323);
        agenda.adicionar("zé",1);
        agenda.listar();

        agenda.editar("ed",2);
        System.out.println("\n");
        agenda.listar();

        System.out.println("\n");
        agenda.remover(2323);
        agenda.listar();
        System.out.println("\n");
        agenda.remover("zé");
        agenda.listar();
        System.out.println("\n");
        agenda.remover("yaroslav");
        agenda.listar();
        System.out.println();

    }
}
