package hrsystem.hr.main.impl;


import hrsystem.Hr;
import hrsystem.hr.main.Employee;
import hrsystem.hr.main.Employee_Pay;
import hrsystem.hr.main.Pay;
import hrsystem.hr.main.Payment;
import hrsystem.hr.main.PaymentSet;
import hrsystem.hr.main.impl.EmployeeImpl;
import hrsystem.hr.main.impl.PayImpl;
import hrsystem.hr.main.impl.PaymentSetImpl;

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


public class Employee_PayImpl extends ModelInstance<Employee_Pay,Hr> implements Employee_Pay {

    public static final String KEY_LETTERS = "Employee_Pay";
    public static final Employee_Pay EMPTY_EMPLOYEE_PAY = new EmptyEmployee_Pay();

    private Hr context;

    // constructors
    private Employee_PayImpl( Hr context ) {
        this.context = context;
        R2_earned_Employee_inst = EmployeeImpl.EMPTY_EMPLOYEE;
        R2_recieves_Pay_inst = PayImpl.EMPTY_PAY;
        R3_Payment_set = new PaymentSetImpl();
    }

    private Employee_PayImpl( Hr context, UniqueId instanceId ) {
        super(instanceId);
        this.context = context;
        R2_earned_Employee_inst = EmployeeImpl.EMPTY_EMPLOYEE;
        R2_recieves_Pay_inst = PayImpl.EMPTY_PAY;
        R3_Payment_set = new PaymentSetImpl();
    }

    public static Employee_Pay create( Hr context ) throws XtumlException {
        Employee_Pay newEmployee_Pay = new Employee_PayImpl( context );
        if ( context.addInstance( newEmployee_Pay ) ) {
            newEmployee_Pay.getRunContext().addChange(new InstanceCreatedDelta(newEmployee_Pay, KEY_LETTERS));
            return newEmployee_Pay;
        }
        else throw new InstancePopulationException( "Instance already exists within this population." );
    }

    public static Employee_Pay create( Hr context, UniqueId instanceId ) throws XtumlException {
        Employee_Pay newEmployee_Pay = new Employee_PayImpl( context, instanceId );
        if ( context.addInstance( newEmployee_Pay ) ) {
            return newEmployee_Pay;
        }
        else throw new InstancePopulationException( "Instance already exists within this population." );
    }



    // attributes


    // instance identifiers

    // operations


    // static operations


    // events


    // selections
    private Employee R2_earned_Employee_inst;
    @Override
    public void setR2_earned_Employee( Employee inst ) {
        R2_earned_Employee_inst = inst;
    }
    @Override
    public Employee R2_earned_Employee() throws XtumlException {
        return R2_earned_Employee_inst;
    }
    private Pay R2_recieves_Pay_inst;
    @Override
    public void setR2_recieves_Pay( Pay inst ) {
        R2_recieves_Pay_inst = inst;
    }
    @Override
    public Pay R2_recieves_Pay() throws XtumlException {
        return R2_recieves_Pay_inst;
    }
    private PaymentSet R3_Payment_set;
    @Override
    public void addR3_Payment( Payment inst ) {
        R3_Payment_set.add(inst);
    }
    @Override
    public void removeR3_Payment( Payment inst ) {
        R3_Payment_set.remove(inst);
    }
    @Override
    public PaymentSet R3_Payment() throws XtumlException {
        return R3_Payment_set;
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
    public Employee_Pay self() {
        return this;
    }

    @Override
    public Employee_Pay oneWhere(IWhere<IXtumlType> condition) throws XtumlException {
        if (null == condition) throw new XtumlException("Null condition passed to selection.");
        if (condition.evaluate(this)) return this;
        else return EMPTY_EMPLOYEE_PAY;
    }

}

class EmptyEmployee_Pay extends ModelInstance<Employee_Pay,Hr> implements Employee_Pay {

    // attributes


    // operations


    // selections
    @Override
    public Employee R2_earned_Employee() {
        return EmployeeImpl.EMPTY_EMPLOYEE;
    }
    @Override
    public Pay R2_recieves_Pay() {
        return PayImpl.EMPTY_PAY;
    }
    @Override
    public PaymentSet R3_Payment() {
        return (new PaymentSetImpl());
    }


    @Override
    public String getKeyLetters() {
        return Employee_PayImpl.KEY_LETTERS;
    }

    @Override
    public Employee_Pay self() {
        return this;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Employee_Pay oneWhere(IWhere<IXtumlType> condition) throws XtumlException {
        if (null == condition) throw new XtumlException("Null condition passed to selection.");
        return Employee_PayImpl.EMPTY_EMPLOYEE_PAY;
    }

}
