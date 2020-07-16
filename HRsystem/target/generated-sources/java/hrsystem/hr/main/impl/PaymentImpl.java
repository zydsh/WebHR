package hrsystem.hr.main.impl;


import hrsystem.Hr;
import hrsystem.hr.main.Employee_Pay;
import hrsystem.hr.main.Payment;
import hrsystem.hr.main.impl.Employee_PayImpl;

import io.ciera.runtime.instanceloading.InstanceCreatedDelta;
import io.ciera.runtime.summit.application.IRunContext;
import io.ciera.runtime.summit.classes.IInstanceIdentifier;
import io.ciera.runtime.summit.classes.InstanceIdentifier;
import io.ciera.runtime.summit.classes.ModelInstance;
import io.ciera.runtime.summit.exceptions.InstancePopulationException;
import io.ciera.runtime.summit.exceptions.XtumlException;
import io.ciera.runtime.summit.types.IWhere;
import io.ciera.runtime.summit.types.IXtumlType;
import io.ciera.runtime.summit.types.UniqueId;


public class PaymentImpl extends ModelInstance<Payment,Hr> implements Payment {

    public static final String KEY_LETTERS = "Payment";
    public static final Payment EMPTY_PAYMENT = new EmptyPayment();

    private Hr context;

    // constructors
    private PaymentImpl( Hr context ) {
        this.context = context;
        R3_Employee_Pay_inst = Employee_PayImpl.EMPTY_EMPLOYEE_PAY;
    }

    private PaymentImpl( Hr context, UniqueId instanceId ) {
        super(instanceId);
        this.context = context;
        R3_Employee_Pay_inst = Employee_PayImpl.EMPTY_EMPLOYEE_PAY;
    }

    public static Payment create( Hr context ) throws XtumlException {
        Payment newPayment = new PaymentImpl( context );
        if ( context.addInstance( newPayment ) ) {
            newPayment.getRunContext().addChange(new InstanceCreatedDelta(newPayment, KEY_LETTERS));
            return newPayment;
        }
        else throw new InstancePopulationException( "Instance already exists within this population." );
    }

    public static Payment create( Hr context, UniqueId instanceId ) throws XtumlException {
        Payment newPayment = new PaymentImpl( context, instanceId );
        if ( context.addInstance( newPayment ) ) {
            return newPayment;
        }
        else throw new InstancePopulationException( "Instance already exists within this population." );
    }



    // attributes


    // instance identifiers

    // operations


    // static operations


    // events


    // selections
    private Employee_Pay R3_Employee_Pay_inst;
    @Override
    public void setR3_Employee_Pay( Employee_Pay inst ) {
        R3_Employee_Pay_inst = inst;
    }
    @Override
    public Employee_Pay R3_Employee_Pay() throws XtumlException {
        return R3_Employee_Pay_inst;
    }


    @Override
    public IRunContext getRunContext() {
        return context().getRunContext();
    }

    @Override
    public Hr context() {
        return context;
    }

    @Override
    public String getKeyLetters() {
        return KEY_LETTERS;
    }

    @Override
    public Payment self() {
        return this;
    }

    @Override
    public Payment oneWhere(IWhere<IXtumlType> condition) throws XtumlException {
        if (null == condition) throw new XtumlException("Null condition passed to selection.");
        if (condition.evaluate(this)) return this;
        else return EMPTY_PAYMENT;
    }

}

class EmptyPayment extends ModelInstance<Payment,Hr> implements Payment {

    // attributes


    // operations


    // selections
    @Override
    public Employee_Pay R3_Employee_Pay() {
        return Employee_PayImpl.EMPTY_EMPLOYEE_PAY;
    }


    @Override
    public String getKeyLetters() {
        return PaymentImpl.KEY_LETTERS;
    }

    @Override
    public Payment self() {
        return this;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Payment oneWhere(IWhere<IXtumlType> condition) throws XtumlException {
        if (null == condition) throw new XtumlException("Null condition passed to selection.");
        return PaymentImpl.EMPTY_PAYMENT;
    }

}
