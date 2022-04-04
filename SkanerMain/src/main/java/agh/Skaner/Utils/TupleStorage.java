package agh.Skaner.Utils;

import agh.Skaner.Types.Tuple;

import java.util.ArrayList;
import java.util.List;

public class TupleStorage {

    public List<Tuple> getListOfTuples() {
        return listOfTuples;
    }

    private final List<Tuple> listOfTuples;

    public TupleStorage(List<Tuple> list)
    {
        listOfTuples = list;
    }

    public TupleStorage()
    {
        listOfTuples = new ArrayList<>();
    }

    @Override
    public String toString() {

        if (listOfTuples.size() == 0)
        {
            return "";
        }

        StringBuilder build = new StringBuilder();
        for(int i = 0; i < listOfTuples.size() - 1; i++)
        {
            build.append(listOfTuples.get(i).toString());
            build.append(',');
        }
        build.append(listOfTuples.get(listOfTuples.size()-1));
        return build.toString();
    }

    public void add(Tuple tup)
    {
        listOfTuples.add(tup);
    }

    public int count()
    {
        return listOfTuples.size();
    }
}
