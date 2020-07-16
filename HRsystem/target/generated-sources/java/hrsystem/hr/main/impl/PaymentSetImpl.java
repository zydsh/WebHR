package hrsystem.hr.main.impl;


import hrsystem.hr.main.Employee_PaySet;
import hrsystem.hr.main.Payment;
import hrsystem.hr.main.PaymentSet;
import hrsystem.hr.main.impl.Employee_PaySetImpl;

import io.ciera.runtime.summit.classes.InstanceSet;
import io.ciera.runtime.summit.exceptions.XtumlException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class PaymentSetImpl extends InstanceSet<PaymentSet,Payment> implements PaymentSet {

    public PaymentSetImpl() {
    }

    public PaymentSetImpl(Comparator<? super Payment> comp) {
        super(comp);
    }

    // attributes


    // selections
    @Override
    public Employee_PaySet R3_Employee_Pay() throws XtumlException {
        Employee_PaySet employee_payset = new Employee_PaySetImpl();
        for ( Payment payment : this ) employee_payset.add( payment.R3_Employee_Pay() );
        return employee_payset;
    }


    @Override
    public Payment nullElement() {
        return PaymentImpl.EMPTY_PAYMENT;
    }

    @Override
    public PaymentSet emptySet() {
      return new PaymentSetImpl();
    }

    @Override
    public PaymentSet emptySet(Comparator<? super Payment> comp) {
      return new PaymentSetImpl(comp);
    }

    @Override
    public List<Payment> elements() {
        return Arrays.asList(toArray(new Payment[0]));
    }

}
