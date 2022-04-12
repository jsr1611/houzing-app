package uz.digitalone.houzingapp.repository;

/*
 * project:  houzing-app
 * author:   Jumanazar Said
 * created:  25/02/2022 2:56 PM
 */


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.digitalone.houzingapp.entity.Attachment;
@Repository
public interface AttachmentRepository extends JpaRepository<Attachment, Long> {
}
