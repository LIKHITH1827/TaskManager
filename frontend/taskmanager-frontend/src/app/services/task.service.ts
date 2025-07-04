import { Injectable } from '@angular/core';
import { Task } from '../task.model';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const BASE_URL= 'http://localhost:8080/api/tasks';

@Injectable({
  providedIn: 'root'
})
export class TaskService {

constructor(private http:HttpClient){

}

  getTasks() : Observable<Task[]>{
    return this.http.get<Task[]>(`${BASE_URL}`)
  }

  addTask(task: Task){
    console.log("in the post task");
    return this.http.post(`${BASE_URL}`, {...task,id:null,project:null}) ;
  }

  updateTask(newTask:Task){

    return this.http.put(`${BASE_URL}/${newTask.id}`,{...newTask, project:null});


  }


deleteTask(id:number){
  return this.http.delete(`${BASE_URL}/${id}`);

}


}
