package data;

import java.util.List;

public interface ServerData {
public Pin getPin(int pin_no);
public PinOutput getOutputPinbyPin_no(int pin_no);
public PinInput getIntputPinbyPin_no(int pin_no);
public void insertInputPin(int pin_no,float value,String name,String sensor);
public void insertOutputPin(int pin_no,int value,String name);
public void removeInputPinbyPin_no(int pin_no);
public void removeOutputPinbyPin_no(int pin_no);
public void removePinByPin_no(int pin_no);
public List<Pin> getPins();
public List<PinOutput> getPinsOutput();
public List<PinInput> getPinsInput();
}
