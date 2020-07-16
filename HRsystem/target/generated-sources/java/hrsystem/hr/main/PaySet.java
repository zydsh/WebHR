package hrsystem.hr.main;


import hrsystem.hr.main.BonusSet;
import hrsystem.hr.main.Employee_PaySet;
import hrsystem.hr.main.GradeSet;
import hrsystem.hr.main.OvertimeSet;

import io.ciera.runtime.summit.classes.IInstanceSet;
import io.ciera.runtime.summit.exceptions.XtumlException;


public interface PaySet extends IInstanceSet<PaySet,Pay> {

    // attributes


    // selections
    public Employee_PaySet R2_earned_Employee_Pay() throws XtumlException;
    public BonusSet R4_is_a_Bonus() throws XtumlException;
    public GradeSet R4_is_a_Grade() throws XtumlException;
    public OvertimeSet R4_is_a_Overtime() throws XtumlException;


}
