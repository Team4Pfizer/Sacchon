package gr.codehub.sacchon.service;


import gr.codehub.sacchon.dto.DoctorDTO;
import gr.codehub.sacchon.exception.NotFoundException;
import gr.codehub.sacchon.model.Doctor;
import gr.codehub.sacchon.repository.DoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DoctorAdviceServiceImpl implements DoctorAdviceService {
    private final DoctorRepository doctorRepository;

    private Doctor getDoctor(String doctorEmailId) throws NotFoundException{
        Optional<Doctor> doctorOptional = doctorRepository.findByDoctorEmailIdIgnoreCase(doctorEmailId);
        if (doctorOptional.isPresent()) {
            return doctorOptional.get();
        } else {
            throw new NotFoundException("No doctor with this Email: " + doctorEmailId);
        }
    }
    @Override
    public Map<String, Object> viewAccount(String doctorEmailId) throws NotFoundException {
        Map< String, Object > viewAccountMap =new HashMap<>();
        Doctor doctor = getDoctor(doctorEmailId);
        viewAccountMap.put("doctor",new DoctorDTO(doctor));

        return viewAccountMap;
    }

    @Override
    public DoctorDTO signUp(DoctorDTO doctorDTO) {
        Doctor doctor = doctorRepository.save(doctorDTO.toEntity());
        return new DoctorDTO(doctor);
    }


}
