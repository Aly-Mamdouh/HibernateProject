package org.alio;

import entity.TodosEntity;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        HibernateDBManger dbManager = new HibernateDBManger("my-persistence-unit");
        System.out.println("Database Connected Successfully");

        // Open the EntityManager
        dbManager.openEntityManager();

        // Insert a new TodosEntity
        TodosEntity todo = new TodosEntity();
        todo.setTitle("Example Todo");
        todo.setDescription("This is an example todo.");
        dbManager.insertTodoEntity(todo);
        System.out.println("Inserted Todo ID: " + todo.getId());

        // Update the TodosEntity
        TodosEntity updatedTodo = dbManager.getTodoEntityById(18);
        updatedTodo.setTitle("After Update1");
        updatedTodo.setDescription("After Update1");
        dbManager.updateTodoEntity(updatedTodo);
        System.out.println("Updated Todo ID: " + updatedTodo.getId());

        // Delete the TodosEntity by ID
        dbManager.deleteTodoEntityById(14);
        System.out.println("Deleted Todo ID: " + 14);


        // Retrieve all TodosEntities
        System.out.println("All Todos:");
        List<TodosEntity> data = dbManager.selectAllData();
        data.forEach(n ->
                System.out.println(n.getId() + " " + n.getTitle()));

        // Close the EntityManager
        dbManager.closeEntityManager();
    }
}