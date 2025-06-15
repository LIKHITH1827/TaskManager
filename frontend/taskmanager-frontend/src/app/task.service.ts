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

    return this.http.post(`${BASE_URL}/api/tasks`, {...task,id:null,project:null}) ;
  }

  updateTask(newTask:Task){

    return this.http.put(`${BASE_URL}/api/tasks/${newTask.id}`,{...newTask, project:null});


  }


deleteTask(id:number){
  return this.http.delete(`${BASE_URL}/api/tasks/${id}`);

}


}
