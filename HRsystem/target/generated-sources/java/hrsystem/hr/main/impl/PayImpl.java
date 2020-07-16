package hrsystem.hr.main.impl;


import hrsystem.Hr;
import hrsystem.hr.main.Bonus;
import hrsystem.hr.main.Employee_Pay;
import hrsystem.hr.main.Employee_PaySet;
import hrsystem.hr.main.Grade;
import hrsystem.hr.main.Overtime;
import hrsystem.hr.main.Pay;
import hrsystem.hr.main.impl.BonusImpl;
import hrsystem.hr.main.impl.Employee_PaySetImpl;
import hrsystem.hr.main.impl.GradeImpl;
import hrsystem.hr.main.impl.OvertimeImpl;

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


public class PayImpl extends ModelInstance<Pay,Hr> implements Pay {

    public static final String KEY_LETTERS = "Pay";
    public static final Pay EMPTY_PAY = new EmptyPay();

    private Hr context;

    // constructors
    private PayImpl( Hr context ) {
        this.context = context;
        R2_earned_Employee_Pay_set = new Employee_PaySetImpl();
        R4_is_a_Bonus_inst = BonusImpl.EMPTY_BONUS;
        R4_is_a_Grade_inst = GradeImpl.EMPTY_GRADE;
        R4_is_a_Overtime_inst = OvertimeImpl.EMPTY_OVERTIME;
    }

    private PayImpl( Hr context, UniqueId instanceId ) {
        super(instanceId);
        this.context = context;
        R2_earned_Employee_Pay_set = new Employee_PaySetImpl();
        R4_is_a_Bonus_inst = BonusImpl.EMPTY_BONUS;
        R4_is_a_Grade_inst = GradeImpl.EMPTY_GRADE;
        R4_is_a_Overtime_inst = OvertimeImpl.EMPTY_OVERTIME;
    }

    public static Pay create( Hr context ) throws XtumlException {
        Pay newPay = new PayImpl( context );
        if ( context.addInstance( newPay ) ) {
            newPay.getRunContext().addChange(new InstanceCreatedDelta(newPay, KEY_LETTERS));
            return newPay;
        }
        else throw new InstancePopulationException( "Instance already exists within this population." );
    }

    public static Pay create( Hr context, UniqueId instanceId ) throws XtumlException {
        Pay newPay = new PayImpl( context, instanceId );
        if ( context.addInstance( newPay ) ) {
            return newPay;
        }
        else throw new InstancePopulationException( "Instance already exists within this population." );
    }



    // attributes


    // instance identifiers

    // operations


    // static operations


    // events


    // selections
    private Employee_PaySet R2_earned_Employee_Pay_set;
    @Override
    public void addR2_earned_Employee_Pay( Employee_Pay inst ) {
        R2_earned_Employee_Pay_set.add(inst);
    }
    @Override
    public void removeR2_earned_Employee_Pay( Employee_Pay inst ) {
        R2_earned_Employee_Pay_set.remove(inst);
    }
    @Override
    public Employee_PaySet R2_earned_Employee_Pay() throws XtumlException {
        return R2_earned_Employee_Pay_set;
    }
    private Bonus R4_is_a_Bonus_inst;
    @Override
    public void setR4_is_a_Bonus( Bonus inst ) {
        R4_is_a_Bonus_inst = inst;
    }
    @Override
    public Bonus R4_is_a_Bonus() throws XtumlException {
        return R4_is_a_Bonus_inst;
    }
    private Grade R4_is_a_Grade_inst;
    @Override
    public void setR4_is_a_Grade( Grade inst ) {
        R4_is_a_Grade_inst = inst;
    }
    @Override
    public Grade R4_is_a_Grade() throws XtumlException {
        return R4_is_a_Grade_inst;
    }
    private Overtime R4_is_a_Overtime_inst;
    @Override
    public void setR4_is_a_Overtime( Overtime inst ) {
        R4_is_a_Overtime_inst = inst;
    }
    @Override
    public Overtime R4_is_a_Overtime() throws XtumlException {
        return R4_is_a_Overtime_inst;
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
    public Pay self() {
        return this;
    }

    @Override
    public Pay oneWhere(IWhere<IXtumlType> condition) throws XtumlException {
        if (null == condition) throw new XtumlException("Null condition passed to selection.");
        if (condition.evaluate(this)) return this;
        else return EMPTY_PAY;
    }

}

class EmptyPay extends ModelInstance<Pay,Hr> implements Pay {

    // attributes


    // operations


    // selections
    @Override
    public Employee_PaySet R2_earned_Employee_Pay() {
        return (new Employee_PaySetImpl());
    }
    @Override
    public Bonus R4_is_a_Bonus() {
        return BonusImpl.EMPTY_BONUS;
    }
    @Override
    public Grade R4_is_a_Grade() {
        return GradeImpl.EMPTY_GRADE;
    }
    @Override
    public Overtime R4_is_a_Overtime() {
        return OvertimeImpl.EMPTY_OVERTIME;
    }


    @Override
    public String getKeyLetters() {
        return PayImpl.KEY_LETTERS;
    }

    @Override
    public Pay self() {
        return this;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Pay oneWhere(IWhere<IXtumlType> condition) throws XtumlException {
        if (null == condition) throw new XtumlException("Null condition passed to selection.");
        return PayImpl.EMPTY_PAY;
    }

}
