package selise.exam.practicaltest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import selise.exam.practicaltest.entity.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("SELECT task FROM Task task WHERE task.user.id = :userId")
    List<Task> findByUserId(@Param("userId") long userId);

    List<Task> findAllByCompleted(boolean completed);
}
