package hrsystem.ui;


import hrsystem.UI;

import io.ciera.runtime.summit.exceptions.BadArgumentException;
import io.ciera.runtime.summit.exceptions.XtumlException;
import io.ciera.runtime.summit.interfaces.IMessage;
import io.ciera.runtime.summit.interfaces.IPort;
import io.ciera.runtime.summit.interfaces.Port;
import io.ciera.runtime.summit.types.BooleanUtil;
import io.ciera.runtime.summit.types.StringUtil;

import ui.shared.IEP;


public class UIApp extends Port<UI> implements IEP {

    public UIApp( UI context, IPort<?> peer ) {
        super( context, peer );
    }

    // inbound messages
    public void Reply( final String p_msg,  final boolean p_state ) throws XtumlException {
        context().Reply( p_msg, p_state );
    }



    // outbound messages
    public void Start( final int p_National_ID ) throws XtumlException {
        if ( satisfied() ) send(new IEP.Start(p_National_ID));
        else {
        }
    }
    public void New_Leave( final int p_Name,  final int p_NumberOfAllowedDays ) throws XtumlException {
        if ( satisfied() ) send(new IEP.New_Leave(p_Name, p_NumberOfAllowedDays));
        else {}    }
    public void New( final String p_FName,  final String p_LName,  final int p_National_ID ) throws XtumlException {
        if ( satisfied() ) send(new IEP.New(p_FName, p_LName, p_National_ID));
        else {
        }
    }


    @Override
    public void deliver( IMessage message ) throws XtumlException {
        if ( null == message ) throw new BadArgumentException( "Cannot deliver null message." );
        switch ( message.getId() ) {
            case IEP.SIGNAL_NO_REPLY:
                Reply(StringUtil.deserialize(message.get(0)), BooleanUtil.deserialize(message.get(1)));
                break;
        default:
            throw new BadArgumentException( "Message not implemented by this port." );
        }
    }



    @Override
    public String getName() {
        return "App";
    }

}
