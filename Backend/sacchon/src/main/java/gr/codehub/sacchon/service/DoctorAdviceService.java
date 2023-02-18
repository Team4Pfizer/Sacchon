package gr.codehub.sacchon.service;

import gr.codehub.sacchon.dto.DoctorDTO;
import gr.codehub.sacchon.exception.NotFoundException;

import java.util.Map;

public interface DoctorAdviceService {
    Map<String,Object> viewAccount (String doctorEmailId) throws NotFoundException;

    DoctorDTO signUp(DoctorDTO doctorDTO);
}
