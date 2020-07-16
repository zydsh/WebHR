package hrsystem.hr.main.impl;


import hrsystem.Hr;
import hrsystem.hr.main.Bonus;
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


public class BonusImpl extends ModelInstance<Bonus,Hr> implements Bonus {

    public static final String KEY_LETTERS = "Bonus";
    public static final Bonus EMPTY_BONUS = new EmptyBonus();

    private Hr context;

    // constructors
    private BonusImpl( Hr context ) {
        this.context = context;
        R4_is_a_Pay_inst = PayImpl.EMPTY_PAY;
    }

    private BonusImpl( Hr context, UniqueId instanceId ) {
        super(instanceId);
        this.context = context;
        R4_is_a_Pay_inst = PayImpl.EMPTY_PAY;
    }

    public static Bonus create( Hr context ) throws XtumlException {
        Bonus newBonus = new BonusImpl( context );
        if ( context.addInstance( newBonus ) ) {
            newBonus.getRunContext().addChange(new InstanceCreatedDelta(newBonus, KEY_LETTERS));
            return newBonus;
        }
        else throw new InstancePopulationException( "Instance already exists within this population." );
    }

    public static Bonus create( Hr context, UniqueId instanceId ) throws XtumlException {
        Bonus newBonus = new BonusImpl( context, instanceId );
        if ( context.addInstance( newBonus ) ) {
            return newBonus;
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
    public Bonus self() {
        return this;
    }

    @Override
    public Bonus oneWhere(IWhere<IXtumlType> condition) throws XtumlException {
        if (null == condition) throw new XtumlException("Null condition passed to selection.");
        if (condition.evaluate(this)) return this;
        else return EMPTY_BONUS;
    }

}

class EmptyBonus extends ModelInstance<Bonus,Hr> implements Bonus {

    // attributes


    // operations


    // selections
    @Override
    public Pay R4_is_a_Pay() {
        return PayImpl.EMPTY_PAY;
    }


    @Override
    public String getKeyLetters() {
        return BonusImpl.KEY_LETTERS;
    }

    @Override
    public Bonus self() {
        return this;
    }

    @Override
    public boolean isEmpty() {
        return true;
    }

    @Override
    public Bonus oneWhere(IWhere<IXtumlType> condition) throws XtumlException {
        if (null == condition) throw new XtumlException("Null condition passed to selection.");
        return BonusImpl.EMPTY_BONUS;
    }

}
