public abstract class tabHash<AnyType> {

    public int size;
    public int used;

    public hashElement<AnyType>[] space;

    public tabHash() {
        this.space = new hashElement[7];
        this.size = 7;
        this.used = 0;
    }

    public tabHash(int size) {
        this.space = new hashElement[size];
        this.size = size;
        this.used = 0;
    }

    public int ocupados() {
        return used;
    }

    public float factorDeCarga() {
        return (float) used / (float) size;
    }

    protected abstract int procPos(AnyType s);

    public void alocarTabela(int dim) {
        this.space = new hashElement[dim];
        this.size = dim;
        this.used = 0;
    }

    public void tornarVazia() {
        this.space = new hashElement[size];
        this.used = 0;
    }

    public AnyType procurar(AnyType x) {
        int proc = procPos(x);
        if (space[proc] == null || !space[proc].exists) {
            return null;
        }
        return x;
    }

    public void remove(AnyType x) {
        int pos = procPos(x);
        if (space[pos].element.equals(x)) {
            space[pos].remove();
        }
    }

    public void insere(AnyType x) {
        float carga = factorDeCarga();
        if (carga > 0.5) {
            rehash();
        }
        int alocar = procPos(x);

        space[alocar] = new hashElement<>(x);
        used++;
    }

    public void rehash() {
        int newSize = newSize(size * 2);

        hashElement old[] = space;
        space = new hashElement[newSize];
        size = newSize;
        used = 0;

        for (hashElement<AnyType> i : old) {
            if (i != null) {
                insere(i.element);
            }
        }
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            if (space[i] != null) {
                if (space[i].exists) {
                    System.out.println("" + i + " : " + space[i]);
                } else {
                    System.out.println("" + i + " : " + "removed");
                }
            } else {
                System.out.println("" + i + " : " + space[i]);
            }

        }
    }

    private int newSize(int oldSize) {
        oldSize++;

        for (int i = 2; i < oldSize; i++) {
            if (oldSize % i == 0) {
                oldSize++;
                i = 2;
            } else {
                continue;
            }
        }
        return oldSize;
    }

    protected class hashElement<AnyType> {
        public AnyType element;
        public boolean exists = false;

        public hashElement(AnyType element) {
            this.element = element;
            exists = true;
        }

        public void remove() {
            exists = false;
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }
}
