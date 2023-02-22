package gr.codehub.sacchon.logger;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomLoggerRepository extends JpaRepository<CustomLoggerModel,Long> {

}
