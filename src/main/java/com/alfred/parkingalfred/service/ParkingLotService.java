package com.alfred.parkingalfred.service;

import com.alfred.parkingalfred.entity.ParkingLot;
import java.util.List;

public interface ParkingLotService {

  public List<ParkingLot> getParkingLotsByParkingBoyId(Long parkingBoyId);


}
