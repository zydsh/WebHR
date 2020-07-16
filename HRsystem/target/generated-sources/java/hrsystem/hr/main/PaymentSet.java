package hrsystem.hr.main;


import hrsystem.hr.main.Employee_PaySet;

import io.ciera.runtime.summit.classes.IInstanceSet;
import io.ciera.runtime.summit.exceptions.XtumlException;


public interface PaymentSet extends IInstanceSet<PaymentSet,Payment> {

    // attributes


    // selections
    public Employee_PaySet R3_Employee_Pay() throws XtumlException;


}
