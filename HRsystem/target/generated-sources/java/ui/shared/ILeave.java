package ui.shared;


import io.ciera.runtime.summit.exceptions.XtumlException;
import io.ciera.runtime.summit.interfaces.Message;


public interface ILeave {

    // to provider messages
    public static final int SIGNAL_NO_NEW_LEAVE = 1;
    public static class New_Leave extends Message {
        public New_Leave( final String p_Name,  final int p_NumberOfAllowedDays ) {
            super(new Object[]{p_Name,  p_NumberOfAllowedDays});
        }
        @Override
        public int getId() {
            return SIGNAL_NO_NEW_LEAVE;
        }
 
    }
    public void New_Leave( final String p_Name,  final int p_NumberOfAllowedDays ) throws XtumlException;
    public static final int SIGNAL_NO_REQUEST = 2;
    public static class Request extends Message {
        public Request( final String p_Starting,  final String p_Ending,  final int p_National_ID,  final String p_Name ) {
            super(new Object[]{p_Starting,  p_Ending,  p_National_ID,  p_Name});
        }
        @Override
        public int getId() {
            return SIGNAL_NO_REQUEST;
        }
 
    }
    public void Request( final String p_Starting,  final String p_Ending,  final int p_National_ID,  final String p_Name ) throws XtumlException;
    public static final int SIGNAL_NO_RETURN = 3;
    public static class Return extends Message {
        public Return( final int p_National_ID ) {
            super(new Object[]{p_National_ID});
        }
        @Override
        public int getId() {
            return SIGNAL_NO_RETURN;
        }
 
    }
    public void Return( final int p_National_ID ) throws XtumlException;


    // from provider messages


}
