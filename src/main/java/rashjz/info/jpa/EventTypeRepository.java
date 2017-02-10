package rashjz.info.jpa;

import com.vaadin.spring.annotation.SpringComponent;
import com.vaadin.spring.annotation.UIScope;
import org.springframework.data.jpa.repository.JpaRepository;
import rashjz.info.domain.EventType;
import rashjz.info.domain.Users;

/**
 * Created by rasha_000 on 2/10/2017.
 */
@SpringComponent
@UIScope
public interface EventTypeRepository extends JpaRepository<EventType, Long> {
}
