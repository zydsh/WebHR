package hrsystem.hr.main.impl;


import hrsystem.hr.main.EmployeeSet;
import hrsystem.hr.main.Employee_Pay;
import hrsystem.hr.main.Employee_PaySet;
import hrsystem.hr.main.PaySet;
import hrsystem.hr.main.PaymentSet;
import hrsystem.hr.main.impl.EmployeeSetImpl;
import hrsystem.hr.main.impl.PaySetImpl;
import hrsystem.hr.main.impl.PaymentSetImpl;

import io.ciera.runtime.summit.classes.InstanceSet;
import io.ciera.runtime.summit.exceptions.XtumlException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class Employee_PaySetImpl extends InstanceSet<Employee_PaySet,Employee_Pay> implements Employee_PaySet {

    public Employee_PaySetImpl() {
    }

    public Employee_PaySetImpl(Comparator<? super Employee_Pay> comp) {
        super(comp);
    }

    // attributes


    // selections
    @Override
    public EmployeeSet R2_earned_Employee() throws XtumlException {
        EmployeeSet employeeset = new EmployeeSetImpl();
        for ( Employee_Pay employee_pay : this ) employeeset.add( employee_pay.R2_earned_Employee() );
        return employeeset;
    }
    @Override
    public PaySet R2_recieves_Pay() throws XtumlException {
        PaySet payset = new PaySetImpl();
        for ( Employee_Pay employee_pay : this ) payset.add( employee_pay.R2_recieves_Pay() );
        return payset;
    }
    @Override
    public PaymentSet R3_Payment() throws XtumlException {
        PaymentSet paymentset = new PaymentSetImpl();
        for ( Employee_Pay employee_pay : this ) paymentset.addAll( employee_pay.R3_Payment() );
        return paymentset;
    }


    @Override
    public Employee_Pay nullElement() {
        return Employee_PayImpl.EMPTY_EMPLOYEE_PAY;
    }

    @Override
    public Employee_PaySet emptySet() {
      return new Employee_PaySetImpl();
    }

    @Override
    public Employee_PaySet emptySet(Comparator<? super Employee_Pay> comp) {
      return new Employee_PaySetImpl(comp);
    }

    @Override
    public List<Employee_Pay> elements() {
        return Arrays.asList(toArray(new Employee_Pay[0]));
    }

}
