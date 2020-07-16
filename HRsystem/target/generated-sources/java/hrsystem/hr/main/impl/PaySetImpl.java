package hrsystem.hr.main.impl;


import hrsystem.hr.main.BonusSet;
import hrsystem.hr.main.Employee_PaySet;
import hrsystem.hr.main.GradeSet;
import hrsystem.hr.main.OvertimeSet;
import hrsystem.hr.main.Pay;
import hrsystem.hr.main.PaySet;
import hrsystem.hr.main.impl.BonusSetImpl;
import hrsystem.hr.main.impl.Employee_PaySetImpl;
import hrsystem.hr.main.impl.GradeSetImpl;
import hrsystem.hr.main.impl.OvertimeSetImpl;

import io.ciera.runtime.summit.classes.InstanceSet;
import io.ciera.runtime.summit.exceptions.XtumlException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class PaySetImpl extends InstanceSet<PaySet,Pay> implements PaySet {

    public PaySetImpl() {
    }

    public PaySetImpl(Comparator<? super Pay> comp) {
        super(comp);
    }

    // attributes


    // selections
    @Override
    public Employee_PaySet R2_earned_Employee_Pay() throws XtumlException {
        Employee_PaySet employee_payset = new Employee_PaySetImpl();
        for ( Pay pay : this ) employee_payset.addAll( pay.R2_earned_Employee_Pay() );
        return employee_payset;
    }
    @Override
    public BonusSet R4_is_a_Bonus() throws XtumlException {
        BonusSet bonusset = new BonusSetImpl();
        for ( Pay pay : this ) bonusset.add( pay.R4_is_a_Bonus() );
        return bonusset;
    }
    @Override
    public GradeSet R4_is_a_Grade() throws XtumlException {
        GradeSet gradeset = new GradeSetImpl();
        for ( Pay pay : this ) gradeset.add( pay.R4_is_a_Grade() );
        return gradeset;
    }
    @Override
    public OvertimeSet R4_is_a_Overtime() throws XtumlException {
        OvertimeSet overtimeset = new OvertimeSetImpl();
        for ( Pay pay : this ) overtimeset.add( pay.R4_is_a_Overtime() );
        return overtimeset;
    }


    @Override
    public Pay nullElement() {
        return PayImpl.EMPTY_PAY;
    }

    @Override
    public PaySet emptySet() {
      return new PaySetImpl();
    }

    @Override
    public PaySet emptySet(Comparator<? super Pay> comp) {
      return new PaySetImpl(comp);
    }

    @Override
    public List<Pay> elements() {
        return Arrays.asList(toArray(new Pay[0]));
    }

}
