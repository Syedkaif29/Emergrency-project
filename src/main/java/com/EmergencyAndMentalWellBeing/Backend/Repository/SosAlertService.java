package com.EmergencyAndMentalWellBeing.Backend.Repository;

import com.EmergencyAndMentalWellBeing.Backend.Model.SosAlert;

public interface SosAlertService {
    boolean sendSosNotification(SosAlert sosAlert);
}

