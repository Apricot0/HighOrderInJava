import java.util.Collections;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class  BijectionGroup  implements  Group<Function> {
    public List<Function<T,T>> 
    
    
    
    public static <T> List<Function<T, T>> bijectionsOf(Set<T> domain) {
        List<T> domainList = new ArrayList<>();
        domainList.addAll(domain);
        List<List<T>> allPerm = new ArrayList<>();
        List<Function<T,T>> allFun = new ArrayList<>();
        permutation(domainList,allPerm,0,domainList.size()-1);

        List<HashMap<T,T>> bijectionMap = new ArrayList<>();
        for (List<T> x: allPerm){
            HashMap<T,T> map = new HashMap<>();
            int i = 0;
            for (T y: domain){
                map.put(y,x.get(i));
                i++;
            }
            bijectionMap.add(map);
        }
        for (HashMap<T,T> map: bijectionMap){
            Function<T,T> x = new Function<>() {
                @Override
                public T apply(T t) {
    
                    return map.get(t);
                }
            };
            allFun.add(x);
        }
        return allFun;

    }
    
    
    public static <T> void permutation (List<T> oriSet, List<List<T>> allPerm, int l, int r){
        if (l==r){
            List<T> toAdd = new ArrayList<>();
            toAdd.addAll(oriSet);
            allPerm.add(toAdd);
        }else{
            for (int i = l; i <=r; i++){
                Collections.swap(oriSet,l,i);
                permutation(oriSet, allPerm, l+1, r);
                Collections.swap(oriSet,l,i);
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
    }
    @Override
    public Object binaryOperation(Object one, Object other) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Object identity() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Object inverseOf(Object t) {
        // TODO Auto-generated method stub
        return null;
    }
}