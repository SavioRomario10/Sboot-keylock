package sbootkeyclock.controller;

import java.util.List;
import java.util.UUID;

import sbootkeyclock.model.Curso;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cursos")
public class CursoController {
  
  @GetMapping("/java")
  public ResponseEntity<Curso> cursoJava(){
    return ResponseEntity.ok(new Curso(UUID.randomUUID(), "Java"));
  }

  @GetMapping("/spring")
  public ResponseEntity<Curso> cursoSpring(){
    return ResponseEntity.ok(new Curso(UUID.randomUUID(), "Spring"));
  }

  @GetMapping("/exclusivos")
  public ResponseEntity<List<Curso>> exclusivox(){
    Curso angular = new Curso(UUID.randomUUID(), "Angular");
    Curso react = new Curso(UUID.randomUUID(), "React");
    Curso view = new Curso(UUID.randomUUID(), "View");

    return ResponseEntity.ok(List.of(angular, react, view));
  }
  
  @GetMapping("/abertos")
  public ResponseEntity<List<Curso>> abertos(){
    Curso java = new Curso(UUID.randomUUID(), "Java");
    Curso spring = new Curso(UUID.randomUUID(), "Spring");
  
    return ResponseEntity.ok(List.of(java, spring));
  }
}