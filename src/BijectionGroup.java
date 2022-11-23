import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class BijectionGroup<T> implements Group<Function<T, T>> {
    /**
     * elements of the group, which are bijection functions
     */
    public List<Function<T, T>> bijGroup;
    /**
     * bijection functions were get based on the set S
     */
    public Set<T> bijSetBase;

    /**
     * Construct a BijectionGroup using a set
     *
     * @param domain set S
     */
    public BijectionGroup(Set<T> domain) {
        this.bijGroup = bijectionsOf(domain);
        this.bijSetBase = domain;
    }

    /**
     * a static method called bijectionGroup.
     * This method should take a finite set of
     * items as its only argument,and return
     * the group of its bijections
     *
     * @param domain a finite set
     * @param <T> type in the set
     * @return the group of its bijections
     */
    public static <T> BijectionGroup<T> bijectionGroup(Set<T> domain) {
        return new BijectionGroup<>(domain);
    }

    /**
     * The job of this bijectionsOf(Set<T>) method
     * is to take a set as its input argument,
     * and return the set of all the bijections of
     * the input set.
     *
     * @param domain a set
     * @return all the bijections of the input set
     * @param <T> type in the set
     */
    public static <T> List<Function<T, T>> bijectionsOf(Set<T> domain) {
        List<T> domainList = new ArrayList<>(domain);
        List<List<T>> allPerm = new ArrayList<>();
        List<Function<T, T>> allFun = new ArrayList<>();
        permutation(domainList, allPerm, 0, domainList.size() - 1);

        List<HashMap<T, T>> bijectionMap = new ArrayList<>();
        for (List<T> x : allPerm) {
            HashMap<T, T> map = new HashMap<>();
            int i = 0;
            for (T y : domain) {
                map.put(y, x.get(i));
                i++;
            }
            bijectionMap.add(map);
        }
        for (HashMap<T, T> map : bijectionMap) {
            Function<T, T> x = map::get;
            allFun.add(x);
        }
        return allFun;
    }

    /**
     * Recursive helper method to get the permutation of a given List
     * @param oriSet input List
     * @param allPerm A List of Lists, used to store all the permutations
     * @param l position in the list, used to swap with r
     * @param r position in the list, used to swap with l
     * @param <T> type in the list
     */
    public static <T> void permutation(List<T> oriSet, List<List<T>> allPerm, int l, int r) {
        if (l == r) {
            List<T> toAdd = new ArrayList<>(oriSet);
            allPerm.add(toAdd);
        } else {
            for (int i = l; i <= r; i++) {
                Collections.swap(oriSet, l, i);
                permutation(oriSet, allPerm, l + 1, r);
                Collections.swap(oriSet, l, i);
            }
        }
    }

    public static void main(String... args) {
        Set<Integer> a_few = Stream.of(1, 2, 3).collect(Collectors.toSet());
        // you have to figure out the data type in the line below
        List<Function<Integer, Integer>> bijections = bijectionsOf(a_few);
        bijections.forEach(aBijection -> {
            a_few.forEach(n -> System.out.printf("%d --> %d; ", n, aBijection.apply(n)));
            System.out.println();
        });

        System.out.println("\n\n\n");
        BijectionGroup<Integer> g = bijectionGroup(a_few);
        Function<Integer, Integer> f1 = bijectionsOf(a_few).get(3);
        a_few.forEach(n -> System.out.printf("%d --> %d; ", n, f1.apply(n)));
        System.out.println();
        Function<Integer, Integer> f2 = g.inverseOf(f1);
        a_few.forEach(n -> System.out.printf("%d --> %d; ", n, f2.apply(n)));
        System.out.println();
        Function<Integer, Integer> id = g.identity();
        a_few.forEach(n -> System.out.printf("%d --> %d; ", n, id.apply(n)));
        System.out.println();
    }


    @Override
    public Function<T, T> binaryOperation(Function<T, T> one, Function<T, T> other) {
        return one.andThen(other);
    }

    @Override
    public Function<T, T> identity() {
        return t -> t;
    }

    @Override
    public Function<T, T> inverseOf(Function<T, T> function) {
        Map<T, T> x = new HashMap<>();
        for (T t : bijSetBase) {
            x.put(function.apply(t), t);
        }
        return x::get;
    }
}