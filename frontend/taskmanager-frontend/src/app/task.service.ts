import { Injectable } from '@angular/core';
import { Task } from './task.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const BASE_URL= 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  tasks: Task[] = [{

    id: 1,
  name: "Develop Front end",
  description : "this is the description",
  completed : true,
  dueDate: new Date('06-03-2025'),
  project : 1

  },
  {

    id: 2,
  name: "Design Wireframe",
  description : "this is the description",
  completed : false,
  dueDate: new Date('06-25-2025'),
  project : 3

  },
  {

    id: 3,
  name: "Design Wireframe",
  description : "this is the description",
  completed : false,
  dueDate: new Date('06-25-2025'),
  project : 2

  },{

    id: 4,
  name: "Design Wireframe",
  description : "this is the description",
  completed : false,
  dueDate: new Date('06-25-2025'),
  project : 1

  }

  ]

  constructor(private http:HttpClient){

}

  getTasks() : Observable<Task[]>{
    return this.http.get<Task[]>(`${BASE_URL}/api/tasks`)
  }

  addTask(task: Task){

    return this.http.post(`${BASE_URL}/api/tasks`, {...task}) ;
  }

  updateTask(newTask:Task){
    const updatedIndex= this.tasks.findIndex((task)=>task.id===newTask.id);
    this.tasks[updatedIndex]=newTask;
    return this.tasks;
  }


deleteTask(id:number){
  const taskIndex= this.tasks.findIndex((task)=>task.id===id);
  this.tasks.splice(taskIndex,1);
  return this.tasks;
}


}
