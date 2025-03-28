package br.com.alexsandro.desafio_todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alexsandro.desafio_todolist.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
