package hrsystem.hr.main;


import hrsystem.hr.main.EmployeeSet;
import hrsystem.hr.main.PaySet;
import hrsystem.hr.main.PaymentSet;

import io.ciera.runtime.summit.classes.IInstanceSet;
import io.ciera.runtime.summit.exceptions.XtumlException;


public interface Employee_PaySet extends IInstanceSet<Employee_PaySet,Employee_Pay> {

    // attributes


    // selections
    public EmployeeSet R2_earned_Employee() throws XtumlException;
    public PaySet R2_recieves_Pay() throws XtumlException;
    public PaymentSet R3_Payment() throws XtumlException;


}
