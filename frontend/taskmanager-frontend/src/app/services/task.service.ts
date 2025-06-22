import { Injectable } from '@angular/core';
import { Task } from '../task.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const BASE_URL= 'http://localhost:8080';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

constructor(private http:HttpClient){

}

  getTasks() : Observable<Task[]>{
    return this.http.get<Task[]>(`${BASE_URL}/api/tasks`)
  }

  addTask(task: Task){
    console.log("in the post task");
    return this.http.post(`${BASE_URL}/api/tasks`, {...task,id:null,project:null}) ;
  }

  updateTask(newTask:Task){

    return this.http.put(`${BASE_URL}/api/tasks/${newTask.id}`,{...newTask, project:null});


  }


deleteTask(id:number){
  return this.http.delete(`${BASE_URL}/api/tasks/${id}`);

}


}
