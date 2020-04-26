package lv.javaguru.aaa.examplebootandjpa.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Repository -> CRUDRepository -> PagingAndSortingRepository -> JpaRepository

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByDescription(String description);

    @Query("select t from Task t where t.description like 'B%'")
    List<Task> findAllByDescStartWithB();

    List<Task> findAllByDescStartWithA();

    @Query("select u from Task u where u.name = :name or u.description = :desc")
    Task findByNameOrDescription(@Param("name") String name,
                                   @Param("desc") String description);

}


