
public class LinHashTable<AnyType> extends tabHash<AnyType> {

    @Override
    protected int procPos(AnyType s) {
        int has = s.hashCode() % size;

        if (has < 0) {
            has *= -1;
        }

        while (true) {
            if (space[has] == null) {
                return has;
            } else if (space[has].element.equals(s) && space[has].exists) {
                return has;
            }
            if (has + 1 == size) {
                has = 0;
            } else {
                has++;
            }
        }

    }

}


