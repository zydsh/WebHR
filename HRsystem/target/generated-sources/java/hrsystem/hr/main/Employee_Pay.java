package hrsystem.hr.main;


import hrsystem.Hr;
import hrsystem.hr.main.Employee;
import hrsystem.hr.main.Pay;
import hrsystem.hr.main.Payment;
import hrsystem.hr.main.PaymentSet;

import io.ciera.runtime.summit.classes.IModelInstance;
import io.ciera.runtime.summit.exceptions.XtumlException;


public interface Employee_Pay extends IModelInstance<Employee_Pay,Hr> {

    // attributes


    // operations


    // selections
    default public void setR2_earned_Employee( Employee inst ) {}
    public Employee R2_earned_Employee() throws XtumlException;
    default public void setR2_recieves_Pay( Pay inst ) {}
    public Pay R2_recieves_Pay() throws XtumlException;
    default public void addR3_Payment( Payment inst ) {}
    default public void removeR3_Payment( Payment inst ) {}
    public PaymentSet R3_Payment() throws XtumlException;


}
