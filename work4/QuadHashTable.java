public class QuadHashTable<AnyType> extends tabHash<AnyType> {

    @Override
    protected int procPos(AnyType s) {
        int has = s.hashCode() % size;
        int inicialHas = has;

        if (has < 0) {
            inicialHas*=-1;
            has *= -1;
        }

        int i = 1;

        while (true) {
            if (space[has] == null) {
                return has;
            } else if (space[has].element.equals(s) && space[has].exists) {
                return has;
            }
            if (inicialHas + (i * i) >= size) {
                has = inicialHas+(i*i);
                has=has%size;

            } else {
                i++;
            }
        }


    }

    public static void main(String[] args) {
        tabHash<String> dicionario= new QuadHashTable<>();
        dicionario.insere("qwd");
        dicionario.insere("qd");
        dicionario.insere("qw");
        dicionario.insere("qwe");
        dicionario.insere("qwg");
        dicionario.insere("qww");
        dicionario.insere("qwg");
        dicionario.print();
    }

}