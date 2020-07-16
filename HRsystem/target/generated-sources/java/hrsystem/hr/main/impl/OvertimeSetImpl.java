package hrsystem.hr.main.impl;


import hrsystem.hr.main.Overtime;
import hrsystem.hr.main.OvertimeSet;
import hrsystem.hr.main.PaySet;
import hrsystem.hr.main.impl.PaySetImpl;

import io.ciera.runtime.summit.classes.InstanceSet;
import io.ciera.runtime.summit.exceptions.XtumlException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class OvertimeSetImpl extends InstanceSet<OvertimeSet,Overtime> implements OvertimeSet {

    public OvertimeSetImpl() {
    }

    public OvertimeSetImpl(Comparator<? super Overtime> comp) {
        super(comp);
    }

    // attributes


    // selections
    @Override
    public PaySet R4_is_a_Pay() throws XtumlException {
        PaySet payset = new PaySetImpl();
        for ( Overtime overtime : this ) payset.add( overtime.R4_is_a_Pay() );
        return payset;
    }


    @Override
    public Overtime nullElement() {
        return OvertimeImpl.EMPTY_OVERTIME;
    }

    @Override
    public OvertimeSet emptySet() {
      return new OvertimeSetImpl();
    }

    @Override
    public OvertimeSet emptySet(Comparator<? super Overtime> comp) {
      return new OvertimeSetImpl(comp);
    }

    @Override
    public List<Overtime> elements() {
        return Arrays.asList(toArray(new Overtime[0]));
    }

}
