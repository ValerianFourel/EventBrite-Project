package src;

import src.Event;

public interface VIPEvent extends Event {
    // interface to get the VIPs from events with VIPS
    int getVIPattendees();
    void setVIPattendees(int VIPattendees);

}
