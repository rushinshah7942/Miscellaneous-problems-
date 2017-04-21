// State interface
import statepattern.exception.MachineWarning;
public interface State {

    public void insertCoin()throws MachineWarning;
    public void pressButton()throws MachineWarning;
    public void dispense()throws MachineWarning;
}