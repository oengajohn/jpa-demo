package io.training.dao;

import io.training.model.Todo;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TodoDao {
  @PersistenceContext(unitName = "trainingUnit" )
  private EntityManager em;

  public List<Todo> getAllTodos(){
    return em.createQuery("select ua from Todo ua",Todo.class)
        .getResultList();

  }
  public Optional<Todo> getTodoWithGivenId(Long todoId){
    var users=  em.createQuery("select ua from Todo ua where ua.id=:todoId",Todo.class)
        .setParameter("todoId",todoId)
        .getResultList();
    if(users.size()>0){
      return Optional.of(users.get(0));
    }
    return Optional.empty();

  }

  public Todo updateTodo(Todo Todo){
    return em.merge(Todo);
  }

  public  void createTodo(Todo Todo){
    em.persist(Todo);

  }
  public  void deleteTodoWithGivenTodoId(Long todoId){
    var TodoOptional = getTodoWithGivenId(todoId);

    TodoOptional.ifPresent(Todo -> {
      em.merge(Todo);
      em.remove(Todo);
    });

  }

}
