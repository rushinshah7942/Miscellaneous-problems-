// Nothing in machine (Empty)

package statepattern;

import statepattern.exception.MachineWarning;

public class EmptyState implements State{
    VendingMachine machine;
    public EmptyState(VendingMachine machine) {
        this.machine =  machine;
    }
    public void insertCoin() throws MachineWarning{
        throw new MachineWarning("Can not process the request");
    }
    public void pressButton() throws MachineWarning{
        throw new MachineWarning("Invalid Action");
    }
    public void dispense() throws MachineWarning{
        throw new MachineWarning("Invalid Action");
    }
}