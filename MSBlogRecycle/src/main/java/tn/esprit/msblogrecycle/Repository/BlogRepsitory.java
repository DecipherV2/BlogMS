package tn.esprit.msblogrecycle.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.msblogrecycle.Entity.Blog;

public interface BlogRepsitory extends JpaRepository<Blog,Integer> {
}
