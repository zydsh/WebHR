package hrsystem.hr.main.impl;


import hrsystem.hr.main.Grade;
import hrsystem.hr.main.GradeSet;
import hrsystem.hr.main.PaySet;
import hrsystem.hr.main.impl.PaySetImpl;

import io.ciera.runtime.summit.classes.InstanceSet;
import io.ciera.runtime.summit.exceptions.XtumlException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class GradeSetImpl extends InstanceSet<GradeSet,Grade> implements GradeSet {

    public GradeSetImpl() {
    }

    public GradeSetImpl(Comparator<? super Grade> comp) {
        super(comp);
    }

    // attributes


    // selections
    @Override
    public PaySet R4_is_a_Pay() throws XtumlException {
        PaySet payset = new PaySetImpl();
        for ( Grade grade : this ) payset.add( grade.R4_is_a_Pay() );
        return payset;
    }


    @Override
    public Grade nullElement() {
        return GradeImpl.EMPTY_GRADE;
    }

    @Override
    public GradeSet emptySet() {
      return new GradeSetImpl();
    }

    @Override
    public GradeSet emptySet(Comparator<? super Grade> comp) {
      return new GradeSetImpl(comp);
    }

    @Override
    public List<Grade> elements() {
        return Arrays.asList(toArray(new Grade[0]));
    }

}
