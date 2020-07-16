package hrsystem.hr.main.impl;


import hrsystem.Hr;
import hrsystem.hr.main.Grade;
import hrsystem.hr.main.Pay;
import hrsystem.hr.main.impl.PayImpl;

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


public class GradeImpl extends ModelInstance<Grade,Hr> implements Grade {

    public static final String KEY_LETTERS = "Grade";
    public static final Grade EMPTY_GRADE = new EmptyGrade();

    private Hr context;

    // constructors
    private GradeImpl( Hr context ) {
        this.context = context;
        R4_is_a_Pay_inst = PayImpl.EMPTY_PAY;
    }

    private GradeImpl( Hr context, UniqueId instanceId ) {
        super(instanceId);
        this.context = context;
        R4_is_a_Pay_inst = PayImpl.EMPTY_PAY;
    }

    public static Grade create( Hr context ) throws XtumlException {
        Grade newGrade = new GradeImpl( context );
        if ( context.addInstance( newGrade ) ) {
            newGrade.getRunContext().addChange(new InstanceCreatedDelta(newGrade, KEY_LETTERS));
            return newGrade;
        }
        else throw new InstancePopulationException( "Instance already exists within this population." );
    }

    public static Grade create( Hr context, UniqueId instanceId ) throws XtumlException {
        Grade newGrade = new GradeImpl( context, instanceId );
        if ( context.addInstance( newGrade ) ) {
            return newGrade;
        }
        else throw new InstancePopulationException( "Instance already exists within this population." );
    }



    // attributes


    // instance identifiers

    // operations


    // static operations


    // events


    // selections
    private Pay R4_is_a_Pay_inst;
    @Override
    public void setR4_is_a_Pay( Pay inst ) {
        R4_is_a_Pay_inst = inst;
    }
    @Override
    public Pay R4_is_a_Pay() throws XtumlException {
        return R4_is_a_Pay_inst;
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
    public Grade self() {
        return this;
    }

    @Override
    public Grade oneWhere(IWhere<IXtumlType> condition) throws XtumlException {
        if (null == condition) throw new XtumlException("Null condition passed to selection.");
        if (condition.evaluate(this)) return this;
        else return EMPTY_GRADE;
    }

}

class EmptyGrade extends ModelInstance<Grade,Hr> implements Grade {

    // attributes


    // operations


    // selections
    @Override
    public Pay R4_is_a_Pay() {
        return PayImpl.EMPTY_PAY;
    }


    @Override
    public String getKeyLetters() {
        return GradeImpl.KEY_LETTERS;
    }

    @Override
    public Grade self() {
        return this;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Grade oneWhere(IWhere<IXtumlType> condition) throws XtumlException {
        if (null == condition) throw new XtumlException("Null condition passed to selection.");
        return GradeImpl.EMPTY_GRADE;
    }

}
