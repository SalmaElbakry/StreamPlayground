package brickset;

import countries.Country;
import repository.Repository;

import java.util.Comparator;
import java.util.Optional;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    //Method 1
    public double AvgNumberOfPieces(){
        return getAll().stream()
                .mapToLong(LegoSet::getPieces)
                .average()
                .getAsDouble();
    }

    //Method 2
    public void SingleName(){
        getAll().stream().
                map(LegoSet::getName).
                filter(s -> s.matches("^[^\\s]+$")).
                distinct().
                sorted().
                forEach(System.out::println);

    }

    //Method 3
    public Optional<LegoSet> NameWithK(){
        return getAll().stream().
                filter(LegoSet -> LegoSet.getName().startsWith("K")).
                findFirst();
    }
    //Method 4
    public boolean ZeroPieces(){
        return getAll().stream().anyMatch(LegoSet -> LegoSet.getPieces() == 0);
    }

    public void DistinctThemeInReverseAlphabeticalOrder(){
        getAll().stream().
                map(LegoSet::getTheme).
                sorted(Comparator.nullsLast(Comparator.reverseOrder())).
                distinct().
                forEach(System.out::println);

    }

    public static void main (String[] args){
        var repository = new LegoSetRepository();

        repository.SingleName();
        System.out.println(repository.AvgNumberOfPieces());
        System.out.println(repository.NameWithK());
        System.out.println(repository.ZeroPieces());
        repository.DistinctThemeInReverseAlphabeticalOrder();

    }

}
