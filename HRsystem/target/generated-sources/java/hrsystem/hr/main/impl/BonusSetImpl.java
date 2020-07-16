package hrsystem.hr.main.impl;


import hrsystem.hr.main.Bonus;
import hrsystem.hr.main.BonusSet;
import hrsystem.hr.main.PaySet;
import hrsystem.hr.main.impl.PaySetImpl;

import io.ciera.runtime.summit.classes.InstanceSet;
import io.ciera.runtime.summit.exceptions.XtumlException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class BonusSetImpl extends InstanceSet<BonusSet,Bonus> implements BonusSet {

    public BonusSetImpl() {
    }

    public BonusSetImpl(Comparator<? super Bonus> comp) {
        super(comp);
    }

    // attributes


    // selections
    @Override
    public PaySet R4_is_a_Pay() throws XtumlException {
        PaySet payset = new PaySetImpl();
        for ( Bonus bonus : this ) payset.add( bonus.R4_is_a_Pay() );
        return payset;
    }


    @Override
    public Bonus nullElement() {
        return BonusImpl.EMPTY_BONUS;
    }

    @Override
    public BonusSet emptySet() {
      return new BonusSetImpl();
    }

    @Override
    public BonusSet emptySet(Comparator<? super Bonus> comp) {
      return new BonusSetImpl(comp);
    }

    @Override
    public List<Bonus> elements() {
        return Arrays.asList(toArray(new Bonus[0]));
    }

}
